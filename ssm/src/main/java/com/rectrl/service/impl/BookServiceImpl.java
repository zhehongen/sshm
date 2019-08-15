package com.rectrl.service.impl;

import com.rectrl.dto.AppointExecution;
import com.rectrl.entity.Appointment;
import com.rectrl.entity.Book;
import com.rectrl.enums.AppointStateEnum;
import com.rectrl.exception.AppointException;
import com.rectrl.exception.NoNumberException;
import com.rectrl.exception.RepeatAppointException;
import com.rectrl.repository.AppointmentRepository;
import com.rectrl.repository.BookRepository;
import com.rectrl.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入Service依赖
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public Book getById(long bookId) {
        return bookRepository.queryById(bookId);
    }

    @Override
    public List<Book> getList() {
        return bookRepository.queryAll(0, 1000);
    }

    @Override
    @Transactional
    /**
     * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    public AppointExecution appoint(long bookId, long studentId) {
        try {
            // 减库存
            int update = bookRepository.reduceNumber(bookId);
            if (update <= 0) {// 库存不足
                //return new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);//错误写法
                throw new NoNumberException("no number");
            } else {
                // 执行预约操作
                int insert = appointmentRepository.insertAppointment(bookId, studentId);
                if (insert <= 0) {// 重复预约
                    //return new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);//错误写法
                    throw new RepeatAppointException("repeat appoint");
                } else {// 预约成功
                    Appointment appointment = appointmentRepository.queryByKeyWithBook(bookId, studentId);
                    return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
                }
            }
            // 要先于catch Exception异常前先catch住再抛出，不然自定义的异常也会被转换为AppointException，导致控制层无法具体识别是哪个异常
        } catch (NoNumberException e1) {
            throw e1;
        }
        catch (RepeatAppointException e2) {
            throw e2;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译期异常转换为运行期异常
            //return new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);//错误写法
            throw new AppointException("appoint inner error:" + e.getMessage());
        }
    }

}