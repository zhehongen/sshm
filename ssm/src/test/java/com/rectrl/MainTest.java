package com.rectrl;

import com.rectrl.dto.AppointExecution;
import com.rectrl.entity.Appointment;
import com.rectrl.entity.Book;
import com.rectrl.repository.AppointmentRepository;
import com.rectrl.repository.BookRepository;
import com.rectrl.security.CurrentUserHolder;
import com.rectrl.service.BookService;
import com.rectrl.service.ProductService;
import com.rectrl.service.ProductServiceAop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/applicationContext.xml"})
@WebAppConfiguration
public class MainTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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

    @Test
    public void testInsertAppointment() throws Exception {
        long bookId = 1000;
        long studentId = 12345678910L;
        int insert = appointmentRepository.insertAppointment(bookId, studentId);
        System.out.println("insert=" + insert);
    }

    @Test
    public void testQueryByKeyWithBook() throws Exception {
        long bookId = 1000;
        long studentId = 12345678910L;
        Appointment appointment = appointmentRepository.queryByKeyWithBook(bookId, studentId);
        System.out.println(appointment);
        System.out.println(appointment.getBook());
    }

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 12345678910L;
        AppointExecution execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }


    @Autowired
    private ProductService productService;

    @Test(expected = Exception.class)
    public void annoInsertTest() {
        CurrentUserHolder.set("tom");
        productService.delete(1L);
    }

    @Test
    public void adminInsertTest() {
        CurrentUserHolder.set("admin");
        productService.delete(1L);
    }

    @Autowired
    private ProductServiceAop productServiceAop;

    @Test(expected = Exception.class)
    public void annoInsertAopTest() {
        CurrentUserHolder.set("tom");
        productServiceAop.delete(1L);
    }

    @Test
    public void adminInsertAopTest() {
        CurrentUserHolder.set("admin");
        productServiceAop.delete(1L);
    }
}
