package com.board.controller;

import java.util.List;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVo;
import com.board.domain.Criteria;
import com.board.domain.PageMaker;
import com.board.domain.ReplyVo;
import com.board.domain.SearchCriteria;
import com.board.service.BoardService;
import com.board.service.ReplyService;
import com.mysql.jdbc.log.Log;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	@Inject
	ReplyService RepService;
	//게시글 목록
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void getList(Model model)throws Exception{
		List<BoardVo> list=null;
		list=service.list();
		model.addAttribute("list",list);
	}
	//게시글 목록+페이징 추가
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num")int num) throws Exception{
		
//		//게시글 총 개수
//		int count=service.count();
//		
//		//한 페이지에서 출력한 게시물 갯수
//		int postNum=10;
//		
//		//하단 페이징 번호([게시글 총 갯수/한 페이지에 출력한 갯수 ]의 올림)
//		int pageNum=(int)Math.ceil((double)count/postNum);
//		
//		//출력할 게시물
//		int displayPost=(num-1)*postNum;
//		
//		//한번에 표시한 페이징 번호의 개수
//		int pageNum_cnt=10;
//		
//		//표시되는 페이지 번호 중 마지막 번호
//		int endPageNum=(int)(Math.ceil((double)num/(double)pageNum_cnt)*pageNum_cnt);
//		
//		//표시되는 페이지번호 중 첫번째 번호
//		int startPageNum=endPageNum-(pageNum_cnt-1);
//		
//		//마지막 번호 재계산
//		int endPageNum_tmp=(int)(Math.ceil((double)count/(double)pageNum_cnt));
//		
//		if(endPageNum>endPageNum_tmp){
//			endPageNum=endPageNum_tmp;
//		}
//		
//		boolean prev=startPageNum==1?false:true;
//		boolean next=endPageNum*pageNum_cnt>=count?false:true;
//		
//		
//		List list=null;
//		list=service.listPage(displayPost, postNum);
//		
//		model.addAttribute("list",list);
//		model.addAttribute("pageNum",pageNum);
//		
//		//시작 및 끝 번호
//		model.addAttribute("startPageNum",startPageNum);
//		model.addAttribute("endPageNum",endPageNum);
//		
//		//이전 및 다음
//		model.addAttribute("prev",prev);
//		model.addAttribute("next",next);
//		
//		//현재 페이지
//		model.addAttribute("select",num);
		

		
		PageMaker pageMaker =new PageMaker();
		pageMaker.setNum(num);
		pageMaker.setTotalCount(service.count());
		
		List<BoardVo> list=null;
		list=service.listPage(pageMaker.getDisplayPost(),pageMaker.getDisplayPageNum());
		
		model.addAttribute("list",list);
//		model.addAttribute("pageNum",pageMaker.getPageNum());
//		
//		model.addAttribute("startPage",pageMaker.getStartPage());
//		model.addAttribute("endPage",pageMaker.getEndPage());
//		
//		model.addAttribute("prev",pageMaker.getPrev());
//		model.addAttribute("next",pageMaker.getNext());
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("select",num);
	
	}
	//글 목록+페이징+검색
	@RequestMapping(value="/listSearch",method=RequestMethod.GET)
	public void listPage(Model model,@RequestParam("num")int num
			,@RequestParam(value="searchType",required=false,defaultValue="title")String searchType
			,@RequestParam(value="keyword",required=false,defaultValue="")String keyword) throws Exception{

		PageMaker pageMaker=new PageMaker();
		pageMaker.setNum(num);
		pageMaker.setTotalCount(service.count());
		
		List<BoardVo> list=null;
		list=service.listSearch(pageMaker.getDisplayPost(), pageMaker.getDisplayPageNum(), searchType, keyword);
		
		model.addAttribute("list",list);
		
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("select",num);
	}
	
	//게시글 작성
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void getWrite() throws Exception{
		
	}
	//게시글 작성
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String postWrite(BoardVo vo)throws Exception{
		service.write(vo);
		return "redirect:/board/list";
	}
	//댓글 작성
	@RequestMapping(value="/replyWrtie",method=RequestMethod.POST)
	public String replyWrite(ReplyVo replyVo,SearchCriteria scri,RedirectAttributes rttr) throws Exception{

		
		RepService.create(replyVo);
		
		rttr.addAttribute("bno",replyVo.getBno());
		rttr.addAttribute("page",scri.getPage());
		rttr.addAttribute("perPageNum",scri.getPerPageNum());
		rttr.addAttribute("serchType",scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());
		
		return "redirect:/board/listPage";
	}
	//게시글 조회
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public void getView(@RequestParam("bno")int bno,Model model) throws Exception{
		BoardVo vo =service.view(bno);
		
		model.addAttribute("view",vo);
		List<ReplyVo> repList=RepService.list(bno);
		model.addAttribute("repList",repList);
		
	}
	//게시글 수정
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void getModify(@RequestParam("bno")int bno,Model model) throws Exception{
		BoardVo vo =service.view(bno);
		
		model.addAttribute("view",vo);
	}
	//게시글 수정
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String postModify(BoardVo vo)throws Exception{
		service.modify(vo);
		return "redirect:/board/view?bno="+vo.getBno();
	}
	//게시글 삭제
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String getDelete(@RequestParam("bno")int bno) throws Exception{
		service.delete(bno);
		return "redirect:/board/listPage?num=1";
	}

}
