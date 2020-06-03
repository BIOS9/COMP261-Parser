package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.BlockNode;
import main.java.parser.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class ConditionNode {
    private static final Pattern
            EQUALS = Pattern.compile("eq"),
            GREATERTHAN = Pattern.compile("gt"),
            LESSTHAN = Pattern.compile("lt"),
            AND = Pattern.compile("and"),
            OR = Pattern.compile("or"),
            NOT = Pattern.compile("not");

    public abstract boolean evaluate(Robot robot);

    public static ConditionNode parse(Scanner s, BlockNode parentBlock) {
        if(FixedBooleanNode.canParse(s)) {
            return FixedBooleanNode.parse(s);
        } else if (Parser.checkFor(EQUALS, s)) {
            return EqualsNode.parse(s, parentBlock);
        } else if (Parser.checkFor(GREATERTHAN, s)) {
            return GreaterThanNode.parse(s, parentBlock);
        } else if (Parser.checkFor(LESSTHAN, s)) {
            return LessThanNode.parse(s, parentBlock);
        } else if (Parser.checkFor(AND, s)) {
            return AndNode.parse(s, parentBlock);
        } else if (Parser.checkFor(OR, s)) {
            return OrNode.parse(s, parentBlock);
        } else if (Parser.checkFor(NOT, s)) {
            return NotNode.parse(s, parentBlock);
        } else {
            Parser.fail("Expected next token to be condition.", s);
            return null;
        }
    }
}
