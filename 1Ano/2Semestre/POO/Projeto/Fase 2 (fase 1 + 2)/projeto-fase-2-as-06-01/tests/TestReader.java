package tests;

import coords.Dimension;
import coords.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestReader {

    private Position position;
    private Dimension dimension;

    @BeforeEach
    public void setUp(){
        position = new Position(1,2);
        dimension = new Dimension(3, 4);
    }
    @Test
    void positionGetX() {
        int value = position.getX();
        int expectedValue = 1;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void positionSetX() {
        position.setX(2);
        int value = position.getX();
        int expectedValue = 2;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void positionGetY() {
        int value = position.getY();
        int expectedValue = 2;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void positionSetY() {
        position.setY(4);
        int value = position.getY();
        int expectedValue = 4;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void dimensionGetX() {
        int value = dimension.getX();
        int expectedValue = 3;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void dimentionSetX() {
        dimension.setX(5);
        int value = dimension.getX();
        int expectedValue = 5;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void dimentionGetY() {
        int value = dimension.getY();
        int expectedValue = 4;
        Assertions.assertEquals(expectedValue,value);
    }

    @Test
    void dimentionSetY() {
        dimension.setY(6);
        int value = dimension.getY();
        int expectedValue = 6;
        Assertions.assertEquals(expectedValue,value);
    }
}