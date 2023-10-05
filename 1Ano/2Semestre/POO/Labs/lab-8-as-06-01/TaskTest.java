
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskTest {

    Task task,task1;


    @BeforeEach
    public void setUp(){
        task = new Task("Description", 0, LocalDate.now());
        task1 = new Task("Description", 1, LocalDate.now());
    }


    @Test
    public void testConstructor(){
        assertTrue(task.getDescription() != null);
    }

    @Test
    public void testEquals(){
        Task task2 = new Task("Description", 0, LocalDate.now());
        System.out.println(task2.getHeader());
        System.out.println(task2);
        assertEquals(task, task2);
    }

    @Test
    public void testCompareTo(){
        assertEquals(1, task1.compareTo(task));
    }



}
