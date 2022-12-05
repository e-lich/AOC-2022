package aoc_2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.naturalOrder;

public class App
{
    public static void main( String[] args ) throws FileNotFoundException {
        ArrayList<ArrayList<String>> elves = getElves();

        Integer[] elfCalories = elves.stream()
                .mapToInt(elf -> elf.stream()
                        .mapToInt(Integer::parseInt)
                        .sum())
                .boxed()
                .toArray(Integer[]::new);


        System.out.println("The maximum number of calories carried is:");

        Arrays.stream(elfCalories)
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);

        System.out.println("Sum of top 3 elves' calories:");
        System.out.println(Arrays.stream(elfCalories)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum());
    }

    private static ArrayList<ArrayList<String>> getElves() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        ArrayList<ArrayList<String>> elves = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                ArrayList<String> elf = new ArrayList<>();
                while (line != null && !line.equals("")) {
                    elf.add(line);
                    line = reader.readLine();
                }
                elves.add(elf);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return elves;
    }
}
