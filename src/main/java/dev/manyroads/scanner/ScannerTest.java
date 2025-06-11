package dev.manyroads.scanner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
Implement the method toPrimitive(). It should take a value of Boolean type and return a boolean.
If the passed value is null, the result should be false.
 */
public class ScannerTest {

    static boolean toPrimitive(Boolean aBoolean) {
        return aBoolean == null ? false : aBoolean;
    }

    public static int calculate(int i1, int i2) {
        return i1 * i2;
    }

    public static int calculate(int i1, int i2, int i3) {
        return (i1 + i2) * i3;
    }


    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String sInts = scanner.nextLine();
        String[] ints = sInts.split("\\s+");
        int nrInts = ints.length;
        switch (nrInts) {
            case 2 -> System.out.println(calculate(Integer.parseInt(ints[0]), Integer.parseInt(ints[1])));
            case 3 ->
                    System.out.println(calculate(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]), Integer.parseInt(ints[2])));
            default -> System.out.println("Unknown type found");
        }
    }
}

class ex1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            //try (Scanner sc = new Scanner(System.in).useDelimiter("\\n")) {
            Deque<String> stack = new ArrayDeque<>();
            do {
                stack.add(sc.next());
            }
            while (sc.hasNext());

            while (!stack.isEmpty()) System.out.println(stack.pollLast());
        }
    }
}

class ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String guestList = new String();

        while (scanner.hasNext()) {
            guestList = scanner.next() + "\n" + guestList;
        }

        System.out.print(guestList);
    }
}

class ex3 {
    public static void main(String[] args) {
        var words = new Scanner(System.in).tokens().collect(Collectors.toList());
        Collections.reverse(words);
        words.forEach(System.out::println);
    }
}

class ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> guestStack = new ArrayDeque<>();

        while (scanner.hasNext()) {
            guestStack.push(scanner.next());
        }

        guestStack.forEach(System.out::println);
    }
}
class ex5{
    public static void main(String[] args) {
        new Scanner(System.in)
                .tokens()
                .collect(Collectors.toCollection(ArrayDeque::new))
                .descendingIterator()
                .forEachRemaining(System.out::println);
    }
}

class ex6{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> arrayNameGuests = new ArrayList<>();
        while (scanner.hasNext()) {
            arrayNameGuests.add(scanner.next());
        }

        Collections.reverse(arrayNameGuests);
        arrayNameGuests.forEach(System.out::println);
    }
}
