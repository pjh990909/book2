package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao  = new AuthorDao();
		
		authorDao.authorInsert("서장훈", "농구선수");
		
		authorDao.authorDelete(8);
		authorDao.authorDelete(9);
		authorDao.authorDelete(10);
		
		List<AuthorVo> authorList = authorDao.authorList();
		for(AuthorVo authorVo: authorList) {
			System.out.println(authorVo.getAuthorId()+","+authorVo.getAuthorName()+","+authorVo.getAuthorDesc());
		}
		AuthorVo authorVo = new AuthorVo(1, "", "");
	    authorDao.authorUpdate(authorVo);

	    
	   
	    BookDao bookDao  = new BookDao();
	    
	    List<BookVo> bookList = bookDao.bookList();
	    
	    
	    BookVo bookVo = new BookVo(1,"","","");
	    bookDao.bookUpdate(bookVo);
	    
	}

}
