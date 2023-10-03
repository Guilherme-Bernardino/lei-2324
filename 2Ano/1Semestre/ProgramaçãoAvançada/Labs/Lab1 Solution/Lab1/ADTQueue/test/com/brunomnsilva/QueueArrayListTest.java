package com.brunomnsilva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class QueueArrayListTest {

    private Queue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new QueueLinkedList<>();
    }

    @Test
    public void size_isCorrect_AfterEnqueueDequeue() {
        assertEquals(0, queue.size(), "size is not correct!");

        queue.enqueue(1);
        assertEquals(1, queue.size(), "size is not correct!");

        queue.enqueue(2);
        assertEquals(2, queue.size(), "size is not correct!");

        queue.enqueue(3);
        assertEquals(3, queue.size(), "size is not correct!");

        queue.dequeue();
        assertEquals(2, queue.size(), "size is not correct!");

        queue.dequeue();
        queue.dequeue();
        assertEquals(0, queue.size(), "size is not correct!");
    }

    @Test
    public void front_isCorrect_afterEnqueueDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals((Integer)1, queue.front(),"front element is not correct!");

        queue.dequeue();
        assertEquals((Integer)2, queue.front(),"front element is not correct!");

        queue.dequeue();
        assertEquals((Integer)3, queue.front(),"front element is not correct!");
    }

    @Test
    public void front_Exception_WhenEmpty() {
        assertThrows(QueueEmptyException.class, () -> {
            queue.front();
        });
    }
}