package hibernateSearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hibernateSearch.dao.BookDao;
import hibernateSearch.model.Book;

@Controller
public class SearchController {

	@Autowired
	BookDao bookDao;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getSearchPage() {
		return "search";
	}

	@RequestMapping(value = "/getSearch", method = RequestMethod.POST)
	public @ResponseBody List<Book> getSearchResults(
			@RequestParam String query) {
		System.out.println(query+"******************************");
		return bookDao.searchBook(query);
	}
}
