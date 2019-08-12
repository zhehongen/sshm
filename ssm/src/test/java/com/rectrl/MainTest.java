package com.rectrl;

import com.rectrl.entity.Book;
import com.rectrl.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Test
    public void testQueryAll() throws Exception {
        List<Book> books = bookRepository.queryAll(0, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        long bookId = 1000;
        int update = bookRepository.reduceNumber(bookId);
        System.out.println("update=" + update);
    }


}
