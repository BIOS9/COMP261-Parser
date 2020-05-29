package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.BlockNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class NotNode extends ConditionNode {
    public final ConditionNode condition;

    public NotNode(ConditionNode condition) {
        if(condition== null)
            throw new IllegalArgumentException("Condition cannot be null.");

        this.condition = condition;
    }

    @Override
    public boolean evaluate(Robot robot) {
        return !condition.evaluate(robot);
    }

    public static NotNode parse(Scanner s, BlockNode parentBlock) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode condition = ConditionNode.parse(s, parentBlock);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new NotNode(condition);
    }

    @Override
    public String toString() {
        return "NotNode{" +
                "condition=" + condition +
                '}';
    }
}
