package dev.manyroads.utf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Unicode Translation Format
 * ASCII - 1 byte (7bits)
 * Unicode - encoding/decoding every possible character in the world, in hexa
 * Unicode Transformation Format (UTF) - encodes UNICODE code points into bits
 * UTF-32 - every code point on 4 bytes (32 bits), not popular
 * UTF-16 - every code point on 2 or 4 bytes
 * UTF-8 - every code point on 1, 2, 3, or 4 bytes, very efficient
 * JAVA - code points less than 65,535 (0xFFFF), we can rely on the charAt()
 *
 */
public class UTFTests {

    static String myString = "AB";
    static ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(myString);
    static int decimalA = "A".charAt(0);

    public static void main(String[] args) {
        System.out.println("myString: " + myString);
        System.out.println("byteBuffer: " + byteBuffer);
        System.out.println("decimalA: " + decimalA);
        System.out.println("strToBinary: " + strToBinary(myString));

    }

    public static String strToBinary(String str) {
        String binary = str
                .chars()
                .mapToObj(Integer::toBinaryString)
                .peek(System.out::println)
                .map(t -> "0" + t)
                .peek(System.out::println)
                .collect(Collectors.joining(" "));
        return binary;
    }
}
