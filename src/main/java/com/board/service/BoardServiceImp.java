package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.BoardDao;
import com.board.domain.BoardVo;
import com.board.domain.Criteria;
import com.board.domain.SearchCriteria;

@Service
public class BoardServiceImp implements BoardService {
	@Inject
	private BoardDao dao;
	
	//게시글 목록
	@Override
	public List<BoardVo> list() throws Exception {
		
		return dao.list();
	}
	//게시글 작성
	@Override
	public void write(BoardVo vo) throws Exception {
		dao.write(vo);
		
	}
	//게시글 조회 
	@Override
	public BoardVo view(int bno) throws Exception {
		
		return dao.view(bno);
	}
	
	//게시글 수정
	@Override
	public void modify(BoardVo vo) throws Exception {
		dao.modify(vo);
		
	}
	
	//게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
		
	}
	//게시글 총 개수
	@Override
	public int count() throws Exception {
		
		return dao.listCount();
	}
	//게시글 목록+페이징
	@Override
	public List<BoardVo> listPage(int displayPost, int displayPageNum) throws Exception {
		
		return dao.listPage(displayPost,displayPageNum);
	}
	
	//목록 페이징 검색
	@Override
	public List<BoardVo> listSearch(int displayPost,int displayPageNum,String searchType,String keyword) throws Exception {
		return dao.listSearch(displayPost,displayPageNum,searchType,keyword);
	}
	//검색 결과 갯수
	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		return dao.countSearch(scri);
	}

}
