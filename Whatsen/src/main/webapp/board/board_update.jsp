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
<script type="text/javascript">
	$(document).ready(function() {
		$('#summernote').summernote({
			onBlur : function(e) {
				var str = $('#summernote').code();
				console.log(str);
				//str = str.replace("&", "&amp;");
				str = str.replace(/&/g, "&amp;");
	            $('#summercontent').html(str);
	        },
	        height : 450,
	        minHeight: null,
	        maxHeight: null,
	        lang: 'ko-KR'
	    });
		$('span.note-current-fontsize').text(12);
		$('div.note-editable p').css('font-size', '12px');
	});
	$(function(){
	$('#ubtnSub').click(function(){
			$('#writeForm').submit();
		});
	$('#ubtnCancel').click(function(){
		history.back();	
	});
	});
</script>
</head>
<body>
<div class="about_top inquire">
    <div class="container">
			<div style="border-top: 1px solid #E2E2E2; border-bottom: 1px solid #E2E2E2;">
				<div class="text-right">
					<ol class="breadcrumb" style="background-color: #fff">
					</ol>
				</div>
				<div class="jumbotron" style="background-color: #fff">
					<h3 style="padding-bottom:20px; font-size: 23px; font-weight: 600; color:#f94b4b;">
					Q & A 게시판 수정하기</h3>
					
					<form class="form-horizontal" method="post" id="writeForm" action="board_update_ok.do">
					    <input type="hidden" name="b_no" value="${dto.b_no}" />
					    <input type="hidden" name="page" value="${requestScope.page}" />
					    <div class="form-group ">
					      <label for="subject" class="control-label col-sm-1">제목</label>
					      <div class="col-sm-11" >
					      	<input type="text" name="b_title" class="form-control" id="subject" value=${dto.b_title }>
					      </div>
					    </div>
						<div class="form-group" style="margin-bottom: 0">
						  
							<div id="summernote">
							${dto.b_content }
							</div>
						<textarea name="b_content" id="summercontent" style="display: none;"></textarea>
						</div>
						<div class="form-group" style="text-align: center;" >
							<button type="button" class="btn btn-primary" id="ubtnSub">확인</button>
							<button type="button" class="btn btn-default" id="ubenCancel">취소</button>
							<div style="clear:both;"></div>
						</div>
					</form>

				</div>

			</div>
			
	</div>
</div>
			
</body>
</html>