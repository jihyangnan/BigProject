package com.board.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {
	//��� ���
	//ȸ��
	@Select("SELECT b_sano,b_no,b_id,b_title,dbday,b_hit,num "
			+"FROM(SELECT b_sano,b_no,b_id,b_title,TO_char(b_date,'YYYY-MM-DD')as dbday,b_hit,rownum as num "
			+"FROM(SELECT b_sano,b_no,b_id,b_title,b_date,b_hit "
			+"FROM ws_board WHERE b_sano=3 ORDER BY b_no DESC)) "
			+"WHERE b_sano=3 AND num BETWEEN #{start} AND #{end}")		
	   public List<BoardVO> boardListData(Map map);
	//������
	@Select("SELECT a.b_no,a.b_id,a.b_title,a.b_content,a.b_sano,b.sa_img,dbday,a.b_hit,num "
			+"FROM(SELECT b_no,b_id,b_title,b_content,To_char(b_date,'YYYY-MM-DD')as dbday,b_hit,b_sano,rownum as num "
			+"FROM (SELECT b_no, b_id, b_title, b_content,b_date, b_hit, b_sano "
            +"FROM ws_board "
            +"WHERE b_sano not in(3) ORDER BY b_no desc)) a JOIN ws_word b ON a.b_sano = b.sa_no "
            +"WHERE num BETWEEN 1 AND 5")
     	public List<BoardVO> boardmListData(Map map);
	
	//�������� ���ϱ�
	@Select("SELECT COUNT(*) FROM ws_board WHERE b_sano=3")
		public int boardTotalPage();
	
	//�۾���
	@SelectKey(keyProperty="b_no",before=true,resultType=int.class,
			statement="SELECT NVL(MAX(b_no)+1,1) as b_no FROM ws_board")
	@Insert("INSERT INTO ws_board VALUES(#{b_no},#{b_id},#{b_title},#{b_content},0,0,SYSDATE,#{b_sano})")
		public void boardInsert(BoardVO vo);
		
	//�󼼺���
	@Update("UPDATE ws_board SET b_hit=b_hit+1 WHERE b_no=#{b_no}")
	   public void boardHitIncrement(int b_no);
	@Select("SELECT b_no,b_id,b_title,b_date,b_hit,b_content FROM ws_board WHERE b_no=#{b_no}")
		public BoardVO boardContentData(int b_no);
	   
	//����
	@Update("UPDATE ws_board SET b_title=#{b_title},b_content=#{b_content},b_date=SYSDATE "
			+ "WHERE b_no=#{b_no}")
		public void boardUpdate(BoardVO vo);
	//����
	@Delete("DELETE FROM ws_board WHERE b_no=#{b_no}")
		public void boardDelete(int b_no);
	//��� �� ��ȸ
	@Select("SELECT COUNT(*) FROM rw_board WHERE rw_bno=#{rw_bno}")
		public int RwboardCount(int rw_bno);
	//��� ��ȸ
	@Select("SELECT rw_no,rw_id,rw_content,TO_CHAR(rw_date,'YYYY-MM-DD HH24:MI:SS') as dbday,group_tab,depth "
			+"FROM rw_board "
			+"WHERE rw_bno=#{rw_bno} ORDER BY group_id DESC, group_step ASC")
		public List<Rw_BoardVO> RwBoardListData(int rw_bno);
	//��� �Է�
	@SelectKey(keyProperty="rw_no",before=true,resultType=int.class,
			statement="SELECT NVL(MAX(rw_no)+1,1) as rw_no FROM rw_board")
	@Insert("INSERT INTO rw_board VALUES(#{rw_no},0,#{rw_content},#{rw_id},SYSDATE,#{rw_bno},"
			+"(SELECT NVL(MAX(group_id)+1,1) FROM rw_board),0,0,0,0)")
		public void RwboardInsert(Rw_BoardVO rvo);
	//��� ����
	@Delete("DELETE FROM rw_board WHERE rw_no=#{rw_no}")
		public void rwboardDelete(int rw_no);
	
	//��� ����
	@Update("UPDATE rw_board SET rw_content=#{rw_content}, rw_date=SYSDATE "
			+ "WHERE rw_no=#{rw_no}")
		public void rwboardUpdate(Rw_BoardVO rvo);
	//��� ������
	@Select("SELECT COUNT(*) FROM rw_board WHERE rw_bno=#{rw_bno}")
		public int RwboardTotalCount(int rw_bno);
}
