<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- link href="css/bootstrap.css" rel='stylesheet' type='text/css' /-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js"></script>
<!-- Custom Theme files -->
<link href="../css/style.css" rel='stylesheet' type='text/css' />
<link href="../css/board.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="../fonts/css/font-awesome.min.css">
<script src="../dist/summernote.min.js"></script>
<link href="../dist/summernote.css" rel="stylesheet">
<script src="../dist/summernote-ko-KR.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>    
function resizeBoard(){
		$("span.content").html(function(){
			var msg = "";
			var icon = "";
			if($(window).width() <= 768){
				var span = $(this).parent();
				var writer = span.siblings(".writer").text();
				var date = span.siblings(".date").text();
				var hit = "조회 " + span.siblings(".hit").text();
				msg = [writer, date, hit].join(" | ");
				
				if(span.prev().find("img").length > 0){
					icon = "<img src='images/ico-list-notice.gif'>";
				}
				
			} 
			$(this).siblings('i').html(icon);
			return msg;
		}); 
	}
	 
	$(document).ready(function(){
		resizeBoard();
		
		$(window).resize(function(){
			resizeBoard();
		});
		
		$('#writeModal').on('shown.bs.modal', function (e) {
			$('#summernote').summernote({
				onBlur : function(e) {
		            $('#summercontent').html($('#summernote').code());
		        },
		        height : 450,
		        minHeight: null,
		        maxHeight: null,
		        lang: 'ko-KR'
		    });
			$('span.note-current-fontsize').text(12);
			$('div.note-editable p').css('font-size', '12px');
		})
		
		$('#btnSub').click(function(){
			var sub=$('#inputSubject').val();
			if(sub=="")
			{
				alert('제목을 입력해주세요');
				$('#inputSubject').focus();
				return;
			}
			var cont=$('#summercontent').val();
			if(cont=="")
			{
				alert('내용을 입력해주세요');
				$('#summercontent').focus();

				return;
			} 
			$('writeForm').submit();
		});
	});
</script>
</head>
<body>
<div class="about_top">
    <div class="container">
		
		<div style="border-top: 1px solid #E2E2E2; border-bottom: 1px solid #E2E2E2;">
			<div class="text-right">
				<ol class="breadcrumb" style="background-color: #fff">
				</ol>
			</div>
			<div align=center style="padding-bottom:30px">
				Q & A 게시판
			</div>
			<!--h3 class="m_3" style="margin-top: 50px; margin-bottom:50px; text-align: center; font-weight: bold;">자유 게시판</h3-->
			<div class="list-group panel panel-default" style="border: 0">	
				<div class="row mhide panel-heading" style="margin:0px;">
					
					<div class="col-sm-1" style="font-weight: 600">번호</div>
					<div class="col-sm-6" style="font-weight: 600">제목</div>
					<div class="col-sm-2" style="font-weight: 600">작성자</div>
					<div class="col-sm-2" style="font-weight: 600">작성일</div>
					<div class="col-sm-1" style="font-weight: 600">조회</div>
				</div>
				
				<!-- 관리자 -->
				<c:forEach var="mdto" items="${mlist }" >
				<div class="row list-group-item" style="margin:0px;">
					<span class="col-sm-1 mhide">
						<img src="../images/${mdto.sa_img }" /> 
					</span>
					<span class="col-sm-6 col-xs-10" style="text-align:left;">
					  <i></i>
					  <a href="board_content.do?b_no=${mdto.b_no}&page=${curpage}">
					  ${mdto.b_title }</a>
					  <c:if test="${mdto.replyCount!=0 }">
					  	<font style="color:#f94b4b;font-size:13px; font-weight:500">
					  	[${mdto.replyCount }]</font>
					  </c:if>
					  <c:if test="${today==mdto.dbday }">
					  <sup><img src="../images/neo_new.gif"></sup>
					  </c:if>
					</span>
					
					<span class="col-sm-2 mhide writer">${mdto.b_id }</span>
					<span class="col-sm-2 mhide date">${mdto.dbday }</span>
					<span class="col-sm-1 mhide hit">${mdto.b_hit }</span>
				</div>
				</c:forEach>
				
				<!-- 회원목록 -->
				<c:forEach var="dto" items="${list }" varStatus="status">
				<div class="row list-group-item" style="margin:0px;">
					<!-- <img src="images/ico-list-notice.gif"> -->
					<span class="col-sm-1">${dto.num }</span>
					<span class="col-sm-6 col-xs-10" style="text-align:left;">
					  <i></i>
					  <a href="board_content.do?b_no=${dto.b_no}&page=${curpage}">
					  ${dto.b_title }</a>
					  <c:if test="${dto.replyCount!=0 }">
					  	<font style="color:#f94b4b;font-size:13px; font-weight:500">
					  	[${dto.replyCount }]</font>
					  </c:if> 
					  <c:if test="${today==dto.dbday }">
					  <sup><img src="../images/neo_new.gif"></sup>
					  </c:if>
					</span>
					
					<span class="col-sm-2 mhide writer">${dto.b_id }</span>
					<span class="col-sm-2 mhide date">${dto.dbday }</span>
					<span class="col-sm-1 mhide hit">${dto.b_hit }</span>
				</div>
				</c:forEach>
				
			</div>		
			
			<div class="row text-right" style="margin:0px;">
			<c:if test="${sessionScope.id!=null }">
			 <!-- <a href="board_insert.do"> -->
				<button type="button" class="btn search-btn" data-toggle="modal" data-target="#writeModal">글쓰기</button>
			<!--  </a> -->
			</c:if>
			<c:if test="${sessionScope.id==null }">
			 <!-- <a href="board_insert.do"> -->
			 <a href="join_login.do">
				<button type="button" class="btn search-btn" data-toggle="modal">글쓰기</button>
			<!--  </a> -->
				</a>
			</c:if>
			</div>	
				
		<div id="writeModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
			    <div class="modal-dialog modal-lg" style="margin:120px auto;">
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			          <h4 class="modal-title" id="gridSystemModalLabel" style="color: #F94B4B; font-weight: bold;">Q & A 게시판 글쓰기</h4>
			        </div>
			        
			        <div class="modal-body">
			          <div class="container-fluid">
			            <form class="form-horizontal" method="post" action="board_insert.do" id="writeForm">
							<div class="form-group">
								<c:if test="${sessionScope.id == 'admin'}" >
									<div >
									<font style="font-weight: 700";>말머리</font>&nbsp;&nbsp;&nbsp;
									<label style="font-size:14px;">
										<input type="radio" name=word value=1>&nbsp; 공지 &nbsp; &nbsp; &nbsp;
										<input type="radio" name=word value=3>&nbsp; 자주 묻는 질문
									</label>
									</div>
								</c:if>
								
								<!-- label for="inputSubject" class="col-sm-2 control-label" style="text-align: left">제목</label-->
								<div class="col-sm-12" style="padding-left: 0; padding-right: 0">
									<input type="text" class="form-control" id="inputSubject" name="b_title"
										placeholder="제목을 입력하세요.">
								</div>
							</div>
							<div class="form-group">
								<div id="summernote"></div>
								<textarea name="b_content" id="summercontent" style="display: none;"></textarea>
							</div>
			          </div>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			          <button type="submit" class="btn btn-primary" id="btnSub">확인</button>
			        </div>
			        </form>
			      </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			  </div><!-- /.modal -->
			
			<div class=text-center>
			   <ul class="pagination">
			    <li>
			      <a href="board_list.do?page=${curpage>1?curpage-1:curpage }" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li class="active"><a href="board_list.do">처음으로</a></li>
			    <li>
			      <a href="board_list.do?page=${curpage<totalpage?curpage+1:curpage }" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			  <ul class="pagination" style="font-size: 12px;">
			  	 <li style="float:right;">${curpage } / ${totalpage }</li>
			  </ul>
			</div>
			
       </div>
       
	</div>
</div>

</body>
</html>