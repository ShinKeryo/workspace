package com.myweb.board.service;

import java.net.http.HttpRequest;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class BoardServiceImpl implements BoardService {

	//dao
	private BoardDAO dao = BoardDAO.getInstance();
	
	@Override
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		dao.insert(writer, title, content);
		
	}

	@Override
	public ArrayList<BoardVO> getList(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<BoardVO> list = dao.getList();
		
		return list;
	}

	@Override
	public BoardVO getContent(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		BoardVO vo = dao.getContent(bno);
		
		return vo;
	}

	@Override
	public int update(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setTitle(title);
		vo.setContent(content);
		
		
		int result = dao.update(vo);
		
		return result;
		
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		dao.delete(bno);
		
	}

	@Override
	public void hitUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		//쿠키 or 세션을 사용해서 조회수 중복을 막자
		//Cookie coo = new Cookie(키,값);
		//coo.setMaxAge(30)
		//reponse.addCookie(coo)
		String bno = request.getParameter("bno");
		
		String cooValue = "";
		boolean flag = true; //if문 의 실행여부 	
		//기존 쿠키가 있었는지 확인
		Cookie[] arr = request.getCookies();
		
		if(arr != null) {
			
			for(Cookie c :arr) {
				
				if(c.getName().equals("hit")) { //hit 쿠키가 있다.
					cooValue=c.getValue(); //쿠키의 값을 저장
					
					if(c.getValue().contains(bno)) {
						System.out.println(true);
						flag=false;
					}
				}
			}
		}
		
		if(flag) { //if문을 실행 안했음
			dao.hitUpdate(bno);
			cooValue += bno+"-";
		}
		
		Cookie coo = new Cookie("hit", bno + "-");
		coo.setMaxAge(30);
		response.addCookie(coo);
	}
}
