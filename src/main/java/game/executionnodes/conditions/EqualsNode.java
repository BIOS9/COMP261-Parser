package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;
import main.java.game.executionnodes.SensorNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class EqualsNode extends NumericComparisonNode {

    public EqualsNode(NumberNode number1, NumberNode number2) {
        super(number1, number2);
    }

    @Override
    public boolean evaluate(Robot robot) {
        if(robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        return number1.getValue(robot) == number2.getValue(robot);
    }

    public static EqualsNode parse(Scanner s) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        NumberNode number1 = NumberNode.parse(s);
        Parser.require(Parser.COMMA, "Expected comma.", s);
        NumberNode number2 = NumberNode.parse(s);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new EqualsNode(number1, number2);
    }

    @Override
    public String toString() {
        return "EqualsNode{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
