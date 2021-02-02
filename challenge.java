
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Queue;

public class challenge {

    public static void main(String[] args)throws FileNotFoundException{
        boolean debug = false;

        //User testing from file
        //Reads in from file for testing
        if(debug){
            Scanner scanfile = new Scanner(System.in);
            System.out.println("Enter Input File Location:");
            String filename = scanfile.nextLine();
            scanfile.close();
            //Clears the terminal screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        File in = new File(filename);
        Scanner scan = new Scanner(in);
        scan.useDelimiter("");

        challenge test = new challenge();

        //C = 01000011
        @SuppressWarnings("unchecked")
        Queue<Character> queue = test.setup(scan);

        //Runs as long as the file has more data within it
        while(scan.hasNext()){
            
            //Queue of 8 bits that is constantly checking to see if its 'C', else it bit shifts to the right by 1.
            if(test.bitCheck(queue)){
                //checkFlag is called after a 'C' is detected, and the following letters are checked to see if CAPTIVATION is written
                if (test.checkFlag(scan)){
                    //If there is indeed the CAPTIVATION flag, then print the next 100 characters, then go back to polling for 'C'
                    test.printHundred(scan);
                }
            }
            //Shifts the queue to the right by 1 bit
            if(scan.hasNext()) {
                queue.remove();
                queue.add(scan.next().charAt(0));
            }

        }

    }
    //Setup, populates queue which will be running FIFO to check for the char 'C'
    Queue setup(Scanner scan){
        Queue<Character> setup = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            setup.add(scan.next().charAt(0));
        }
        return setup;
    }

    //Checks to see if these 8 bits are equal to C
    boolean bitCheck(Queue<Character> queue){
        Queue<Character> local = new LinkedList<>(queue);
        String binary = "";
        for (int i = 0; i < 8; i++) {
            binary += local.remove();
        }
        int decimal = Integer.parseInt(binary,2);
        char letter = (char) decimal;

        return (letter == 'C');
    }
    //Reads in the next 8 bits, and converts to a char
    char getLetter(Scanner scan){

        String binary = "";
        for (int i = 0; i < 8; i++) {
            binary += scan.next().charAt(0);
        }
        int decimal = Integer.parseInt(binary,2);
        char letter = (char) decimal;
        return letter;
    }

    //Checks to see if the rest of the preamble string is correct
    boolean checkFlag(Scanner scan){
        char[] correct = {'A','P','T','I','V','A','T','I','O','N'};
        for (int i = 0; i < 10; i++) {
            if(getLetter(scan) != correct[i]){
                return false;
            }
        }
        return true;
    }

    //Prints the next 100 characters
    void printHundred(Scanner scan){
        String output = "";
        for (int i = 0; i < 100; i++) {
            output += getLetter(scan);
        }
        System.out.println(output);
    }
}
