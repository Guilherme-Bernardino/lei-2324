package com.brunomnsilva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueArrayListInheritedTest extends QueueGenericTest {

    @BeforeEach
    public void setUp() {
        queue = new QueueLinkedList<>();
    }

}
