package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public class FixedBooleanNode extends ConditionNode {
    public final boolean value;
    private static final Pattern
            TRUE = Pattern.compile("true"),
            FALSE = Pattern.compile("false");

    public FixedBooleanNode(boolean value) {
        this.value = value;
    }

    public static boolean canParse(Scanner s) {
        return s.hasNext(TRUE) || s.hasNext(FALSE);
    }

    public static FixedBooleanNode parse(Scanner s) {
        if(Parser.checkFor(TRUE, s)) {
            return new FixedBooleanNode(true);
        } else if(Parser.checkFor(FALSE, s)) {
            return new FixedBooleanNode(false);
        }

        Parser.fail("Expected boolean.", s);
        return null;
    }

    @Override
    public boolean evaluate(Robot robot) {
        return value;
    }

    @Override
    public String toString() {
        return "FixedBooleanNode{" +
                "value=" + value +
                '}';
    }
}
