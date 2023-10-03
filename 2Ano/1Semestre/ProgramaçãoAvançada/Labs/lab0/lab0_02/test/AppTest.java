package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.pa.adts.InvalidSizeException;
import pt.pa.adts.MyRectangle;

/**
 * AppTest
 */
public class AppTest {

    @Test
    public void testIsPerfect_returnsTrue_whenRectangleIsPerfect() {
        MyRectangle rect = new MyRectangle("One", 10.0, 20.0, 30.0, 40.0);
        assertTrue(rect.isPerfect());
        rect = new MyRectangle("Two", 20.0, 40.0);
        assertTrue(rect.isPerfect());
    }

    @Test
    public void testGetHypotenuse() {
        MyRectangle rect = new MyRectangle("Zip Rect", 10.0, 20.0, 1.0, 4.0);
        double hypotenuse = rect.getHypotenuse();
        assertEquals(4.1231, hypotenuse, 0.0001);
    }

    @Test
    public void name_isUpperCase_afterCreate() {
        MyRectangle rect = new MyRectangle("One", 1, 1);
        assertEquals(rect.getName().toUpperCase(), rect.getName());
    };

    @Test
    public void name_isUpperCase_afterSettingName() {
        MyRectangle rect = new MyRectangle("One", 1, 1);
        rect.setName("Two");
        assertNotEquals(rect.getName().toUpperCase(), rect.getName());
    };
	
    @Test
    public void testInvalidSizeException_afterCreate() {
		assertThrows(InvalidSizeException.class, () -> {
			MyRectangle rect = new MyRectangle("One", (-1), 2);
			rect.getWidth(); 
		});
          
	}
	
    @Test
    public void testInvalidSizeException_afterSettingValues() {
		assertThrows(InvalidSizeException.class, () -> {
			MyRectangle rect = new MyRectangle("One", 1, 1);
			rect.setHeight(-1);
		});
	}
    

    @Test
    public void testIllegalArgumentException_afterCreate() {
        assertThrows(IllegalArgumentException.class, () -> {
			MyRectangle rect = new MyRectangle(null, 1, 1);
            rect.getName();
        });
    }

    @Test
    public void testIllegalArgumentException_afterSettingName() {
        assertThrows(IllegalArgumentException.class, () -> {
			MyRectangle rect = new MyRectangle("One", 1, 1);
            rect.setName(null);
        });
    }

}
