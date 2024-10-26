import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import student.TestCase;

/**
 * This class contains tests for the DLLNode class which implements a doubly
 * linked list node.
 * 
 * @author Tiffany Ha
 * @version 2024.9.10
 */
public class ControllerTest extends TestCase {

    private Controller testController;

    /**
     * Sets up the test environment. This method initializes a new DLLNode with
     * test data.
     */
    public void setUp() {
        testController = new Controller();
    }


    /**
     * This test makes sure that our controller performs basic operations
     * correctly - resolivng issues that existed in advanced advanced insert -
     * hash
     */
    @Test
    public void testControllerInsert() {
        String[] keywords = {"Good", "Bad", "Ugly"};
        testController.insert(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        
        assertTrue(testController.getIDTree().getNumberOfNodes() == 1);
        assertTrue(testController.getCostTree().getNumberOfNodes() == 1);
        assertTrue(testController.getDateTree().getNumberOfNodes() == 1);
        assertTrue(testController.getKeywordTree().getNumberOfNodes() == 3);
        //System.out.println(testController.getIDTree().getRoot().getSeminarKey());
        assertTrue(Integer.valueOf(1729).equals(testController.getIDTree().getRoot().getSeminarKey()));
    }

}