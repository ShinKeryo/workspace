package com.project.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.service.UserService;
import com.myweb.user.service.UserServiceImpl;


@WebServlet("*.customer")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
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
		
		if(path.equals("/new/joinForm.customer")) {
		
			response.sendRedirect("index.jsp");
	}else if(path.equals("new/login.customer")) {
		
		System.out.println(path);
	}else if(path.equals("joinForm.customer")) {
		
		service.customerJoin(request, response);
		
		
	}

}
	
	 
}

