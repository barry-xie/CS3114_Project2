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
        try (// Read the file line by line
		Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim(); // Trim spaces
                if (line.isEmpty())
                    continue; // Skip empty lines

                // send it to helper function
                extractAndProcessinstruction(line, sc);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void extractAndProcessinstruction(String line, Scanner sc) {
        Pattern pattern = Pattern.compile("(insert|delete|search|print)\\s*(.*)");
        Matcher matcher = pattern.matcher(line);
        String instruction;
        String arguments;
        if (matcher.find()) {
            instruction = matcher.group(1); // Returns "insert", "delete", "search", "print"
            arguments = matcher.group(2);
        }
        else {
            System.out.println("instruction was not recognized");
            return;
        }

        switch (instruction) {
        case "insert":
            Pattern insertPattern1 = Pattern.compile(".*");
            Pattern insertPattern2 = Pattern.compile(".*");
            Pattern insertPattern3 = Pattern.compile("(\\S+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
            Pattern insertPattern4 = Pattern.compile(".*"); //note this needs further parsing to add into an array or arraylist
            Pattern insertPattern5 = Pattern.compile(".*");
            
            String titleLine = sc.nextLine().trim();
            String dateLine = sc.nextLine().trim();
            String keywordLine = sc.nextLine().trim();
            String descriptionLine = sc.nextLine().trim();
            
            matcher = insertPattern1.matcher(arguments);
            Matcher titleMatcher = insertPattern2.matcher(titleLine);
            Matcher dateMatcher = insertPattern3.matcher(dateLine);
            Matcher keywordMatcher = insertPattern4.matcher(keywordLine);
            Matcher descriptionMatcher = insertPattern5.matcher(descriptionLine);
            
            if (matcher.find() & titleMatcher.find() & dateMatcher.find() & keywordMatcher.find() & descriptionMatcher.find()) {
                int ID = Integer.parseInt(matcher.group(1).trim());
                String title = titleMatcher.group(1).trim();
                String date = dateMatcher.group(1).trim();
                int length = Integer.parseInt(dateMatcher.group(2).trim());
                short x = Short.parseShort(dateMatcher.group(3).trim());
                short y = Short.parseShort(dateMatcher.group(4).trim());
                int cost = Integer.parseInt(dateMatcher.group(5).trim());
                String[] keywords = keywordMatcher.group(1).trim().split("\\s+");
                String desc = descriptionMatcher.group(1).trim();
                myController.insert(ID, title, date, length, x, y, cost, keywords, desc);
            }
            break;
            
        case "delete":
            Pattern deletePattern = Pattern.compile(".*");
            matcher = deletePattern.matcher(arguments);

            if (matcher.find()) {
                int ID = Integer.parseInt(matcher.group(1).trim());
                myController.delete(ID);
            }
            break;
            
        case "search":
            Pattern searchPattern = Pattern.compile("(ID|cost|date|keyword|location)\\s*(.*)");
            matcher = searchPattern.matcher(arguments);
            
            if (matcher.find()) {
                String treeToSearch = matcher.group(1).trim();
                String searchParameters = matcher.group(2).trim();
                
                switch (treeToSearch) {
                case "ID":
                	String ID = searchParameters;
                	myController.search("ID", ID, null, null);
                	break;
                case "keyword":
                    String keyword = searchParameters;
                	myController.search("keyword", keyword, null, null);
                	break;
                case "cost":
                    String[] costs = searchParameters.split("\\s+");
                    myController.search("cost", costs[1], costs[2], null);
                    break;
                case "date":
                    String[] dates = searchParameters.split("\\s+");
                    myController.search("cost", dates[1], dates[2], null);
                	break;
                case "location":
                    String[] locations = searchParameters.split("\\s+");
                    //TODO: this needs to be implemented in controller
                    myController.search("location", locations[1], locations[2], locations[3]);
                    break;
                }
            }
            break;
        case "print":
            Pattern printPattern = Pattern.compile("(ID|cost|date|keyword|location)");
            matcher = printPattern.matcher(arguments);

            if (matcher.find()) {
                String type = matcher.group(1).trim();
                myController.print(type);
            }
            break;
        default:
            System.out.println("Unrecognized input: " + line);
            break;
            
//        switch (instruction) {
//            case "insert":
//                Pattern insertPattern = Pattern.compile("(.+?)<SEP>(.+)");
//                matcher = insertPattern.matcher(arguments);
//
//                if (matcher.find()) {
//                    String artist = matcher.group(1).trim();
//                    String song = matcher.group(2).trim();
//                    myController.insert(artist, song);
//                }
//                break;
//            case "remove":
//                Pattern removePattern = Pattern.compile("(artist|song)\\s(.+)");
//                matcher = removePattern.matcher(arguments);
//
//                if (matcher.find()) {
//                    String type = matcher.group(1).trim();
//                    String name = matcher.group(2).trim();
//                    myController.remove(type, name);
//                }
//                break;
//            case "print":
//                Pattern printPattern = Pattern.compile("(artist|song|graph)");
//                matcher = printPattern.matcher(arguments);
//
//                if (matcher.find()) {
//                    String type = matcher.group(1).trim();
//                    myController.print(type);
//                }
//                break;
//            default:
//                System.out.println("Unrecognized input: " + line);
//                break;
        }
    }
}