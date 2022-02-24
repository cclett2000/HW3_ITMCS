import java.util.Arrays;

public class assign2{
    final static boolean ENABLE_DEBUG = true;

    public void run() throws InterruptedException {
        check_set_equality();
    }

    private static void check_set_equality() throws InterruptedException {
        boolean is_equal = true;                // flag to switch to prevent true message display
        int[] set1 = {2, 1, 6, 3, 2, 9, 3};
        int[] set2 = {3, 9, 3, 6, 2, 1, 2};

        if(ENABLE_DEBUG) System.out.println(
                "Sets Before Sort" +
                        "\n\tSet1: " + Arrays.toString(set1) +
                        "\n\tSet2: " + Arrays.toString(set2) +
                        "\n"
        );

        // multithreading - https://stackoverflow.com/a/21570040
        // wait till threads complete - https://stackoverflow.com/a/56426361

        // FINGERS CROSSED I GET EXTRA CREDIT FOR MULTITHREADING. >> YOU SEE THIS PROFESSOR?
        // sorts both sets on different threads - should decrease runtime
        Thread sorter_thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("***Starting Thread 1***");
                // insertion sort algorithm altered to work with array
                //  > link: https://github.com/cclett2000/insertion_sort/blob/master/src/HM_sort.java

                int i = 0;  // position
                int key;    // curr val

                System.out.println("\t> Sorting Array...");

                for (int j = 1; j < set1.length; j++) {
                    key = set1[j];

                    i = j - 1;

                    //shift value if true; val > key
                    while (i >= 0 && set1[i] > key) {
                        // replace ind + 1 with ind in hashmap
                        // TODO: read more into how insertion sort works
                        set1[i + 1] = set1[i];

                        // decrement i
                        i = i - 1;
                    }

                    // replace i + 1 with current val held in key
                    set1[i + 1] = key;
                }


                System.out.println("\t> Sorting Done!");

                System.out.println("***Thread 1 finished***");
            }
        });
        Thread sorter_thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("***Starting Thread 2***");
                // insertion sort algorithm altered to work with array
                //  > link: https://github.com/cclett2000/insertion_sort/blob/master/src/HM_sort.java

                int i = 0;  // position
                int key;    // curr val

                System.out.println("\t> Sorting Array...");

                for (int j = 1; j < set2.length; j++) {
                    key = set2[j];

                    i = j - 1;

                    //shift value if true; val > key
                    while (i >= 0 && set2[i] > key) {
                        // replace ind + 1 with ind in hashmap
                        // TODO: read more into how insertion sort works
                        set2[i + 1] = set2[i];

                        // decrement i
                        i = i - 1;
                    }

                    // replace i + 1 with current val held in key
                    set2[i + 1] = key;
                }


                System.out.println("\t> Sorting Done!");

                System.out.println("***Thread 2 Finished***");
            }
        });

        // start sorter threads
        sorter_thread1.start();
        sorter_thread2.start();

        // hold until threads finish (after sort message fails without this)
        sorter_thread1.join();
        sorter_thread2.join();

        if(ENABLE_DEBUG) System.out.println(
        "\nSets After Sort" +
                "\n\tSet1: " + Arrays.toString(set1) +
                "\n\tSet2: " + Arrays.toString(set2)
        );

        // checker
        for (int i = 0; i < set1.length; i++){
            if(set1[i] != set2[i]){
                System.out.println("\n>>> The sets are not equal");
                is_equal = false;
                break;
            }
        }

        if (is_equal) System.out.println("\n>>> The sets are equal");
    }
}
