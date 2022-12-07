package aoc_2022;

import java.util.HashMap;

public class Directory {
    private final Directory parent;
    private HashMap<String, Directory> children;
    private final String name;
    private long size;

    public Directory(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        this.size = 0;
        this.children = new HashMap<>();
    }

    public Directory getParent() {
        return parent;
    }

    public Directory getChild(String name) {
        return children.get(name);
    }

    public HashMap<String, Directory> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public void addChild(Directory child) {
        children.put(child.getName(), child);
    }

    public void increaseSize(long value) {
        if (parent != null) {
            parent.increaseSize(value);
        }
        size += value;
    }

    public boolean isSmall() {
        return size < 100000;
    }

    public Directory findBigEnoughChild(long size, Directory dir) {
        Directory bigEnoughChild = dir;
        for (String childName : children.keySet()) {
            Directory child = children.get(childName);
            if (child.getSize() >= size) {
                if (bigEnoughChild == null || child.getSize() < bigEnoughChild.getSize()) {
                    bigEnoughChild = child;
                }
                if (child.children.size() > 0) {
                    bigEnoughChild = child.findBigEnoughChild(size, bigEnoughChild);
                }
            }
        }
        return bigEnoughChild;
    }
}
