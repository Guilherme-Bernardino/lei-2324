import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListOfElementsTest {
    ListOfElements listOfElements;

    @BeforeEach
    public void setUp(){
        listOfElements = new ListOfElements(ListOfElements.HEADER);
    }


    @Test
    public void testAdd(){
        Task t1 = new Task("Study for POO MT3", 3, LocalDate.of(2023,6,20));
        Task t2 = new Task("Do POO project phase 2", 2, LocalDate.of(2023,6,24));
        Task t3 = new Task("Do POO project phase 1", 1, LocalDate.of(2023,5,29));
        Task t4 = new Task("Study for POO test", 2, LocalDate.of(2023, 6, 4));
        listOfElements.addAll(Arrays.asList(t1, t2, t3, t4));


        System.out.println(listOfElements.showSortedByNaturalOrder());
        System.out.println(listOfElements.showSortedByOrderableComparator());

        assertTrue(!listOfElements.isEmpty());
    }
}
