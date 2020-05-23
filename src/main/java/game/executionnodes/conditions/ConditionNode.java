package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class ConditionNode {
    private static final Pattern
            EQUALS = Pattern.compile("eq"),
            GREATERTHAN = Pattern.compile("gt"),
            LESSTHAN = Pattern.compile("lt");

    public abstract boolean evaluate(Robot robot);

    public static ConditionNode parse(Scanner s) {
        if (Parser.checkFor(EQUALS, s)) {
            return EqualsNode.parse(s);
        } else if (Parser.checkFor(GREATERTHAN, s)) {
            return GreaterThanNode.parse(s);
        } else if (Parser.checkFor(LESSTHAN, s)) {
            return LessThanNode.parse(s);
        } else {
            Parser.fail("Expected next token to be condition.", s);
            return null;
        }
    }
}
