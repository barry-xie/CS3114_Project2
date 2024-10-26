import java.util.List;

/**
 * The Controller class manages the operations between hash tables and a graph.
 * It provides functionality to insert and remove artists and songs into their
 * respective hash tables, check for expansions in the graph, and display the
 * connected components of the graph.
 * 
 * @author Barry Xie
 * @version 2024.9.10
 */
public class Controller {
	private BSTree<Integer> IDTree;
	private BSTree<Integer> costTree;
	private BSTree<String> dateTree;
	private BSTree<String> keywordTree;

	/**
	 * Constructs a controller with a specified size for a shared hash table.
	 * 
	 * @param size The initial capacity of the hash table.
	 */
	public Controller() {
		setIDTree(new BSTree<>());
		setCostTree(new BSTree<>());
		setDateTree(new BSTree<>());
		setKeywordTree(new BSTree<>());
	}

	/**
	 * inserts a seminar into the system. our seminar to add should have a unique
	 * ID. if it duplicates a seminar id already in the system, this is a duplicate
	 * seminar and we should not add it otherwise create a record for it, and insert
	 * it correctly to all of our existing trees.
	 * 
	 * @param ID
	 * @param title
	 * @param dateAndTime
	 * @param length
	 * @param x
	 * @param y
	 * @param cost
	 * @param keywordList
	 * @param description
	 */
	public void insert(int ID, String title, String dateAndTime, int length, short x, short y, int cost,
			String[] keywordList, String description) {

		// check if seminar is already in system
		// could split this off before reading seminar info for optimal runtime/space
		// efficiency
		if (getIDTree().search(getIDTree().getRoot(), ID) != null) {
			System.out.println("Insert FAILED - There is already a record with ID " + ID);
			return;
		}

		// check if x,y are valid
		// first check negatives
		// bitwise manipulation to check first bit for negation is most efficient
		if (x < 0 || y < 0) {
			System.out.println("Insert FAILED - Bad x, y coordinates: " + x + ", " + y);
			return;
		}
		if (x > 127) {
			System.out.println("Bad coordinate: x value is too big");
			return;
		}
		if (y > 127) {
			System.out.println("Bad coordinate: y value is too big");
			return;
		}

		// check world size?

		// otherwise add it
		// first create our seminar object
		Seminar current = new Seminar(ID, title, dateAndTime, length, x, y, cost, keywordList, description);
		
		// then add it to all relevant trees
		getIDTree().createNodeAndInsert(current, ID);
		getCostTree().createNodeAndInsert(current, cost);
		getDateTree().createNodeAndInsert(current, dateAndTime);
		for (int i = 0; i < keywordList.length; i++)
		{
			getKeywordTree().createNodeAndInsert(current, keywordList[i]);
		}

		System.out.println("Successfully inserted record with ID " + ID);
		System.out.println(current.toString());
	}

	/**
	 * search IDTree for a seminar with the given ID. remove seminar from tree if
	 * found, otherwise error
	 * @param <T>
	 * 
	 * @param ID
	 */
	public void delete(int ID) {
		BSTNode<Integer> curr = getIDTree().search(getIDTree().getRoot(), ID);
		
		// if seminar doesnt exist
		if (curr == null) {
			System.out.println("delete did not find error mesasge");
			return;
		}

		
		
		// otherwise delete all trees
		getIDTree().deleteBehavior(getIDTree().getRoot(), curr.getSeminar().id());
		getCostTree().deleteBehavior(getCostTree().getRoot(), curr.getSeminar().cost());
		getDateTree().deleteBehavior(getDateTree().getRoot(), curr.getSeminar().date());
		for (String word: curr.getSeminar().keywords())
		{
			getKeywordTree().keywordDeleteBehavior(getKeywordTree().getRoot(), word, curr.getSeminar());
		}
		

		System.out.println("Record with ID " + ID + " successfully deleted from the database");
	}
	
	/*
	 * Initial idea for search is implementing an array or hashmap that contains all
	 * of our nodes sorted by ID, therefore search for ID is O(n), and we can handle
	 * results from search functions easier if we have anything to do with them
	 * later Space cost would just be an array of pointers to each existing seminar
	 */
	
	/**
	 * search the expected BST for any trees that match a value or fall into a group of values and print them 
	 * excess identifier parameters should be null if not expected for the corresponding tree
	 * 
	 * @param treeToSearch
	 * @param identifier1
	 * @param identifier2
	 * @param identifier3
	 */
	public void search(String treeToSearch, String identifier1, String identifier2, String identifier3) {
		// find which tree we're searching through and search it
		switch(treeToSearch)
		{
		case "ID":
			getIDTree().searchAndPrint(Integer.parseInt(identifier1));
			break;
		case "cost":
			getCostTree().searchAndPrintRange(Integer.parseInt(identifier1), Integer.parseInt(identifier2), "costs");
			break;
		case "date":
			getDateTree().searchAndPrintRange(identifier1, identifier2, "dates");
			break;
		case "keyword":
			System.out.println("Seminars matching keyword VT:");
			getKeywordTree().searchAndPrintKeyword(identifier1);
			break;
		}
	}

	/**
	 * prints the appropriate tree
	 * 
	 * @param treeToPrint
	 */
	public void print(String treeToPrint)
	{
		// find which tree we're printing and print it
		// should technically handle printing error messages here for good OOP but im lazy
		switch(treeToPrint)
		{
		case "ID":
			getIDTree().print();
			break;
		case "cost":
			getCostTree().print();
			break;
		case "date":
			getDateTree().print();
			break;
		case "keyword":
			getKeywordTree().print();
			break;
		}
	}

    public BSTree<Integer> getIDTree() {
        return IDTree;
    }

    public void setIDTree(BSTree<Integer> iDTree) {
        IDTree = iDTree;
    }

    public BSTree<Integer> getCostTree() {
        return costTree;
    }

    public void setCostTree(BSTree<Integer> costTree) {
        this.costTree = costTree;
    }

    public BSTree<String> getDateTree() {
        return dateTree;
    }

    public void setDateTree(BSTree<String> dateTree) {
        this.dateTree = dateTree;
    }

    public BSTree<String> getKeywordTree() {
        return keywordTree;
    }

    public void setKeywordTree(BSTree<String> keywordTree) {
        this.keywordTree = keywordTree;
    }
}