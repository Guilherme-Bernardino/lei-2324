package com.brunomnsilva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueLinkedListTest {
    private Queue<Integer> q;

    @BeforeEach
    void setUp() {
        q= new QueueLinkedList<>();
    }


    @Test
    @DisplayName("Test is empty")
    void testIsEmptyClearTrue(){
        q.enqueue(1);
        q.enqueue(3);
        q.clear();
        assertTrue(q.isEmpty(),"the queue should be empty after clear");


    }
}