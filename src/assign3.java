import java.util.Arrays;

public class assign3 {
    public void run(int[] subset1, int[] subset2, int[] set_to_check_against) throws InterruptedException {
        subset_checker(subset1, subset2, set_to_check_against);
    }

    private static void subset_checker(int[] subset1, int[] subset2, int[] master_set) throws InterruptedException {
        Thread sortMasterSet = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("***Starting Thread 1***");
                // insertion sort algorithm altered to work with array
                //  > link: https://github.com/cclett2000/insertion_sort/blob/master/src/HM_sort.java

                int i = 0;  // position
                int key;    // curr val

                System.out.println("\t> [THREAD1] Sorting Master Set...");

                for (int j = 1; j < master_set.length; j++) {
                    key = master_set[j];

                    i = j - 1;

                    //shift value if true; val > key
                    while (i >= 0 && master_set[i] > key) {
                        // replace ind + 1 with ind in hashmap
                        // TODO: read more into how insertion sort works
                        master_set[i + 1] = master_set[i];

                        // decrement i
                        i = i - 1;
                    }

                    // replace i + 1 with current val held in key
                    master_set[i + 1] = key;
                }


                System.out.println("\t> [THREAD1] Sorting Done!");
                System.out.println("***Thread 1 Finished***");
            }
        });        // master worker
        Thread checkThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("***Starting Thread 2***");
                // insertion sort algorithm altered to work with array
                //  > link: https://github.com/cclett2000/insertion_sort/blob/master/src/HM_sort.java

                int i = 0;                                                  // position
                int key;                                                    // curr val
                boolean[] sub_status = new boolean[subset1.length];         // stores bool val for each number in subset - true if in master_set
                boolean is_subset = true;

                System.out.println("\t> [THREAD2] Sorting Subset 1...");

                for (int j = 1; j < subset1.length; j++) {
                    key = subset1[j];

                    i = j - 1;

                    //shift value if true; val > key
                    while (i >= 0 && subset1[i] > key) {
                        // replace ind + 1 with ind in hashmap
                        // TODO: read more into how insertion sort works
                        subset1[i + 1] = subset1[i];

                        // decrement i
                        i = i - 1;
                    }

                    // replace i + 1 with current val held in key
                    subset1[i + 1] = key;
                }

                // wait till master thread is finished before continuing
                // - should allow the sorting of all three sets around the
                //   same time?

                try {
                    sortMasterSet.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("\t> [THREAD2] Sorting Done!" +
                        "\n\t> [THREAD2] Starting Check Against Master...");


                // subset cannot be same size or larger than master set
                if (subset1.length >= master_set.length){
                    System.out.println("\t>>> [THREAD2] Subset 1 is not a subset of Master Set - (Subset size >= Master size)");
                }

                // TODO: FIX BUG - DOESN'T PROPERLY DETECT DUPLICATES
                // check each value against master set
                else {
                    // if val in subset == master, set its status to true
                    for (int sub_inc = 0; sub_inc < subset1.length; sub_inc++) {
                        for (int mas_inc = 0; mas_inc < master_set.length; mas_inc++) {
                            if (subset1[sub_inc] == master_set[mas_inc]) {
                                sub_status[sub_inc] = true;
                            }
                        }
                    }

                    // check every values status to dictate if subset is part of master set
                    for (int chk_inc = 0; chk_inc < sub_status.length; chk_inc++) {
                        if (sub_status[chk_inc] == false) {
                            System.out.println(">>> [THREAD2] Subset1 is not a subset of Master Set -> " + Arrays.toString(sub_status));
                            is_subset = false;
                            break;
                        }
                    }

                    if (is_subset) System.out.println(">>> [THREAD2] Subset1 is a subset of Master set -> " + Arrays.toString(sub_status));
                }

                    System.out.println("***Thread 2 Finished***");
            }
        });         // subset1 worker
        Thread checkThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("***Starting Thread 3***");
                // insertion sort algorithm altered to work with array
                //  > link: https://github.com/cclett2000/insertion_sort/blob/master/src/HM_sort.java

                int i = 0;                                                  // position
                int key;                                                    // curr val
                boolean[] sub_status2 = new boolean[subset2.length];        // stores bool val for each number in subset - true if in master_set
                boolean is_subset = true;

                System.out.println("\t> [THREAD3] Sorting Subset 2...");

                for (int j = 1; j < subset2.length; j++) {
                    key = subset2[j];

                    i = j - 1;

                    //shift value if true; val > key
                    while (i >= 0 && subset2[i] > key) {
                        // replace ind + 1 with ind in hashmap
                        // TODO: read more into how insertion sort works
                        subset2[i + 1] = subset2[i];

                        // decrement i
                        i = i - 1;
                    }

                    // replace i + 1 with current val held in key
                    subset2[i + 1] = key;
                }

                // wait till master thread is finished before continuing
                // - should allow the sorting of all three sets around the
                //   same time?
                try {
                    sortMasterSet.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("\t> [THREAD3] Sorting Done!" +
                        "\n\t> [THREAD3] Starting Check Against Master...");


                // subset cannot be same size or larger than master set
                if (subset2.length >= master_set.length){
                    System.out.println("\t>>> [THREAD3] Subset 2 is not a subset of Master Set - (Subset size >= Master size)");
                }

                // TODO: FIX BUG - DOESN'T PROPERLY DETECT DUPLICATES
                // check each value against master set
                else {
                    // if val in subset == master, set its status to true
                    for (int sub_inc = 0; sub_inc < subset2.length; sub_inc++) {
                        for (int mas_inc = 0; mas_inc < master_set.length; mas_inc++) {
                            if (subset2[sub_inc] == master_set[mas_inc]) {
                                sub_status2[sub_inc] = true;
                            }
                        }
                    }

                    // check every values status to dictate if subset is part of master set
                    for (int chk_inc = 0; chk_inc < sub_status2.length; chk_inc++) {
                        if (sub_status2[chk_inc] == false) {
                            System.out.println("\t>>> [THREAD3] Subset 2 is not a subset of Master Set -> " + Arrays.toString(sub_status2));
                            is_subset = false;
                            break;
                        }
                    }

                    if (is_subset) System.out.println("\t>>> [THREAD3] Subset 2 is a subset of Master Set -> " + Arrays.toString(sub_status2));
                }
                System.out.println("***Thread 3 Finished***");

            }
        });         // subset2 worker

        // sets before sort and check
        System.out.println(
                "\nMaster Set: " + Arrays.toString(master_set) +
                "\nSubset1: " + Arrays.toString(subset1) +
                "\nSubset2: " + Arrays.toString(subset2) +
                "\n"
        );

        sortMasterSet.start();
        //sortMasterSet.join();       // wait till master set is sorted before continuing

        checkThread1.start();
        checkThread2.start();

        // wait till worker threads are finished before continuing
        checkThread2.join();

        // sets after sort and check
        System.out.println(
                "\nMaster Set: " + Arrays.toString(master_set) +
                "\nSubset1: " + Arrays.toString(subset1) +
                "\nSubset2: " + Arrays.toString(subset2)
        );
    }
}
