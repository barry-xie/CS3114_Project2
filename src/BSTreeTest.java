import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import student.TestCase;

public class BSTreeTest<T extends Comparable<T>> extends TestCase{
	private BSTree<Integer> intTree;
	private BSTNode<Integer> node1;
	private BSTNode<Integer> node2;
	private BSTNode<Integer> node3;
	private BSTNode<Integer> node4;
	private BSTNode<Integer> node5;
	
	@Before
	public void setUp() {
		intTree = new BSTree<Integer>();
		node1 = new BSTNode<Integer>(null, 1);
		node2 = new BSTNode<Integer>(null, 2);
		node3 = new BSTNode<Integer>(null, 3);
		node4 = new BSTNode<Integer>(null, 4);
		node5 = new BSTNode<Integer>(null, 5);
	}

	public void testCreateNodeAndInsert()
	{
        String[] keywords = {"Good", "Bad", "Ugly"};
        Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
	    intTree.createNodeAndInsert(mysem, 4);
	    assertTrue(4 == intTree.getRoot().getSeminarKey());
	    assertEquals(mysem, intTree.getRoot().getSeminar());
	}
	
	@Test
	public void testInsert() {
		intTree.insert(intTree.getRoot(), node2);
		assertTrue(intTree.getRoot().getSeminarKey() == Integer.valueOf(2));
		assertEquals(intTree.getNumberOfNodes(), 1);
		
		intTree.insert(intTree.getRoot(), node1);
		intTree.insert(intTree.getRoot(), node3);
		assertEquals(intTree.getNumberOfNodes(), 3);
		
		node2.printDebug();
		assertTrue(node2.getLeft().equals(node1));
		assertTrue(node2.getRight().equals(node3));
		
		intTree.insert(intTree.getRoot(), node5);
		intTree.insert(intTree.getRoot(), node4);
		assertEquals(intTree.getNumberOfNodes(), 5);
		assertTrue(node2.getRight().getRight().equals(node5));
		assertTrue(node2.getRight().getRight().getLeft().equals(node4));
		
		intTree.insert(intTree.getRoot(), node1);
		assertEquals(intTree.getNumberOfNodes(), 6);
		assertTrue(node2.getLeft().getDuplicateNode() != null);
	}

	@Test
	public void testDeleteNullBehavior() {
		BSTNode<Integer> curr = intTree.deleteBehavior(intTree.getRoot(), 1);
		assertTrue(curr == null);
	}
	
	@Test
	public void testDeleteBehavior() {
		intTree.insert(intTree.getRoot(), node2);
		intTree.insert(intTree.getRoot(), node1);
		intTree.insert(intTree.getRoot(), node3);
		intTree.insert(intTree.getRoot(), node5);
		intTree.insert(intTree.getRoot(), node4);
		assertEquals(intTree.getNumberOfNodes(), 5);
		
		intTree.deleteBehavior(intTree.getRoot(), 4);
		assertEquals(intTree.getNumberOfNodes(), 4);
		assertTrue(intTree.getRoot().getRight().getRight().getLeft() == null);
		
		intTree.insert(intTree.getRoot(), node4);
		intTree.deleteBehavior(intTree.getRoot(), 5);
		assertEquals(intTree.getNumberOfNodes(), 4);
		assertTrue(intTree.getRoot().getRight().getRight().getSeminarKey() == 4);
		//intTree.print();
		
		intTree.deleteBehavior(intTree.getRoot(), 2);
		intTree.print();
		assertTrue(intTree.getRoot().getSeminarKey() == 1);
		assertTrue(intTree.getRoot().getRight().getSeminarKey() == 3);
		assertEquals(intTree.getNumberOfNodes(), 3);
		
	}

	@Test
	public void testSearch() {
		assertTrue(intTree.search(intTree.getRoot(), 1) == null);
		intTree.insert(intTree.getRoot(), node2);
		intTree.insert(intTree.getRoot(), node1);
		intTree.insert(intTree.getRoot(), node3);
		intTree.insert(intTree.getRoot(), node5);
		intTree.insert(intTree.getRoot(), node4);
		assertTrue(intTree.search(intTree.getRoot(), 2) == node2);
		assertTrue(intTree.search(intTree.getRoot(), 1) == node1);
		assertTrue(intTree.search(intTree.getRoot(), 10) == null);
	}

//	@Test
//	public void testSearchAndPrint() {
//		fail("Not yet implemented"); // TODO
//	}
//
	
	@Test
	public void testPrintSimple() {
		intTree.insert(intTree.getRoot(), node2);
		intTree.print();
	}
	
	@Test
	public void testPrintMedium() {
		intTree.insert(intTree.getRoot(), node2);
		intTree.insert(intTree.getRoot(), node1);
		intTree.insert(intTree.getRoot(), node3);
		assertEquals(intTree.getHeight(intTree.getRoot()), 1);
		intTree.print();
	}
	
	@Test
	public void testPrintAdvanced() {
		intTree.insert(intTree.getRoot(), node2);
		intTree.insert(intTree.getRoot(), node1);
		intTree.insert(intTree.getRoot(), node3);
		intTree.insert(intTree.getRoot(), node5);
		intTree.insert(intTree.getRoot(), node4);
		intTree.print();
	}

//	@Test
//	public void testGetNumberOfNodes() {
//		fail("Not yet implemented"); // TODO
//	}

	public void testGetHeight()
	{
		assertEquals(intTree.getHeight(intTree.getRoot()), -1);
		
		intTree.insert(intTree.getRoot(), node2);
		assertEquals(intTree.getHeight(intTree.getRoot()), 0);
		
		intTree.insert(intTree.getRoot(), node1);
		assertEquals(intTree.getHeight(intTree.getRoot()), 1);
		intTree.insert(intTree.getRoot(), node3);
		assertEquals(intTree.getHeight(intTree.getRoot()), 1);
		intTree.insert(intTree.getRoot(), node5);
		assertEquals(intTree.getHeight(intTree.getRoot()), 2);
		intTree.insert(intTree.getRoot(), node4);
		assertEquals(intTree.getHeight(intTree.getRoot()), 3);
	}
}
