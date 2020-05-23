package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;
import main.java.game.executionnodes.SensorNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class AndNode extends ConditionNode {
    public final ConditionNode condition1, condition2;

    public AndNode(ConditionNode condition1, ConditionNode condition2) {
        if (condition1 == null)
            throw new IllegalArgumentException("Condition1 cannot be null.");
        if (condition2 == null)
            throw new IllegalArgumentException("Condition2 cannot be null.");

        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    @Override
    public boolean evaluate(Robot robot) {
        return condition1.evaluate(robot) && condition2.evaluate(robot);
    }

    public static AndNode parse(Scanner s) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode condition1 = ConditionNode.parse(s);
        Parser.require(Parser.COMMA, "Expected comma.", s);
        ConditionNode condition2 = ConditionNode.parse(s);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new AndNode(condition1, condition2);
    }

    @Override
    public String toString() {
        return "AndNode{" +
                "condition1=" + condition1 +
                ", condition2=" + condition2 +
                '}';
    }
}
