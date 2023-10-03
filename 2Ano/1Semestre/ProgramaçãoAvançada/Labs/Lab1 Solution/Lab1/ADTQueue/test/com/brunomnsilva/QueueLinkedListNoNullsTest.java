package com.brunomnsilva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueLinkedListNoNullsTest extends QueueGenericTest {

    @BeforeEach
    public void setUp() {
        queue = new QueueLinkedListNoNulls<>();
    }

    @Test
    public void enqueue_Exception_WhenNull() {
        assertThrows(NullNotAllowedException.class, () -> {
            queue.enqueue(null);
        });
    }

}
