package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 작가 등록
	public int authorInsert(String name, String desc) {

		int count = -1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null,?,?) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		} // 작가등록끝
		return count;
		
	}

	// 작가 삭제
	public int authorDelete(int no) {

		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			count = pstmt.executeUpdate();

			System.out.println(count + "건 삭제 되었습니다.");

		} catch (

		ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		} // 삭제끝
		return count;
	}
	// 작사리스트

	public List<AuthorVo> authorList() {

		
		//리스트만들기
		//db에서 데이터가져오기
		//리스트에 추가하기
		//리스트 주소 전달하기
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// 0. import java.sql.*;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(no,name,desc);
				
				authorList.add(authorVo);
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		return authorList;

	}//리스트 끝

}
