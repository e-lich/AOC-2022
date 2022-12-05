package aoc_2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        ArrayList<String> rounds = getRounds();

        int[][] res1 = {{4, 8, 3},
                {1, 5, 9},
                {7, 2, 6}};

        int[][] res2 = {{3, 4, 8},
                {1, 5, 9},
                {2, 6, 7}};

        int sum1 = 0;
        int sum2 = 0;

        for (String round : rounds) {
            String[] players = round.split(" ");
            int int1 = Integer.parseInt(players[0]);
            int int2 = Integer.parseInt(players[1]);

            sum1 += res1[int1][int2];
            sum2 += res2[int1][int2];
        }

        System.out.println("The result of the first encoding is: " + sum1);
        System.out.println("The result of the second encoding is: " + sum2);
    }

    private static ArrayList<String> getRounds() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        ArrayList<String> rounds = new ArrayList<>();

        try {;
            String line = reader.readLine();
            while (line != null) {
                rounds.add(line.replace("A", "0")
                        .replace("B", "1")
                        .replace("C", "2")
                        .replace("X", "0")
                        .replace("Y", "1")
                        .replace("Z", "2"));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rounds;
    }
}
