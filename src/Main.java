/*
Charles Lett Jr.
February 23, 2022
DESC: Program for 'SETS' homework

INSTRUCTIONS:
1. Write an OOP Program to determine if the user input is an element of a set A
A = { 2, 1, 6, 3, 2, 9, 3}



2. Write an OOP Program to determine if set A and B are equl
A = { 2, 1, 6, 3, 2, 9, 3}
B = { 3, 9, 3, 6, 2, 1, 2}


3. Write an OOP Program to determine if  set A and B are a subset of set C
A = { 2, 1, 6, 3, 2, 9, 3}
B = {7, 4, 6, 1, 3,9}
C = {1, 2, 3, 4, 6, 9, 8}
 */

public class Main {
    final static int ASSIGN_TO_CHECK = 3;   // choose which part of the HW to check

    public static void main(String[] args) throws InterruptedException {
        // assignment 1. >> CHECK FOR USER INPUT IN SET
        if(ASSIGN_TO_CHECK == 1) {
            assign1 a1 = new assign1();
            a1.run();
        }

        // assignment 2. >> CHECK IF SET MATCH
        if(ASSIGN_TO_CHECK == 2){
            assign2 a2 = new assign2();
            a2.run();
        }

        // assignment 3. >> CHECK IF SETS ARE SUBSETS OF ANOTHER SET
        if(ASSIGN_TO_CHECK == 3){
            int[] subset1 = {2, 1, 6, 3, 2, 9, 3};
            int[] subset2 = {7, 4, 6, 1, 3, 9};
            int[] master_set = {1, 2, 3, 4, 6, 9, 8};

            assign3 a3 = new assign3();
            a3.run(subset1, subset2, master_set);
        }
    }
}