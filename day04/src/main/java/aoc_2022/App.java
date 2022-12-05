package aoc_2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
    public static void main( String[] args ) throws FileNotFoundException {
        ArrayList<String[]> ranges = getRanges();

        int coveredTwice = 0;
        int overlapping = 0;

        for (String[] rangePair : ranges) {
            String[] range1 = rangePair[0].split("-");
            String[] range2 = rangePair[1].split("-");

            Integer startA = Integer.parseInt(range1[0]);
            Integer endA = Integer.parseInt(range1[1]);
            Integer startB = Integer.parseInt(range2[0]);
            Integer endB = Integer.parseInt(range2[1]);

            if (rangeInsideRange(startA, endA, startB, endB)) {
                coveredTwice += 1;
            }

            if (rangesOverlap(startA, endA, startB, endB)) {
                overlapping += 1;
            }
        }

        System.out.println("Number of ranges fully contained in another range: " + coveredTwice);
        System.out.println("Number of overlapping ranges: " + overlapping);
    }

    private static ArrayList<String[]> getRanges() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        ArrayList<String[]> ranges = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                ranges.add(line.split(","));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ranges;
    }

    private static boolean rangeInsideRange(Integer startA, Integer endA, Integer startB, Integer endB) {
        return (startA >= startB && endA <= endB) || (startB >= startA && endB <= endA);
    }

    private static boolean rangesOverlap(Integer startA, Integer endA, Integer startB, Integer endB) {
        return (endA >= startB && endA <= endB) ||
                (startA >= startB && startA <= endB) ||
                rangeInsideRange(startA, endA, startB, endB);
    }
}
