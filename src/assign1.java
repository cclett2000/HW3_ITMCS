import java.util.Arrays;
import java.util.Scanner;



public class assign1 {
    public void run(){
        check_num_in_set();
    }

    private static void check_num_in_set(){
        // init
        boolean not_found_flag = false;             // checks and prevents not found message if found
        Scanner input = new Scanner(System.in);
        int[] set = {2, 1, 6, 3, 2, 9, 3};

        // get num
        System.out.print(
                "Set: " + Arrays.toString(set) +
                "\nEnter number to check against set: "
        );
        int num = input.nextInt();

        // check alg
        for (int i = 0; i < set.length; i++){
            if(num == set[i]){
                System.out.println("\n>>> " + num + " found in set\n>>> Index = " + i + "\n");
                not_found_flag = true;
                break;
            }
        }

        // !not_found_flag == not_found_flag = false
        if(!not_found_flag) System.out.println("\n>>> " + num + " not found in set");
    }
}
