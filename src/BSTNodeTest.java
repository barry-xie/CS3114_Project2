import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import student.TestCase;

public class BSTNodeTest extends TestCase{
	private BSTNode<Integer> node1;
	private BSTNode<Integer> node2;
	private BSTNode<Integer> node3;

	@Before
	public void setUp() {
		node1 = new BSTNode<Integer>(null, 1);
		node2 = new BSTNode<Integer>(null, 2);
		node3 = new BSTNode<Integer>(null, 3);
	}

	@Test
	public void test() {
		assertEquals(null, node1.getSeminar());
		assertTrue(Integer.valueOf(1) == node1.getSeminarKey());
	}
	
}
