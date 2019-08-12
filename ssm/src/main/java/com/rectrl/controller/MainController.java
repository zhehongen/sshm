package com.rectrl.controller;

import com.rectrl.entity.Book;
import com.rectrl.repository.BookRepository;
import com.rectrl.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        //实际返回的是views/test.jsp ,spring-mvc.xml中配置过前后缀
        return "test";
    }


    @Autowired
    private TestService testService;

    @RequestMapping(value = "springtest", method = RequestMethod.GET)
    public String springTest() {
        return testService.test();
    }

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("testQueryById")
    @ResponseBody
    public Book testQueryById() throws Exception {
        long bookId = 1000;
        Book book = bookRepository.queryById(bookId);

        System.out.println(book);
        return book;
    }
}