package dev.manyroads.stacks.bracketsarebalanced.example1;

class Main {
    public static void main(String[] args) {
        String braces = new java.util.Scanner(System.in).nextLine();
        var deque = new java.util.ArrayDeque<Character>();
        var pairs = java.util.Map.of(')', '(', '}', '{', ']', '[');

        for (int i = 0; i < braces.length(); i++) {
            char currentChar = braces.charAt(i);
            if ("({[".contains(currentChar + "")) {
                deque.offerLast(currentChar);
            } else if (pairs.get(currentChar) == deque.peekLast()) {
                deque.pollLast();
            } else {
                System.out.println(false);
                return;
            }
        }
        System.out.println(deque.isEmpty());
    }
}
