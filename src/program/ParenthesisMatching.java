package program;

import collections.container.stack.LinkedStack;
import collections.container.stack.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ParenthesisMatching {
    private static final String[] SUPPORT_PARENTHESIS = {"()", "[]", "{}"};

    public static void main(String... args) {
        while (StdIn.hasNextLine()) {
            var expr = StdIn.readAll();
            if (parenthesisMatchingChecker(expr)) {
                StdOut.println("OK");

            } else {
                StdOut.println("ERROR");
            }
        }
    }

    public static boolean parenthesisMatchingChecker(String expr) {
        Stack openningParenStack = new LinkedStack();

        for (int i = 0; i < expr.length(); i++) {
            var ch = expr.charAt(i);
            if (isOpenningParenthesis(ch)) {
                openningParenStack.push(ch);

            } else if (isClosingParenthesis(ch)) {
                if (openningParenStack.isEmpty()) {
                    return false;
                }

                var openningParen = (Character) openningParenStack.pop();
                if (!isOpenningAndClosingParenMatching(openningParen, ch)) {
                    return false;
                }
            }
        }

        return openningParenStack.isEmpty();
    }

    public static Iterator<Character> allOpenningParenthesis() {
        return new Iterator<Character>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < SUPPORT_PARENTHESIS.length;
            }

            @Override
            public Character next() {
                return SUPPORT_PARENTHESIS[index++].charAt(0);
            }
        };
    }

    public static Iterator<Character> allClosingParenthesis() {
        return new Iterator<Character>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < SUPPORT_PARENTHESIS.length;
            }

            @Override
            public Character next() {
                return SUPPORT_PARENTHESIS[index++].charAt(1);
            }
        };
    }

    private static boolean isCharInChars(char ch, Iterator<Character> chars) {
        while (chars.hasNext()) {
            var c = chars.next();
            if (c.equals(ch)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isOpenningParenthesis(char ch) {
        return isCharInChars(ch, allOpenningParenthesis());
    }

    private static boolean isClosingParenthesis(char ch) {
        return isCharInChars(ch, allClosingParenthesis());
    }

    private static boolean isOpenningAndClosingParenMatching(char openning, char closing) {
        for (var parents : SUPPORT_PARENTHESIS) {
            var theParents = String.format("%c%c", openning, closing);
            if (parents.equals(theParents)) {
                return true;
            }
        }

        return false;
    }

}
