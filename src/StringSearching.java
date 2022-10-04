// JAVA program for implementation of KMP pattern 
// searching algorithm

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Object;

import static java.lang.System.nanoTime;

class StringSearching {

    public static int bruteForceSearch(String txt, String pat)
    {
        int n = txt.length();
        int m = pat.length();

        for(int i = 0; i < n; i++) {
            int offset = i;
            int count = 0;
            for(int j = 0; j < m && offset < n; j++) {
                if(txt.charAt(offset) == pat.charAt(j)) {
                    // iterate through string until mismatch
                    count++;
                    offset++;
                }
                if(count == pat.length()) {
                    // if string is found
                    System.out.println("Pattern, " + pat + ", found at index: " + i);
                    return i;
                }
            }
        }
        System.out.println("Pattern, " + pat + ", not found");
        return -1;
    }

    public int KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        //index for txt
        int i = 0;

        while(i < N) {
            if(pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if(j == M) {
                System.out.println("Pattern, " + pat + ", found at index: " + (i - j));
                return (i - j);
            } else if(i < N && pat.charAt(j) != txt.charAt(i)) {
                if(j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        System.out.println("Pattern, " + pat + ", not found");
        return -1;
    }

    void computeLPSArray(String pat, int M, int[] lps)
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    // Driver program to test above function
    public static void main(String[] args)
    {
        if(args.length > 0) {
            try{
                String pat = "me along, Queequeg, let";
                StringBuilder txt = new StringBuilder();
                Scanner input = new Scanner(new File(args[0]));
                while(input.hasNextLine()){
                    txt.append(input.nextLine());
                }
                final long startTimeKMP = nanoTime();
                new StringSearching().KMPSearch(pat, txt.toString());
                final long timeTakenKMP = nanoTime() - startTimeKMP;
                System.out.println("Time taken for KMP sort: " + timeTakenKMP + "\n");

                final long startTimeBF = nanoTime();
                bruteForceSearch(txt.toString(), pat);
                final long timeTakenBF = nanoTime() - startTimeBF;
                System.out.println("Time taken for Brute Force sort: " + timeTakenBF + "\n");

            } catch(FileNotFoundException ex) {
                System.out.println("No such file");
            }
        } else {
            System.out.println("No args given");
        }
    }
}