import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import student.TestCase;

public class BSTreeTest extends TestCase{
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

//	@Test
//	public void testBSTreeBSTNode() {
//		fail("Not yet implemented"); // TODO
//	}
//
//
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testSearch() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testSearchAndPrint() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testPrint() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testGetNumberOfNodes() {
//		fail("Not yet implemented"); // TODO
//	}

}
