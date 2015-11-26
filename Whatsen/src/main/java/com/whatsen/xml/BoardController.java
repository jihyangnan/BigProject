package com.whatsen.xml;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.template.AddAttributeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

import com.board.dao.*;
@Controller
public class BoardController {
	@Resource(name="mdao")
	private BoardDAO dao;
	
	//사용자 요청 확인
	@RequestMapping("board/board_list.do")
	public String board_list(String page, Model model, HttpServletRequest req)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		HttpSession session = req.getSession();
		session.setAttribute("id", "hong"); // 나중에 지울부분
		
		List<BoardVO> list=dao.boardListData(map);	
		for(BoardVO d:list)
		{
			d.setReplyCount(dao.rwboardCount(d.getB_no()));
		}
		
		List<BoardVO> mlist=dao.boardmListData(map);
		int totalpage=dao.boardTotalPage();
		req.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.addAttribute("mlist",mlist);
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		
		return "board/board_list";
	}
	//추가
	@RequestMapping("board/board_insert.do")
	public String board_insert(BoardVO vo, HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		String b_id=(String)session.getAttribute("id");
		String b_sano=req.getParameter("b_sano");
		vo.setB_id(b_id);
		
		if(b_sano==null){
			vo.setB_sano(3);
		} else {
			vo.setB_no(Integer.parseInt(b_sano));
		}
		dao.boardInsert(vo);
		return "redirect:/board/board_list.do";
	}
	
	//상세보기
	@RequestMapping("board/board_content.do")
	public String board_content(int b_no, int page, String rPage, Model model)
	{
		BoardVO vo=dao.boardContentData(b_no);
		int rw_bno=b_no;
		int type=0;
		if(rPage==null)
		{
			rPage="1";
			type=1;
		}
		int rcurpage=Integer.parseInt(rPage);
		List<Rw_BoardVO> rvo=dao.RwboardListData(rw_bno);
		List<Rw_BoardVO> list=new ArrayList<Rw_BoardVO>();
		int rtotal=dao.rwboardPageTotalpage(rw_bno);
		int j=0;
		int pagecnt=(rcurpage*10)-10;
		for(int i=0; i<rvo.size(); i++)
		{
			Rw_BoardVO dd=rvo.get(i);
			list.add(dd);
			j++;
		}
		model.addAttribute("rlist",list);
		model.addAttribute("dto",vo);
		model.addAttribute("page",page);
		model.addAttribute("rtotal",rtotal);
		model.addAttribute("rcurpage",rcurpage);
		return "board/board_content";
	}
	//수정할 데이터 가져오기
	@RequestMapping("board/board_update.do")
	public String board_update(int b_no, int page, Model model)
	{
		BoardVO vo=dao.boardUpdateData(b_no);
		model.addAttribute("dto",vo);
		model.addAttribute("page",page);
		return "board/board_update";
	}
	//수정완료
	@RequestMapping("board/board_update_ok.do")
	public String board_update_ok(BoardVO vo,int page, Model model)
	{
		dao.boardUpdate(vo);
		int b_no=vo.getB_no();
		/*model.addAttribute(b_no);
		model.addAttribute(page);*/
		return "redirect:/board/board_content.do?b_no="+b_no+"&page="+page;
	}
	//삭제하기
	@RequestMapping("board/board_delete.do")
	public String board_delete(int b_no, int page, Model model)
	{
		dao.boardDelete(b_no);
		model.addAttribute("page",page);
		return "redirect:/board/board_list.do";
	}
	//댓글입력
	@RequestMapping("board/rwboard_new_insert.do")
	public String reboard_insert(Rw_BoardVO rvo,int page, Model model, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		String b_id=(String)session.getAttribute("id");
		String rw_id=(String)session.getAttribute("id");
		rvo.setRw_id(rw_id);	
		int rw_bno=rvo.getRw_bno();
		dao.RwboardInsert(rvo);
		return "redirect:/board/board_content.do?b_no="+rw_bno+"&page="+page;
	}
	
	//댓글 삭제하기
	@RequestMapping("board/rwboard_delete.do")
	public String rwboard_delete(int rw_no, int page, int b_no)
	{
		dao.rwboardDelete(rw_no);
		return "redirect:/board/board_content.do?b_no="+b_no+"&page="+page;
	}
	
	//댓글 수정하기
	@RequestMapping("board/rwboard_update.do")
	public String rwboard_update(Rw_BoardVO rvo,int page, Model model)
	{
		dao.rwboardUpdate(rvo);
		int rw_bno=rvo.getRw_bno();
		/*model.addAttribute(b_no);
		model.addAttribute(page);*/
		return "redirect:/board/board_content.do?b_no="+rw_bno+"&page="+page;
	}

}
