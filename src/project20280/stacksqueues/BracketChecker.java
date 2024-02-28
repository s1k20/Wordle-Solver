package project20280.stacksqueues;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }


        // TODO
        public boolean check (String s) {

            LinkedStack<Character> Buffer = new LinkedStack<>();

            final String opening = "([{";
            final String closing = ")]}";

            for (char c : s.toCharArray()) {
                if (opening.indexOf(c) != -1) {
                    Buffer.push(c);
                } else if (closing.indexOf(c) != -1) {
                    if (Buffer.isEmpty()) {
                        return false;
                    }
                    if (opening.indexOf(Buffer.pop()) != closing.indexOf(c)) {
                        return false;
                    }
                }

            }
            return Buffer.isEmpty();
        }


    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            System.out.println(checker.check(input));
        }
    }
}