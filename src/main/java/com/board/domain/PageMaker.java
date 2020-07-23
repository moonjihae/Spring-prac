package com.board.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	//현재 페이지 번호
	private int num;
	//게시물 총 갯수
	private int totalCount;
	//한 페이지에 출력한 게시물 갯수
	private int displayPageNum=10;
	//하단 페이징 번호 ([게시물 총 개수/한 페이지에 출력한 갯수]의 올림)
	private int pageNum;
	//출력할 게시물
	private int displayPost;
	//한번에 표시할 페이징 번호의 갯수
	private int pageNumCnt=10;
	//표지되는 페이지 번호 중 마지막 번호
	private int endPage;
	//표시되는 페이지 번호 중 첫번째 번호
	private int startPage;
	// 다음/이전 표시 여부
	private boolean prev;
	private boolean next;
	

	
	private Criteria cri;
	
	//setter 필요한건 num이랑 TotalCount 뿐
	public void setNum(int num) {
		this.num=num;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public int getPageNum() {
		return pageNum;
		
	}
	
	public int getPageNumCnt() {
		return pageNumCnt;
	}
	
	public int getDisplayPost() {
		return displayPost;
	}
	
	public int getStartPage() {
		return startPage;
	}


	public int getEndPage() {
		return endPage;
	}

	public boolean getPrev() {
		return prev;
	}

	public boolean getNext() {
		return next;
	}



	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	//
	private void calcData() {
		//마지막 번호
		  endPage = (int) (Math.ceil(num / (double)pageNumCnt) * pageNumCnt);
		//시작 번호
		  startPage = endPage - (pageNumCnt-1);
		//마지막 번호 재계산
		  int tempEndPage = (int) (Math.ceil((double)totalCount / (double)pageNumCnt));
		  
		  if (endPage > tempEndPage)
		  {
		   endPage = tempEndPage;
		  }
		  prev = startPage == 1 ? false : true;
		  next = endPage * pageNumCnt >= totalCount ? false : true;
		  
		  displayPost=(num-1)*displayPageNum;
		 }
	
	public String makeQuery(int page) {
		UriComponents uriComponents=
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page) {
		UriComponents uriComponents=
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum",cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword",encoding(((SearchCriteria)cri).getKeyword()))
				.build();
		return uriComponents.toUriString();
	}
	
	private String encoding(String keyword) {
		if(keyword==null|| keyword.trim().length()==0)
			return "";
		try {
			return URLEncoder.encode(keyword,"UTF-8");
			
		}catch(UnsupportedEncodingException e) {
			return "";}
	}
}