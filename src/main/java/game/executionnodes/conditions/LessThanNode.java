package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class LessThanNode extends ConditionNode {
    public final NumberNode number1, number2;

    public LessThanNode(NumberNode number1, NumberNode number2) {
        if(number1 == null)
            throw new IllegalArgumentException("Number1 cannot be null.");
        if(number2 == null)
            throw new IllegalArgumentException("Number2 cannot be null.");

        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public boolean evaluate(Robot robot) {
        if(robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        return number1.getValue(robot) < number2.getValue(robot);
    }

    public static LessThanNode parse(Scanner s) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        NumberNode number1 = NumberNode.parse(s, false);
        Parser.require(Parser.COMMA, "Expected comma.", s);
        NumberNode number2 = NumberNode.parse(s, true);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new LessThanNode(number1, number2);
    }

    @Override
    public String toString() {
        return "LessThanNode{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
