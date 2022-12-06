package aoc_2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class App {
    public static void main( String[] args ) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        ArrayList<CrateStack> stacks1 = getCrateStacks(reader);
        ArrayList<CrateStack> stacks2 = getCrateStacks(new BufferedReader(
                new FileReader("src/main/resources/input.txt")));

        ArrayList<String> instructions = getLines(reader);

        for (String instruction : instructions) {
            crateMover9000(instruction, stacks1);
        }

        StringBuilder output1 = new StringBuilder();
        for (CrateStack stack : stacks1) {
            output1.append(stack.peek());
        }

        System.out.println("Tops of stacks fot CrateMover 9000: " + output1);

        for (String instruction : instructions) {
            crateMover9001(instruction, stacks2);
        }

        StringBuilder output2 = new StringBuilder();
        for (CrateStack stack : stacks2) {
            output2.append(stack.peek());
        }

        System.out.println("Tops of stacks fot CrateMover 9001: " + output2);
    }

    private static ArrayList<CrateStack> getCrateStacks(BufferedReader reader) {
        ArrayList<CrateStack> stacks = new ArrayList<>();
        ArrayList<String> lines = getLines(reader);

        int numberOfCrateStacks = (lines.get(lines.size() -1).length() - 2) / 4 + 1;
        lines.remove(lines.size() - 1);

        for (int i = 0; i < numberOfCrateStacks; i++) {
            CrateStack stack = new CrateStack();
            stacks.add(stack);
        }

        for (String line : lines) {
            for (int i = 0, j = 0; i < line.length(); i += 3, j++) {
                String crate = line.substring(i + 1, i + 2);
                if (!crate.equals(" ")) {
                    stacks.get(j).pushToFill(crate);
                }
                i++;
            }
        }

        return stacks;
    }

    private static ArrayList<String> getLines(BufferedReader reader) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (!Objects.equals(line, "") && line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static void crateMover9000(String instruction, ArrayList<CrateStack> stacks) {
        String[] split = instruction.split(" ");
        int qty = Integer.parseInt(split[1]);
        int src = Integer.parseInt(split[3]) - 1;
        int dest = Integer.parseInt(split[5]) - 1;

        for (int i = 0; i < qty; i++) {
            String crate = stacks.get(src).pop();
            stacks.get(dest).push(crate);
        }
    }

    private static void crateMover9001(String instruction, ArrayList<CrateStack> stacks) {
        String[] split = instruction.split(" ");
        int qty = Integer.parseInt(split[1]);
        int src = Integer.parseInt(split[3]) - 1;
        int dest = Integer.parseInt(split[5]) - 1;

        CrateStack tempStack = new CrateStack();

        for (int i = 0; i < qty; i++) {
            String crate = stacks.get(src).pop();
            tempStack.push(crate);
        }

        for (int i = 0; i < qty; i++) {
            String crate = tempStack.pop();
            stacks.get(dest).push(crate);
        }
    }
}
