// this class should implement a BST
public class BSTree<T extends Comparable<T>> {
	private BSTNode<T> root;
	private int numberOfNodes = 0;
	
	/**
	 * default constructor
	 */
	public BSTree()
	{
		this.root = null;
	}
	
	/**
	 * constructor when called with a root node
	 * 
	 * @param root
	 */
	public BSTree(BSTNode<T> root)
	{
		this.root = root;
	}
	
	public void createNodeAndInsert(Seminar newSeminar, T newSeminarKey)
	{
		BSTNode<T> newNode = new BSTNode<T>(newSeminar, newSeminarKey);
		insert(root, newNode);
	}
	
	/**
	 * recursively find the correct position, and if empty, insert
	 * 
	 * @param rt    the node we are starting from, used recursively
	 * @param nodeToInsert   the node we are trying to insert
	 * @return     our updated tree
	 */
	public BSTNode<T> insert(BSTNode<T> rt, BSTNode<T> nodeToInsert)
	{
		if (numberOfNodes == 0)
		{
			root = nodeToInsert;
			// changed because of mutation testing "replaced wtih second member"
			numberOfNodes = 1;
			return root;
		}
		if (rt == null)
		{
			numberOfNodes++;
			return nodeToInsert;	
		}
		
		int comparison = rt.getSeminarKey().compareTo(nodeToInsert.getSeminarKey());


		if (comparison > 0)
		{
			rt.setLeft(insert(rt.getLeft(), nodeToInsert));
		}
		else if (comparison < 0)
		{
			rt.setRight(insert(rt.getRight(), nodeToInsert));
		}
		else
		{
			//should allow duplicate inserts
			numberOfNodes++;
			rt.setNextDuplicate(nodeToInsert);
		}
		
		return rt;
	}
	
	/**
	 * traverse the tree to find the node to delete then delete it
	 * in the case where it has two children, default to replacing it with the largest value less than it
	 * 
	 */
	public void delete(T keyToDelete)
	{
		BSTNode<T> curr = root;
		//if ()
	}
	
	/**
	 * searches if BSTree contains a node by given key.
	 * should it contain the location to insert if doesnt exist?
	 * we can just return the node we find actually
	 */
	public BSTNode<T> search()
	{
		return null;
	}
	
	/**
	 * searches our tree for any nodes matching the conditions and prints all
	 * 
	 * @return whether or not found and printed
	 */
	public boolean searchAndPrint()
	{
	// handle not found/print behavior	
		return false;
	}
	
	/**
	 * this function will handle searching for a range of values within our tree
	 * 
	 * @return
	 */
	public boolean searchAndPrintRange()
	{
		return false;
	}
	
	/**
	 * this one prints the whole tree
	 */
	public void print()
	{}
	
	public BSTNode<T> getRoot()
	{
		return root;
	}
	
	public int getNumberOfNodes()
	{
		return numberOfNodes;
	}
}
