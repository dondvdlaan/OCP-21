package dev.manyroads.projects.asciimirror.stage5.example1.function;

import java.util.function.IntUnaryOperator;

public record ReversedCodePoint() implements IntUnaryOperator {
    private static final String ORIGINAL = "<[({})]>/\\";
    private static final String REVERSED = ">])}{([<\\/";

    @Override
    public int applyAsInt(int codePoint) {
        var index = ORIGINAL.indexOf(codePoint);
        return index < 0 ? codePoint : REVERSED.charAt(index);
    }
}
