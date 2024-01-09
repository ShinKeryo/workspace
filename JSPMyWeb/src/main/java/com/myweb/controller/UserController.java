  package com.myweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserVO;
import com.myweb.user.service.UserService;
import com.myweb.user.service.UserServiceImpl;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UserController() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(request.getContextPath().length());
		
		System.out.println(path);
		
		//서비스 영역 선언
		UserService service = new UserServiceImpl();
		
		if(path.equals("/user/join.user")) { //가입화면
			
			//화면으로 이동 기본값 - forward 형식이 되어야함
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
		
		}else if(path.equals("/user/login.user")) { //로그인화면
			
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
			
		}else if(path.equals("/user/joinForm.user")){ //회원가입
			
			int result = service.join(request, response);
			
			if(result==1) { //아이디중복
				request.setAttribute("msg","아이디가 중복되었습니다");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
				
			}else {//회원가입성공
				//response.sendRedirect("user_login.jsp"); //절반만 맞다.
				response.sendRedirect("login.user"); //MVC2 방식에서 리다이렉트는 다시 컨트롤러를 태우는데 사용함;
			}
			
		}else if (path.equals("/user/loginForm.user")) { //로그인 성공
				
				UserVO vo = service.login(request, response);
				
				if(vo != null) {//로그인 성공
					
					
					//서블릿에서는 request.getSession 현재세션을 얻을수있다.
					HttpSession session = request.getSession();
					session.setAttribute("user_id", vo.getId());
					session.setAttribute("user_name", vo.getName());
					
					response.sendRedirect(request.getContextPath()); //홈화면
					
				}else {
					
					request.setAttribute("msg", "아이디 비밀번호를 확인하세요");
					request.getRequestDispatcher("user_login.jsp").forward(request, response);
				}
				
			}else if(path.equals("/user/logout.user")) {//로그아웃
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect(request.getContextPath());
				
			}else if(path.equals("/user/mypage.user")) { //마이페이지 화면
				
				request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
			}else if(path.equals("/user/update.user")) { //정보수정화면
					
				//여기에서 회원의 기존 데이터를 가지고 화면으로 나감
				
				/*
				 * 1. DAO에서는 id기준으로 회원정보를 조회해서 UserVO저장
				 * 2. service 영역에서는 리턴해서 컨트롤러까지 회원정보를 가지고나옴
				 * 3. 컨트롤러에서는 vo를 request에 저장합니다.
				 * 4. 화면에서 EL태그를 사용해서 value속성에서 찍어 줍니다
				 */
				
				UserVO vo = service.getUserInfo(request, response);
				
				
				request.setAttribute("vo",vo );
				request.getRequestDispatcher("user_update.jsp").forward(request, response);
				
			}else if (path.equals("/user/updateForm.user")) {//회원정보 수정
				
				int result = service.upDate(request,response);
				System.out.println(result);
				
				if(result == 1) {
					//세션의 
					
					
					//브라우저 화면에 직접 응답을 해주는 형태
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();				
					out.println("<script>");
					out.println("alert('업데이트에 성공했습니다.');");
					out.println("location.href ='mypage.user';");
					out.println("</script>");
					
				}else {
					
					response.sendRedirect("mypage.user");
				}
				
			}else if (path.equals("/user/delete.user")) {
				
				//탈퇴화면
				request.getRequestDispatcher("user_delete.jsp").forward(request, response);
			}else if(path.equals("/user/deleteForm.user")) {//회원 탈퇴 요청
				
				/*
				 * 1. service영역의 delete메서드로 연결합니다.
				 * 2. service에서는 먼저 login메서드를 이용해서 회원의 정보를 조회해서 가지고 나온다.
				 * 3. 회원이 있다는 것은 비밀번호가 일치한다.
				 * 4. delete메서드를 호출시켜서 회원정보를 삭제하고, 세션을도 삭제, 홈화면으로 리다이렉트
				 * 5. 비밀번호가 불일치시 delete.jsp화면으로 메시지를 보내주세요
				 */
				
				int result = service.delete(request, response);
				
				if(result ==1 ) {//삭제성공
					
					
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();				
					out.println("<script>");
					out.println("alert('회원 탈퇴처리 되었습니다.');");
					out.println("location.href ='/JSPMyWeb';");
					out.println("</script>");
					
					
					
				}else {//삭제실패
					
					request.setAttribute("msg", "비밀번호를 확인하세요");
					request.getRequestDispatcher("user_delete.jsp").forward(request, response);
				}
				
			}
			
			
		}
		
	}