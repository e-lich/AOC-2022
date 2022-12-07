package aoc_2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main( String[] args ) throws FileNotFoundException {
        ArrayList<String> commands = getCommands();
        Directory root = new Directory(null, "root");
        Directory current = root;
        long sumOfSmallerDirectories = 0;

        for (int i = 1; i < commands.size(); i++) {
            String command = commands.get(i);
            if (command.startsWith("$ cd")) {
                String newDirName = command.split(" ")[2];

                if (newDirName.equals("..")) {
                    if (current.isSmall()) {
                        sumOfSmallerDirectories += current.getSize();
                    }
                    current = current.getParent();
                } else {
                    current = current.getChild(newDirName);
                }
            } else if (command.startsWith("$ ls")) {
                i++;
                String outputLine = commands.get(i);
                while (!outputLine.startsWith("$")) {
                    if (outputLine.startsWith("dir")) {
                        Directory newDir = new Directory(current, outputLine.split(" ")[1]);
                        current.addChild(newDir);
                    } else {
                        current.increaseSize(Integer.parseInt(outputLine.split(" ")[0]));
                    }
                    i++;
                    if (i < commands.size()) {
                        outputLine = commands.get(i);
                    } else {
                        break;
                    }
                }
                i--;
            } else {
                System.out.println("Unknown command: " + command);
            }
        }

        while (current != root) {
            if (current.isSmall()) {
                sumOfSmallerDirectories += current.getSize();
            }
            current = current.getParent();
        }

        System.out.println("The sum of the total sizes of all directories that are smaller than 100000 is: "
                + sumOfSmallerDirectories);

        System.out.println("The total size of the root directory is: " + root.getSize());

        long requiredSpace = 30000000 - (70000000 - root.getSize());
        System.out.println("We must delete a directory with total size of at least: " + requiredSpace);

        Directory dirToDelete = root.findBigEnoughChild(requiredSpace, null);
        System.out.println("The smallest directory that is larger than the required space is: "
                + dirToDelete.getName() + " with size " + dirToDelete.getSize());
    }

    private static ArrayList<String> getCommands() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        ArrayList<String> commands = new ArrayList<>();

        try {
            String line = reader.readLine();
            while (line != null) {
                commands.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commands;
    }

}
