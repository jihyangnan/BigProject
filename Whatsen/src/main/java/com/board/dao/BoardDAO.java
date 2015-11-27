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
	
	//�������� ���ϱ�
	public int boardTotalPage()
	{
		int total=0;
		int count=mapper.boardTotalPage();
		total=(int)(Math.ceil(count/10.0));
		return total;
	}
	//�۾���
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	//�󼼺���
	public BoardVO boardContentData(int b_no)
	{
		mapper.boardHitIncrement(b_no);
		return mapper.boardContentData(b_no);
	}
	//������ �� ��������
	public BoardVO boardUpdateData(int b_no)
	{
		return mapper.boardContentData(b_no);
	}
	//�����Ϸ�
	public void boardUpdate(BoardVO vo)
	{
		mapper.boardUpdate(vo);
	}
	//����
	public void boardDelete(int b_no)
	{
		mapper.boardDelete(b_no);
	}
	//��� ��ȸ
	public List<Rw_BoardVO> RwboardListData(int rw_bno)
	{
		return mapper.RwBoardListData(rw_bno);
	}
	//��� �� ��ȸ
	public int rwboardCount(int rw_bno)
	{
		int count=mapper.RwboardCount(rw_bno);
		return count;
	}
	//��� �Է�
	public void RwboardInsert(Rw_BoardVO rvo)
	{
		mapper.RwboardInsert(rvo);
	}
	//��� ����
	public void rwboardDelete(int rw_no)
	{
		mapper.rwboardDelete(rw_no);
	}
	//��� ����
	public void rwboardUpdate(Rw_BoardVO rvo)
	{
		mapper.rwboardUpdate(rvo);
	}
	
	//��� ������
	public int rwboardPageTotalpage(int rw_bno)
	{
		int count=mapper.RwboardTotalCount(rw_bno);
		return count;		
	}

}
