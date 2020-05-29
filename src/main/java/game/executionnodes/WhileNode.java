package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.conditions.ConditionNode;
import main.java.parser.Parser;

import java.util.Scanner;

public class WhileNode extends StatementNode {
    public final ConditionNode condition;
    public final BlockNode block;

    public WhileNode(ConditionNode condition, BlockNode block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        while (condition.evaluate(robot)) {
            block.execute(robot);
        }
    }

    public static WhileNode parse(Scanner s, BlockNode parentBlock) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode conditionNode = ConditionNode.parse(s, parentBlock);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        return new WhileNode(conditionNode, BlockNode.parse(s, parentBlock));
    }

    @Override
    public String toString() {
        return "WhileNode{" +
                "condition=" + condition +
                ", block=" + block +
                '}';
    }
}
