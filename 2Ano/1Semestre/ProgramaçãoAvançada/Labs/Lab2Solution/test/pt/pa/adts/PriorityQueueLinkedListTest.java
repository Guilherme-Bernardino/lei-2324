package pt.pa.adts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//nivel 1 e 2
class PriorityQueueLinkedListTest {

    private PriorityQueueLinkedList<Integer> queue;

    private Integer[] elements = {
            new Integer(3),
            new Integer(3),
            new Integer(1),
            new Integer(8),
            new Integer(3)
    };

    @BeforeEach
    void setUp() {
        queue = new PriorityQueueLinkedList<>();
    }

    @Test
    public void isEmpty_isCorrect_AfterEnqueueDequeue() {
        assertTrue(queue.isEmpty(), "isEmpty is not correct, because should be TRUE!");

        queue.enqueue(elements[0]);
        assertFalse(queue.isEmpty(), "isEmpty is not correct, because should be FALSE!");

        queue.enqueue(elements[1]);
        assertFalse(queue.isEmpty(), "isEmpty is not correct, because should be FALSE!");

        queue.dequeue();
        assertFalse(queue.isEmpty(), "isEmpty is not correct, because should be FALSE!");

        queue.dequeue();
        assertTrue(queue.isEmpty(), "isEmpty is not correct, because should be TRUE!");
    }

    @Test
    public void size_isCorrect_AfterEnqueueDequeue() {
        assertEquals(0, queue.size(), "size is not correct!");

        queue.enqueue(elements[0]);
        assertEquals(1, queue.size(), "size is not correct!");

        queue.enqueue(elements[1]);
        assertEquals(2, queue.size(), "size is not correct!");

        queue.enqueue(elements[2]);
        assertEquals(3, queue.size(), "size is not correct!");

        queue.dequeue();
        assertEquals(2, queue.size(), "size is not correct!");

        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size(), "size is not correct!");
    }

    @Test
    public void front_isCorrect_afterEnqueueDequeue() {
        queue.enqueue(elements[0]);
        queue.enqueue(elements[2]);
        queue.enqueue(elements[3]);

        assertSame(elements[3], queue.front(),"front element is not correct!");

        queue.dequeue();
        assertSame(elements[0], queue.front(),"front element is not correct!");

        queue.dequeue();
        assertSame(elements[2], queue.front(),"front element is not correct!");
    }

    @Test
    public void front_Exception_WhenEmpty() {
        assertThrows(QueueEmptyException.class, () -> {
            queue.front();
        });
    }
}