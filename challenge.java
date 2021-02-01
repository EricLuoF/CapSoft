
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Queue;

public class challenge {

    public static void main(String[] args)throws FileNotFoundException{
        boolean debug = true;

        //Reads in from file for testing
        File in = new File("C:\\Users\\Eric\\Desktop\\Code\\captivationchallengejava\\input.txt");
        Scanner scan = new Scanner(in);
        scan.useDelimiter("");

        //Actual scanner, should both work
        //Scanner scan = new Scanner(System.in);


        challenge test = new challenge();
        char check;

        //C = 01000011
        Queue<Character> queue = test.setup(scan);

        //since in theory this is a never ending stream
        while(scan.hasNext()){
            //if these 8 bits are = C
            if(test.bitCheck(queue)){
                if (test.checkFlag(scan)){
                    test.printHundred(scan);
                }
            }
            //Move forward by 1 bit
            if(scan.hasNext()) {
                queue.poll();
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
