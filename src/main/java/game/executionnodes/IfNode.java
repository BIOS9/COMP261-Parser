package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.conditions.ConditionNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class IfNode extends StatementNode {
    public final ConditionNode condition;
    public final BlockNode block;

    public IfNode(ConditionNode condition, BlockNode block) {
        if(condition == null)
            throw new IllegalArgumentException("Condition cannot be null.");
        if(block == null)
            throw new IllegalArgumentException("Block cannot be null.");

        this.condition = condition;
        this.block = block;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        if(condition.evaluate(robot)) {
            block.execute(robot);
        }
    }

    public static IfNode parse(Scanner s) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode conditionNode = ConditionNode.parse(s);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new IfNode(conditionNode, BlockNode.parse(s));
    }

    @Override
    public String toString() {
        return "IfNode{" +
                "condition=" + condition +
                ", block=" + block +
                '}';
    }
}
