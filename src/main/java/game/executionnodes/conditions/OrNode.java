package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.BlockNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class OrNode extends ConditionNode {
    public final ConditionNode condition1, condition2;

    public OrNode(ConditionNode condition1, ConditionNode condition2) {
        if(condition1 == null)
            throw new IllegalArgumentException("Condition1 cannot be null.");
        if(condition2 == null)
            throw new IllegalArgumentException("Condition2 cannot be null.");

        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    @Override
    public boolean evaluate(Robot robot) {
        return condition1.evaluate(robot) || condition2.evaluate(robot);
    }

    public static OrNode parse(Scanner s, BlockNode parentBlock) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode condition1 = ConditionNode.parse(s, parentBlock);
        Parser.require(Parser.COMMA, "Expected comma.", s);
        ConditionNode condition2 = ConditionNode.parse(s, parentBlock);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new OrNode(condition1, condition2);
    }

    @Override
    public String toString() {
        return "OrNode{" +
                "condition1=" + condition1 +
                ", condition2=" + condition2 +
                '}';
    }
}
