package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pt.pa.adts.EmptyContainerException;
import pt.pa.adts.IntegerBST;

/**
 * Tests
 */
public class Tests {

	IntegerBST bst;
	IntegerBST bstNull;

	@Before
	public void setUp() {
		bst = new IntegerBST(4);
		bstNull = new IntegerBST();

        bst.insert(2);
        bst.insert(1);
        bst.insert(3);        
        bst.insert(8);
        bst.insert(6);        
        bst.insert(5);
        bst.insert(10);
        bst.insert(14);
        bst.insert(9);
	}
	
	@Test
	public void isThrow_Exeption_onSumOddOnEmptyTree() {
		assertThrows(EmptyContainerException.class, () -> {
			bstNull.sumOdd();
		});
	}

	@Test
	public void sumOdd_isCorrect_afterInsertAndRemove() {
		bst.insert(0);
		bst.insert(7);
		bst.insert(12);
		bst.remove(5);
		assertEquals(Integer.valueOf(20), Integer.valueOf(bst.sumOdd()));
	}

	@Test
	public void test_elementExists_AfterInsert() {
		bstNull.insert(7);
		bstNull.insert(13);
		bstNull.insert(0);
		assertTrue(bstNull.exists(7));
		assertTrue(bstNull.exists(13));
		assertTrue(bstNull.exists(0));
	}
	
	@Test
	public void test_elementDoNotExit_afterRemove() {
		bst.insert(4);
		bst.remove(4);
		bst.remove(2);
		bst.remove(1);
		assertTrue(!bstNull.exists(2));
		assertTrue(!bstNull.exists(1));
		assertTrue(!bstNull.exists(4));
	}

	@Test
	public void isCorrect_inOrder_withSeveralTree() {
		bst.insert(17);
		bst.remove(4);
		assertTrue(equals(bst.inOrder(), 1, 2, 3, 5, 6, 8, 9, 10, 14, 17));
	}

	private boolean equals(Iterable<Integer> actual, Integer... expected) {
		int i = 0;
		int count = 0;
		for (int item: actual) {
			if (item != expected[i++]) {
				return false;
			} count++;
		} return count == expected.length;
	}

	@Test
	public void countGreaterThan_isCorrect_afterInsertAndRemove() {
		bst.remove(2);
		bst.remove(10);
		bst.insert(-1);
		bst.insert(123);
		assertEquals(Integer.valueOf(5), Integer.valueOf(bst.countGreaterThan(5)));
	}

	@Test
	public void breadthOrder_isCorrect_afterInsertAndRemove() {
		bst.remove(4);
		assertTrue(equals(bst.breadthOrder(), 5, 2, 8, 1, 3, 6, 10, 9, 14));
		bst.remove(5);
		assertTrue(equals(bst.breadthOrder(), 6, 2, 8, 1, 3, 10, 9, 14));
	}
}
