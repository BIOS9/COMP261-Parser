package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;
import main.java.game.executionnodes.SensorNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class EqualsNode extends NumericComparisonNode {

    public EqualsNode(SensorNode sensor, NumberNode number) {
        super(sensor, number);
    }

    @Override
    public boolean evaluate(Robot robot) {
        if(robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        return sensor.getValue(robot) == number.getValue(robot);
    }

    public static EqualsNode parse(Scanner s) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        SensorNode sensor = SensorNode.parse(s);
        Parser.require(Parser.COMMA, "Expected comma.", s);
        NumberNode number = NumberNode.parse(s);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new EqualsNode(sensor, number);
    }

    @Override
    public String toString() {
        return "EqualsNode{" +
                "sensor=" + sensor +
                ", number=" + number +
                '}';
    }
}
