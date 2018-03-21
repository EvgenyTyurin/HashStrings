import Tyurin.Evgeny.StringHash.StringHash;
import java.util.Scanner;

/*

    Robert Lafore "Data Structures And Algorithms in Java"
    Chapter 11 Exercise 2: Hash strings

    by Evgeny Tyurin

*/

public class Lafore11E2 {

    // Run point
    public static void main(String[] args) {

        // Prepare to hash english letters and some marks
        StringHash hash = new StringHash("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ;,!?");
        System.out.println("Input strings to hash or 'q' to quit");
        /* User input cycle for hash some strings */
        Scanner con = new Scanner(System.in);
        do {
            System.out.println("Input: ");
            String inputStr = con.nextLine();
            if (inputStr.equals("q"))
                break;
            int hashCode = hash.hash(inputStr);
            System.out.println("Hash table: " + hash);
            System.out.println("Table[" + hashCode + "] = " + hash.getHashMap()[hashCode]);
            System.out.println("Hash of input str = " + hash.getHash(inputStr));
        } while (true);

    }

}
