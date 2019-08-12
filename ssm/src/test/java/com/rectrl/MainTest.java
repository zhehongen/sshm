package com.rectrl;

import com.rectrl.entity.Book;
import com.rectrl.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/applicationContext.xml"})
public class MainTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testQueryById() throws Exception {
        long bookId = 1000;
        Book book = bookRepository.queryById(bookId);
        System.out.println(book);
    }


}
