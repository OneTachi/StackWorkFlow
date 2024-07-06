package com.example.stackworkflow;

import java.util.ArrayDeque;
import java.util.Deque;

public class WorkStack {
    // Stack of strings that hold the task's name
    Deque<String> stack = new ArrayDeque<String>();
    // Number of tasks visible in front of the user -- number of blocks user will see
    private int visibleTasks = 1;
    // Experimental
    private static final int maxVisibleTasks = 3;

    public void add(String taskName) {
        stack.add(taskName);
    }

    public void delete() {
        stack.clear();
    }
}
