package com.project.customer.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.costomer.model.CustomerDAO;
import com.project.costomer.model.CustomerVO;

public class ServiceImpl implements Service {

	CustomerDAO dao = CustomerDAO.getInstance();

	@Override
	public int customerJoin(HttpServletRequest request, HttpServletResponse response) {
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		
		int result = dao.idCheck(id);
		
		if(result == 1 ) {
			return 1;
			
		}else {
			
			CustomerVO vo = new CustomerVO(id,name,age,email,address,gender);
			
			vo.set
			
		}
		
		return 0;
	}

	
	
	
}
