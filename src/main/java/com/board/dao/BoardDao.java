package com.board.dao;

import java.util.List;

import com.board.domain.BoardVo;
import com.board.domain.Criteria;
import com.board.domain.SearchCriteria;

public interface BoardDao {
	//게시글 목록
	public List<BoardVo> list() throws Exception;
	
	//게시글 작성
	public void write(BoardVo vo)throws Exception;
	
	//게시글 조회
	public BoardVo view(int bno)throws Exception;
	
	//게시글 수정
	public void modify(BoardVo vo)throws Exception;
	
	//게시글 삭제
	public void delete(int bno) throws Exception;

	
	//게시글 목록+페이징
	public List<BoardVo> listPage(int displayPost, int displayPageNum)throws Exception;
	
	//게시글 총 개수 
	public int listCount() throws Exception;
	
	//목록 +페이징+검색
	public List<BoardVo> listSearch(int displayPost, int displayPageNum
			,String searchType, String keyword)throws Exception;
	
	//검색 결과 갯수
	public int countSearch(SearchCriteria scri)throws Exception;

}
