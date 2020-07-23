package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVo;
import com.board.domain.Criteria;
import com.board.domain.SearchCriteria;


@Repository
public class BoardDaoImp implements BoardDao {
	
	@Inject
	private SqlSession sql;
	
	private static String namespace="com.board.mappers.board";
	
	//게시글 목록
	@Override
	public List<BoardVo> list() throws Exception {
		
		return sql.selectList(namespace + ".list");
	}
	//게시글 작성
	@Override
	public void write(BoardVo vo) throws Exception {

		 sql.insert(namespace+".write",vo);
		
	}
	//게시글 조회
	@Override
	public BoardVo view(int bno) throws Exception {
		
		return sql.selectOne(namespace+".view",bno);
	}
	//게시글 수정
	@Override
	public void modify(BoardVo vo) throws Exception {
		sql.update(namespace+".modify",vo);
		
	}
	//게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		sql.delete(namespace+".delete",bno);
		
	}
	//게시글 목록+ 페이징
	@Override
	public List<BoardVo>listPage(int displayPost, int displayPageNum) throws Exception {
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		data.put("displayPost", displayPost);
		data.put("displayPageNum", displayPageNum);
		return sql.selectList(namespace+".listPage",data);
	}
	//게시물 총 갯수
	@Override
	public int listCount() throws Exception {
		return sql.selectOne(namespace+".count");
	}
	//목록 페이징 검색
	@Override
	public List<BoardVo> listSearch(int displayPost, int displayPageNum, String searchType, String keyword) throws Exception {
		HashMap<String,Object> data=new HashMap<String,Object>();
		
		data.put("displayPost", displayPost);
		data.put("displayPageNum", displayPageNum);
		
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return sql.selectList(namespace+".listSearch",data);
	}
	//검색 결과 갯수
	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		return sql.selectOne(namespace+".countSearch",scri);
	}


}
