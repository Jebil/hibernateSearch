package hibernateSearch.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hibernateSearch.dao.BookDao;
import hibernateSearch.model.Author;
import hibernateSearch.model.Book;

@Controller
public class HomeController {

	@Autowired
	BookDao bookDao;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView homeMethod(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		model.addObject("bookList", bookDao.listAllBook());
		return model;
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public @ResponseBody Book addBook(HttpServletRequest req) {
		Book b = new Book();
		Author a = new Author();
		a.setName(req.getParameter("author"));
		b.setAuthor(a);
		b.setLanguage(req.getParameter("language"));
		b.setTitle(req.getParameter("title"));
		bookDao.addBook(b);
		b = bookDao.getBook(req.getParameter("title"));
		return b;
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	public @ResponseBody void deleteBook(@RequestParam int id) {
		Book book = new Book();
		book.setId(id);
		bookDao.deleteBook(book);
	}
}
