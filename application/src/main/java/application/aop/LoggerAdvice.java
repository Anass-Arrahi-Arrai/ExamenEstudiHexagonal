package application.aop;

import cat.tecnocampus.rooms.application.dtos.ClassroomDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);


    //A pointcut that matches methods with a single parameter of type StudentDTO
    @Pointcut("execution(* cat.tecnocampus.rooms.application.RoomController.*(cat.tecnocampus.rooms.application.dtos.StudentDTO))")
    public void studentDTOParameter() {
    }

    //Before advice of a pointcut
    @Before("studentDTOParameter()")
    public void newStudent() {
        logger.info("Creating a new student (before)");
    }

    //A pointcut that matches all methods finishing with the word "Classrooms"
    @Pointcut("execution(* cat.tecnocampus.rooms.application.RoomController.*Classrooms(..))")
    public void pointcutClassRooms() {
    }

    //Before advice of a pointcut
    @After("pointcutClassRooms()")
    public void adviceClassRooms() {
        logger.info("Working with classrooms (after)");
    }

    //A pointcut that matches all methods returning a ClassRoomDTO
    @Pointcut("execution(public cat.tecnocampus.rooms.application.dtos.ClassroomDTO cat.tecnocampus.rooms.application.RoomController.*(..)) && args(name)")
    public void getAClassroom(String name) {
    }

    //Around advice. Note that this method must return what the proxied method is supposed to return
    @Around("getAClassroom(name)")
    public ClassroomDTO dealRequestParam(ProceedingJoinPoint jp, String name) throws Throwable {

        try {
            logger.info("Before getting the classroom: " + name);
            ClassroomDTO res = (ClassroomDTO) jp.proceed();
            logger.info("Already got the classroom: " + name);
            return res;
        } catch (Throwable e) {
            logger.info("Something went wrong when the classroom with name: " + name);
            throw e;
        }
    }
}
