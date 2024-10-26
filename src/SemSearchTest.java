import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class SemSearchTest extends TestCase {
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx()
    {
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        SemSearch.main(null);
    }
    
    /**
     * Example 2: This method runs on a command sample IO file You will write
     * similar test cases using different text files
     *
     * @throws Exception
     */
    public void testSampleIO() throws Exception {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "10";
        args[1] = "solutionTestData/P2_sampleInput.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile(
            "solutionTestData/P2_sampleOutput.txt");

        // Compare the two outputs
        // TODO: uncomment the following line
        // once you have implemented your project
        assertFuzzyEquals(expectedOutput, actualOutput);

 }
    /**
     * Example 2: This method runs on a command sample IO file You will write
     * similar test cases using different text files
     *
     * @throws Exception
     */
    public void testSampleIOSimple() throws Exception {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "10";
        args[1] = "solutionTestData/P2_syntaxInsertInput.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile(
            "solutionTestData/P2_syntaxInsertOutput.txt");

        // Compare the two outputs
        // TODO: uncomment the following line
        // once you have implemented your project
        assertFuzzyEquals(expectedOutput, actualOutput);

 }
}

