//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import org.junit.Test;
//import student.TestCase;
//
///**
// * This class contains tests for the DLLNode class which implements a doubly
// * linked list node.
// * 
// * @author Tiffany Ha
// * @version 2024.9.10
// */
//public class ControllerTest extends TestCase {
//
//    private Controller testController;
//
//    /**
//     * Sets up the test environment. This method initializes a new DLLNode with
//     * test data.
//     */
//    public void setUp() {
//        testController = new Controller(10);
//    }
//
//
//    /**
//     * This test makes sure that our controller performs basic operations
//     * correctly - resolivng issues that existed in advanced advanced insert -
//     * hash
//     */
//    @Test
//    public void testControllerInsert() {
//        testController.insert("Tiny Bradshaw", "Well Oh Well");
//        int artistHash1 = Hash.h("Tiny Bradshaw", 10);
//        int songHash1 = Hash.h("Well Oh Well", 10);
//        assertTrue(testController.getArtistHash().getTable()[artistHash1]
//            .getKey().equals("Tiny Bradshaw"));
//        assertTrue(testController.getSongHash().getTable()[songHash1].getKey()
//            .equals("Well Oh Well"));
//        testController.print("artist");
//        testController.print("song");
//        testController.insert("Tiny Bradshaw", "Well Oh Well");
//        testController.print("artist");
//        testController.print("song");
//        assertEquals(1, testController.getArtistHash().getSize());
//        assertEquals(1, testController.getSongHash().getSize());
//    }
//
//}