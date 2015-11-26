package com.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.mapred.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sun.research.ws.wadl.Response;

@Repository("mdao")
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	private static SqlSessionFactory ssf;
	
	public List<BoardVO> boardListData(Map map)
	{
		return mapper.boardListData(map);
	}
	
	public List<BoardVO> boardmListData(Map map)
	{
		return mapper.boardmListData(map);
	}
	
	//총페이지 구하기
	public int boardTotalPage()
	{
		int total=0;
		int count=mapper.boardTotalPage();
		total=(int)(Math.ceil(count/10.0));
		return total;
	}
	//글쓰기
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	//상세보기
	public BoardVO boardContentData(int b_no)
	{
		mapper.boardHitIncrement(b_no);
		return mapper.boardContentData(b_no);
	}
	//수정할 값 가져오기
	public BoardVO boardUpdateData(int b_no)
	{
		return mapper.boardContentData(b_no);
	}
	//수정완료
	public void boardUpdate(BoardVO vo)
	{
		mapper.boardUpdate(vo);
	}
	//삭제
	public void boardDelete(int b_no)
	{
		mapper.boardDelete(b_no);
	}
	//댓글 조회
	public List<Rw_BoardVO> RwboardListData(int rw_bno)
	{
		return mapper.RwBoardListData(rw_bno);
	}
	//댓글 총 조회
	public int rwboardCount(int rw_bno)
	{
		int count=mapper.RwboardCount(rw_bno);
		return count;
	}
	//댓글 입력
	public void RwboardInsert(Rw_BoardVO rvo)
	{
		mapper.RwboardInsert(rvo);
	}
	//댓글 삭제
	public void rwboardDelete(int rw_no)
	{
		mapper.rwboardDelete(rw_no);
	}
	//댓글 수정
	public void rwboardUpdate(Rw_BoardVO rvo)
	{
		mapper.rwboardUpdate(rvo);
	}
	
	//댓글 페이지
	public int rwboardPageTotalpage(int rw_bno)
	{
		int count=mapper.RwboardTotalCount(rw_bno);
		return count;		
	}

}
