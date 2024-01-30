package com.javaex.ex01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao  = new AuthorDao();
		
		int cnt = authorDao.authorInsert("1234", "1234");
		System.out.println(cnt + " success");
		
		List<AuthorVo> authorList = authorDao.authorList();
	    for(AuthorVo authorVo : authorList) {
	    	int id = authorVo.getAuthorId();
	    	String name = authorVo.getAuthorName();
	    	String desc = authorVo.getAuthorDesc();
	    	System.out.println(id+",   "+name+",   "+desc);
	    }
		
		/*
		for(int i=0;i<authorList.size();i++) {
			int id = authorList.get(i).getAuthorId();
			String name = authorList.get(i).getAuthorName();
			String desc = authorList.get(i).getAuthorDesc();
			System.out.println(id+",   "+name+",   "+desc);
		}
		*/
		System.out.println(authorList.size() + "명의 작가가 등록되었습니다.");
		//authorDao.authorInsert("1234", "1234");
		
		//authorDao.authorDelete(7);
		
		

	}

}
