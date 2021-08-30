package duke;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * A class encapsulating a chat bot.
 *
 * @author Toh Wang Bin
 */
public class Duke {

    /**
     * The variants of the Tasks handled by Duke
     */
    enum Tasks {DEADLINE, EVENT, TODO}

    //path of file containing stored data
    private static final String FILE_PATH = "data/test.txt";
    //path of folder containing data file
    private static final String DIRECTORY_PATH = "data";

    private void run() {
        //initialise required classes
        TaskList taskList = new TaskList();
        Storage storage = new Storage(FILE_PATH, DIRECTORY_PATH, taskList);


        //activate storage and start reading user input
        storage.start();
        Scanner scan = new Scanner(System.in);
        Message.printWelcomeMessage();

        //start reading user input
        while(true) {
            String input = scan.nextLine();
            //split input into strings by whitespaces
            String[] inputArray = input.split("\\s");
            String firstString = inputArray[0];
            if (Parser.parseInput(taskList, storage, firstString, inputArray)) {
                //The function will only return true if user wants to quit
                break;
            }
        }
        //exit from the program
        scan.close();
        Message.printEndMessage();
    }

    /**
     * Starts the main process, activating the chatbot.
     *
     * @param args The default parameter for the main function.
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        dukeInstance.run();
    }



}
