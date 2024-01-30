package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";

	public void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기

			conn = DriverManager.getConnection(url, "book", "book");
		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {

			System.out.println("error:" + e);
		}

	}// getConnection

	private void close() {
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
    //등록
	public int bookInsert(String title, String pubs,String pub_date) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비
			String query = "";
			query += " insert into book ";
			query += " values(null,?,?,?,null) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pub_date);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} // 작가등록끝
		this.close();

		return count;
	}
	//제거
	public int bookDelete(int no) {

		int count = -1;

		this.getConnection();

		try {

			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			count = pstmt.executeUpdate();

			System.out.println(count + "건 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 삭제끝
		this.close();
		return count;

	}
	//북리스트
	public List<BookVo> bookList() {

		// 리스트만들기
		// db에서 데이터가져오기
		// 리스트에 추가하기
		// 리스트 주소 전달하기

		List<BookVo> bookList = new ArrayList<BookVo>();

		// 0. import java.sql.*;

		this.getConnection();
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        pub_date, ";
			query += "        author_id ";
			query += "        author_name, ";
			query += "        author_desc, ";
			query += " from book";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bo = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String date = rs.getString("pub_date");
				int no = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");

				BookVo bookVo = new BookVo(bo, title, pubs,date,no,name,desc);

				bookList.add(bookVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();

		return bookList;

	}// 리스트 끝
	
	
	//수정
	public int bookUpdate(BookVo book) {
		int count = -1;

		this.getConnection();

		try {

			String query = "";
			query += " update author";
			query += " set title = ?";
			query += "     pubs = ?";
			query += "     pub_date = ?";
			query += "     author_id = ?";
			query += " where book_id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,book.getTitle());
			pstmt.setString(2,book.getPubs());
			pstmt.setString(3,book.getPub_date());
			pstmt.setInt(4,book.getAuthorId());
			pstmt.setInt(5,book.getBookId());
			count = pstmt.executeUpdate();

			System.out.println(count + "건 수정 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		
		this.close();
		
		return count;
	}

}
