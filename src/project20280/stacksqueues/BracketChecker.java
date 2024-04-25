package project20280.stacksqueues;

import java.util.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        Stack<Character> stack = new Stack<>();
        boolean isCorrect = true;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            switch (ch) {
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        isCorrect = false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        isCorrect = false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        isCorrect = false;
                    }
                    break;
            }
            if (!isCorrect) {
                break;
            }
        }

        if (!stack.isEmpty()) {
            isCorrect = false;
        }

        System.out.println("checking: " + input + " - " + (isCorrect ? "correct" : "not correct"));
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct
                "a{b[c]d}e", // correct
                "a{b(c]d}e", // not correct; ] doesn't match (
                "a[b{c}d]e}", // not correct; nothing matches final }
                "a{b(c) ", // not correct; Nothing matches opening {

        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            checker.check();
        }
    }
}
