package aoc_2022;

import java.util.ArrayList;
import java.util.LinkedList;

public class CrateStack {
    private LinkedList<String> stack;

    public CrateStack() {
        stack = new LinkedList<>();
    }

    public void pushToFill(String crate) {
        stack.addFirst(crate);
    }

    public void push(String crate) {
        stack.add(crate);
    }

    public String pop() {
        if (stack.size() == 0) {
            return null;
        }
        return stack.removeLast();
    }

    public String peek() {
        if (stack.size() == 0) {
            return null;
        }
        return stack.getLast();
    }
}
