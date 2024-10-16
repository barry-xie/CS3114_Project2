import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Processes instructions from a file to manage artists and songs using separate
 * hash tables.
 * 
 * @author Barry Xie
 * @version 2024.12.02
 */
public class CommandProcessor {
    private Controller myController;

    /**
     * constructor
     * 
     * @param providedController
     *            the controller that is being passed in to
     *            call commands from
     */
    public CommandProcessor(Controller providedController) {
        myController = providedController;
    }


    /**
     * starts reading in from a provided file line by line
     * it is expected that each of those lines have instructions
     * then passes those lines to be processed and executed
     * 
     * @param filename
     *            the file path of the document that we are
     *            reading commands from.
     */
    public void beginParsingByLine(String filename) {
        try {
            // Read the file line by line
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim(); // Trim spaces
//                if (line.isEmpty())
//                    continue; // Skip empty lines

                // send it to helper function
                extractAndProcessinstruction(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void extractAndProcessinstruction(String line) {
        Pattern pattern = Pattern.compile("(insert|remove|print)\\s*(.*)");
        Matcher matcher = pattern.matcher(line);
        String instruction;
        String arguments;
        if (matcher.find()) {
            instruction = matcher.group(1); // Returns "insert", "remove", or
                                            // "print"
            arguments = matcher.group(2);
        }
        else {
            System.out.println("instruction was not recognized");
            return;
        }

        switch (instruction) {
            case "insert":
                Pattern insertPattern = Pattern.compile("(.+?)<SEP>(.+)");
                matcher = insertPattern.matcher(arguments);

                if (matcher.find()) {
                    String artist = matcher.group(1).trim();
                    String song = matcher.group(2).trim();
                    myController.insert(artist, song);
                }
                break;
            case "remove":
                Pattern removePattern = Pattern.compile("(artist|song)\\s(.+)");
                matcher = removePattern.matcher(arguments);

                if (matcher.find()) {
                    String type = matcher.group(1).trim();
                    String name = matcher.group(2).trim();
                    myController.remove(type, name);
                }
                break;
            case "print":
                Pattern printPattern = Pattern.compile("(artist|song|graph)");
                matcher = printPattern.matcher(arguments);

                if (matcher.find()) {
                    String type = matcher.group(1).trim();
                    myController.print(type);
                }
                break;
            default:
                System.out.println("Unrecognized input: " + line);
                break;
        }
    }
}