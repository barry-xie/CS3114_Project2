public class BSTNode<T extends Comparable<T>> {
	private T seminarKey;
	private Seminar seminar;
	private BSTNode<T> left;
	private BSTNode<T> right;
	private BSTNode<T> duplicateNode = null;

	/**
	 * default constructor
	 * 
	 * @param newSeminar seminar node contains
	 * @param key        key node contains
	 */
	public BSTNode (Seminar newSeminar, T key) {
		seminar = newSeminar;
		seminarKey = key;
		left = null;
		right = null;
	}

//this is probably not needed since we can just access the key baseTypes compareTo method
//	public int compareTo(BSTNode<T> o) {
//		return this.seminarKey.compareTo(o.seminarKey);
//	}

	
	/**
	 * setter for left
	 * 
	 * @param newLeft this is the new left
	 */
	public void setLeft(BSTNode<T> newLeft) {
		left = newLeft;
	}

	/**
	 * setter for left
	 * 
	 * @param newLeft this is the new right
	 */
	public void setRight(BSTNode<T> newRight) {
		right = newRight;
	}

	public void setNextDuplicate(BSTNode<T> nodeWithDuplicateKey)
	{
		duplicateNode = nodeWithDuplicateKey;
	}
	
	/**
	 * print this nodes contents
	 */
	public void print()
	{
		System.out.println(seminar.toString());
	}
	
	/**
	 * print for debug
	 */
	public void printDebug()
	{
		System.out.println("left: " + left.getSeminarKey() + " right: "+ right.getSeminarKey());
	}
	
	/**
	 * getter for seminar
	 * 
	 * @return this nodes seminar
	 */
	public Seminar getSeminar() {
		return seminar;
	}

	/**
	 * getter for seminarKey
	 * 
	 * @return this nodes key
	 */
	public T getSeminarKey() {
		return seminarKey;
	}

	/**
	 * getter for left
	 * 
	 * @return this node left
	 */
	public BSTNode<T> getLeft() {
		return left;
	}

	/**
	 * getter for right
	 * 
	 * @return this nodes right
	 */
	public BSTNode<T> getRight() {
		return right;
	}
	
	/**
	 * getter for duplicateNode
	 * 
	 * @return this nodes next duplicate
	 */
	public BSTNode<T> getDuplicateNode() {
		return duplicateNode;
	}

}
