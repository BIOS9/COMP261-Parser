package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.conditions.ConditionNode;
import main.java.parser.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public class IfNode extends StatementNode {
    private static final Pattern ELSE = Pattern.compile("else");

    public final ConditionNode condition;
    public final BlockNode block;
    public final BlockNode elseBlock;

    public IfNode(ConditionNode condition, BlockNode block, BlockNode elseBlock) {
        if(condition == null)
            throw new IllegalArgumentException("Condition cannot be null.");
        if(block == null)
            throw new IllegalArgumentException("Block cannot be null.");

        this.condition = condition;
        this.block = block;
        this.elseBlock = elseBlock;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        if(condition.evaluate(robot)) {
            block.execute(robot);
        } else {
            elseBlock.execute(robot);
        }
    }

    public static IfNode parse(Scanner s) {
        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode conditionNode = ConditionNode.parse(s);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);

        BlockNode block = BlockNode.parse(s);
        BlockNode elseBlock = null;
        if(Parser.checkFor(ELSE, s)) {
            elseBlock = BlockNode.parse(s);
        }
        return new IfNode(conditionNode, block, elseBlock);
    }

    @Override
    public String toString() {
        return "IfNode{" +
                "condition=" + condition +
                ", block=" + block +
                ", elseBlock=" + elseBlock +
                '}';
    }
}
