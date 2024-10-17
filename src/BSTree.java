// this class should implement a BST
public class BSTree<T extends Comparable<T>> {
	private BSTNode<T> root;
	private int numberOfNodes = 0;
	private int treeHeight = 0; // note this is only updated in print

	/**
	 * default constructor
	 */
	public BSTree() {
		this.root = null;
	}

	/**
	 * constructor when called with a root node
	 * 
	 * @param root
	 */
	public BSTree(BSTNode<T> root) {
		this.root = root;
	}

	public void createNodeAndInsert(Seminar newSeminar, T newSeminarKey) {
		BSTNode<T> newNode = new BSTNode<T>(newSeminar, newSeminarKey);
		insert(root, newNode);
	}

	/**
	 * recursively find the correct position, and if empty, insert
	 * 
	 * @param root         the node we are start from, used recursively
	 * @param nodeToinsert the node we are trying to insert
	 * @return our updated tree
	 */
	public BSTNode<T> insert(BSTNode<T> root, BSTNode<T> nodeToinsert) {
		if (numberOfNodes == 0) {
			this.root = nodeToinsert;
			// changed because of mutation testing "replaced wtih second member"
			numberOfNodes = 1;
			return this.root;
		}
		if (root == null) {
			numberOfNodes++;
			return nodeToinsert;
		}

		int comparison = root.getSeminarKey().compareTo(nodeToinsert.getSeminarKey());

		if (comparison > 0) {
			root.setLeft(insert(root.getLeft(), nodeToinsert));
		} else if (comparison < 0) {
			root.setRight(insert(root.getRight(), nodeToinsert));
		} else {
			// should allow duplicate inserts
			numberOfNodes++;
			root.setNextDuplicate(nodeToinsert);
		}

		return root;
	}

//	//TODO change deleteBehavior to this if it all works out
//	/**
//	 * finds the correct node to delete in a tree then calls on that node
//	 * 
//	 * 
//	 */
//	public void delete(T keyToDelete) {
//		if (keyToDelete.getClass().isArray())
//		{
//			for (int i = 0; i < keyToDelete.length(); i++)
//			{
//				deleteBehavior(root, keyToDelete[i]);
//			}
//		}
//		else 
//		{
//			deleteBehavior(root, keyToDelete);
//		}
//	}
	
	/**
	 * testing zone for keyword tree deletion
	 * currently i have the brute force implementation
	 * we are trying to handle the case where duplicate nodes are at the same key, so they chain 
	 * off in a linked list
	 * 
	 * @param root
	 */
	public BSTNode<T> keywordDeleteBehavior(BSTNode<T> root, T keyToDelete, Seminar seminarToDelete) {
		if (root == null) {
			return root;
		}

		int comparison = root.getSeminarKey().compareTo(keyToDelete);

		if (comparison > 0) {
			root.setLeft(keywordDeleteBehavior(root.getLeft(), keyToDelete, seminarToDelete));
		} else if (comparison < 0) {
			root.setRight(keywordDeleteBehavior(root.getRight(), keyToDelete, seminarToDelete));
		} 
//		else if (root.getSeminar() != seminarToDelete)
//		{
//			root.setNextDuplicate(deleteBehavior(root.getDuplicateNode(), keyToDelete, seminarToDelete));
//		}
		else {
			if (root.getDuplicateNode() != null && root.getSeminar() == seminarToDelete) {
				BSTNode<T> initial = root;
				root = root.getDuplicateNode();
				root.setLeft(initial.getLeft());
				root.setRight(initial.getRight());
			    return root; // Update the root to the next duplicate
			}
			//here i know it must be a keywords case
			if (root.getSeminar() != seminarToDelete)
			{
				BSTNode<T> initial = root;
				BSTNode<T> previous = root;
				root = root.getDuplicateNode();
				while (root != null && root.getSeminar() != seminarToDelete)
				{
					previous = root;
					root = root.getDuplicateNode();
				}
				if (root == null)
				{
					return initial; //seminar not found case
				}
				previous.setNextDuplicate(root.getDuplicateNode());
				return initial;
			}

			if (root.getLeft() == null) {
				numberOfNodes--;
				return root.getRight();
			}

			if (root.getRight() == null) {
				numberOfNodes--;
				return root.getLeft();
			}

			// When both children are present
			BSTNode<T> succ = getMax(root.getLeft());
			root.setSeminarKey(succ.getSeminarKey());
			root.setSeminar(succ.getSeminar());
			root.setLeft(keywordDeleteBehavior(root.getLeft(), succ.getSeminarKey(), seminarToDelete));
		}
		return root;
	}
	

	/**
	 * handle the actual deletion behavior.
	 * traverse the tree to find the node to
	 * delete then delete it in the case where it has two children, default to
	 * replacing it with the largest value less than it
	 * 
	 * @param root
	 */
	public BSTNode<T> deleteBehavior(BSTNode<T> root, T keyToDelete) {
		if (root == null) {
			return root;
		}

		int comparison = root.getSeminarKey().compareTo(keyToDelete);

		if (comparison > 0) {
			root.setLeft(deleteBehavior(root.getLeft(), keyToDelete));
		} else if (comparison < 0) {
			root.setRight(deleteBehavior(root.getRight(), keyToDelete));
		} 
		else {
			if (root.getLeft() == null) {
				numberOfNodes--;
				return root.getRight();
			}

			if (root.getRight() == null) {
				numberOfNodes--;
				return root.getLeft();
			}

			// When both children are present
			BSTNode<T> succ = getMax(root.getLeft());
			root.setSeminarKey(succ.getSeminarKey());
			root.setSeminar(succ.getSeminar());
			root.setLeft(deleteBehavior(root.getLeft(), succ.getSeminarKey()));
		}
		return root;
	}

	// Get the maximum valued element in a subtree
	private BSTNode<T> getMax(BSTNode<T> root) {
		if (root.getRight() == null) {
			return root;
		}
		return getMax(root.getRight());
	}

	/**
	 * searches if BSTree contains a node by given key. used primarily to check if a
	 * tree contains the key already
	 * 
	 * should it contain the location to insert if doesnt exist? we can just return
	 * the node we find actually
	 */
	public BSTNode<T> search(BSTNode<T> root, T keyToSearch) {
		if (root == null) {
			return root;
		}

		int comparison = root.getSeminarKey().compareTo(keyToSearch);

		if (comparison > 0) {
			return search(root.getLeft(), keyToSearch);
		} else if (comparison < 0) {
			return search(root.getRight(), keyToSearch);
		} else {
			return root;
		}
	}

	/**
	 * searches our tree for any nodes matching the conditions and prints all
	 * 
	 * @return whether or not found and printed
	 */
	public void searchAndPrint(T key) {
		BSTNode<T> curr = search(root, key);
		if (curr == null)
		{
			System.out.println("Search FAILED -- There is no record with ID " + key);
			return;
		}
		System.out.println("Found record with ID " + key);
		System.out.println(curr.getSeminar().toString());
		return;
	}

	public void searchAndPrintRange(T identifier1, T identifier2, String treeName)
	{
		System.out.println("Seminars with " + treeName + " in range " + identifier1 + " to " + identifier2 + ":");
		searchAndPrintRangeBehavior(root, identifier1, identifier2);
	}
	
	/**
	 * this function will handle searching for a range of values within our tree
	 * 
	 * @return whether or not found and printed
	 */
	public void searchAndPrintRangeBehavior(BSTNode<T> root, T min, T max) {
	    if (root == null) {
	        return;
	    }

	    // Explore the left subtree only if the current key is greater than min
	    if (min.compareTo(root.getSeminarKey()) < 0) {
	        searchAndPrintRangeBehavior(root.getLeft(), min, max);
	    }

	    // Print the node if its key is within the range
	    if (min.compareTo(root.getSeminarKey()) <= 0 && max.compareTo(root.getSeminarKey()) >= 0) {
	        System.out.print(root.getSeminarKey() + " ");
	    }

	    // Explore the right subtree only if the current key is less than max
	    if (max.compareTo(root.getSeminarKey()) > 0) {
	        searchAndPrintRangeBehavior(root.getRight(), min, max);
	    }
	}
	
	/**
	 * searches our tree for any nodes matching the conditions and prints all
	 * 
	 * @return whether or not found and printed
	 */
	public void searchAndPrintKeyword(T key) {
		BSTNode<T> curr = search(root, key);
		System.out.println("Seminars matching keyword VT:");
		while (curr != null)
		{
			System.out.println(curr.getSeminar().toString());
			curr = curr.getDuplicateNode();
		}
		
		return;
	}

	/**
	 * this one prints the whole tree
	 */
	public void print() {
		if (root == null) {
			System.out.println("This tree is empty");
			return;
		}
		treeHeight = getHeight(root);
		printhelp(root, 0);
	}

	private void printhelp(BSTNode<T> root, int nodeDepth) {
		if (root == null) {
			printVisit(root, nodeDepth);
			return;
		}
		printhelp(root.getLeft(), nodeDepth + 1);
		printVisit(root, nodeDepth);
		printhelp(root.getRight(), nodeDepth + 1);
	}

	private void printVisit(BSTNode<T> nodeToPrint, int nodeDepth) {
		if (nodeToPrint == null) {
			for (int i = 0; i < treeHeight - nodeDepth + 1; i++) {
				System.out.print("    ");
			}
			System.out.println("(null)");
		} else {
			for (int i = 0; i < treeHeight - nodeDepth + 1; i++) {
				System.out.print("    ");
			}
			System.out.println("\\");
			for (int i = 0; i < treeHeight - nodeDepth + 1; i++) {
				System.out.print("    ");
			}
			System.out.println("(" + nodeToPrint.getSeminarKey() + ")");
			for (int i = 0; i < treeHeight - nodeDepth + 1; i++) {
				System.out.print("    ");
			}
			System.out.println("/");
		}
	}

	/**
	 * update the root height
	 * 
	 * @param root the node we are checking the height of
	 * @return the height of root
	 */
	public int getHeight(BSTNode<T> root) {
		if (root == null) {
			return -1; // Base case: height of an empty tree is -1
		}
		// Recursively find the height of left and right subtrees
		int leftHeight = getHeight(root.getLeft());
		int rightHeight = getHeight(root.getRight());

		// Height of the current node is 1 + max of left and right subtree heights
		return 1 + Math.max(leftHeight, rightHeight);
	}

	/**
	 * getter for root
	 * 
	 * @return tree root
	 */
	public BSTNode<T> getRoot() {
		return root;
	}

	/**
	 * getter for number of nodes
	 * 
	 * @return number of nodes in the tree
	 */
	public int getNumberOfNodes() {
		return numberOfNodes;
	}
}