package Tyurin.Evgeny.StringHash;

import java.util.HashMap;

/*
    Class for calculate and store hashes of strings

    by Evgeny Tyurin

*/

public class StringHash {

    /* Hash range */
    private int hashMax = 10000000;
    /* Hash storage: idx in array = hash code */
    private String[] hashMap = new String[hashMax];
    /* Character codes */
    private HashMap<Character, Integer> charMap = new HashMap<>();

    /* Constructs StringHash object with alphabet */
    public StringHash(String alphabet) {
        for(int idx=0; idx < alphabet.length(); idx++)
            charMap.put(alphabet.charAt(idx), idx + 1);
    }

    // Hash string into hash table
    public int hash(String str) {
        int hash = calcHash(str);
        if (hash > 0)
            getHashMap()[hash] = str;
        return hash;
    }

    // Get hash of string
    public int getHash(String str) {
        int hash = calcHash(str);
        if (getHashMap()[hash] != null)
            return hash;
        else
            return 0;
    }

    // For printing hash map
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int arrayIdx = 0; arrayIdx < getHashMap().length; arrayIdx ++) {
            if (getHashMap()[arrayIdx] != null)
                sb.append(arrayIdx).append("=").append(getHashMap()[arrayIdx]).append(" ");
        }
        return sb.toString();
    }

    // Getter for hash map array
    public String[] getHashMap() {
        return hashMap;
    }

    // Calculates int hash of string,
    // returns 0 if something goes wrong
    private int calcHash(String str) {
        // Horner's method
        int hashVal = 0;
        for (int j=0; j<str.length(); j++) {
            // Char not in alphabet - return 0
            if (!charMap.containsKey(str.charAt(j)))
                return 0;
            int letter = charMap.get(str.charAt(j));
            hashVal = (hashVal * (charMap.size() + 1) + letter) % hashMax;
        }
        // Something going wrong
        if (hashVal <= 0)
            return 0;
        // Linear probation
        boolean cycled = false;
        do {
            // Out of hash range - try again from beginning
            if (hashVal >= hashMax) {
                // It's second cycle - time to go out
                if (cycled)
                    return 0;
                hashVal = 1;
                cycled = true;
            }
            // Empty place in hash table - return it
            if (getHashMap()[hashVal] == null)
                return hashVal;
            // Place is not empty, but equals hashing str - return it
            if (getHashMap()[hashVal].equals(str))
                return hashVal;
            // Not suitable place in hash table - try next
            hashVal++;
        } while (true);
    }

}
