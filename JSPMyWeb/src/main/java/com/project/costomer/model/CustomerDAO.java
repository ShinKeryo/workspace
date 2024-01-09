package com.project.costomer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;


public class CustomerDAO {
	private DataSource dataSource;	
	private static CustomerDAO dao = new CustomerDAO();
	
	private CustomerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static CustomerDAO getInstance() {
		return dao;
	}
	
	public int idCheck(String id) {
		int result =0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from customer where customerid = ?";
		
		try {
			
			conn = dataSource.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = 1;
			}else {
				result = 0;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	
		
		
		
		return result;
	}
	
}
