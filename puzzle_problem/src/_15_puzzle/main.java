package _15_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static int[][] final_state = new int[4][4];
    public static int n = 4 ;
    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File("in.txt"));
        int input_count = scanner.nextInt();
        System.out.println(input_count);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(scanner.hasNextInt())
                    final_state[i][j] = scanner.nextInt();
            }
        }

        System.out.println("");
        int[][] initial_state = new int[4][4];
        for (int k = 0; k < input_count ; k++) {
            System.out.println("Using input "+ (k+1)+"........................");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if(scanner.hasNextInt())
                        initial_state[i][j] = scanner.nextInt();
                }
            }

            System.out.println("----------------the misplaced tiles/displacement heuristic--------------------");
             long startTime = System.currentTimeMillis();
            Search_displacement search = new Search_displacement();
            search.displacement_heuristic_search(initial_state);

             long duration = System.currentTimeMillis() - startTime;
            //System.out.println("Duration time of misplaced tiles huristic : "+duration);

            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("----------------manhattan heuristic-------------------------------------------");
            startTime = System.currentTimeMillis();
            Search_Manhattan search_manhattan = new Search_Manhattan();
            search_manhattan.Manhattan_heuristic_search(initial_state);

             duration = System.currentTimeMillis() - startTime;
            //System.out.println("Duration time of manhattan huristic : "+duration);

            System.out.println();
            System.out.println("................................................");
            System.out.println();
        }












    }
}
