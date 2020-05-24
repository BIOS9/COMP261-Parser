package main.java.game.executionnodes;

import javafx.util.Pair;
import jdk.nashorn.internal.ir.Block;
import main.java.game.Robot;
import main.java.game.executionnodes.conditions.ConditionNode;
import main.java.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IfNode extends StatementNode {
    private static final Pattern
            ELSE = Pattern.compile("else"),
            ELSEIF = Pattern.compile("elif");

    private final List<Pair<ConditionNode, BlockNode>> ifBlocks;

    public IfNode(List<Pair<ConditionNode, BlockNode>> ifBlocks) {
        if(ifBlocks == null)
            throw new IllegalArgumentException("if list cannot be null.");
        this.ifBlocks = ifBlocks;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        for(Pair<ConditionNode, BlockNode> ifPair : ifBlocks) {
            if(ifPair.getKey() == null || ifPair.getKey().evaluate(robot)) {
                ifPair.getValue().execute(robot);
                return;
            }
        }
    }

    public static IfNode parse(Scanner s) {
       ArrayList<Pair<ConditionNode, BlockNode>> ifBlocks = new ArrayList<>();

        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        ConditionNode ifCondition = ConditionNode.parse(s);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
        BlockNode ifBlock = BlockNode.parse(s);
        ifBlocks.add(new Pair<>(ifCondition, ifBlock));

        while (Parser.checkFor(ELSEIF, s)) {
            Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
            ConditionNode elseIfConditionNode = ConditionNode.parse(s);
            Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);
            BlockNode elseIfBlock = BlockNode.parse(s);
            ifBlocks.add(new Pair<>(elseIfConditionNode, elseIfBlock));
        }

        if(Parser.checkFor(ELSE, s)) {
            BlockNode elseIfBlock = BlockNode.parse(s);
            ifBlocks.add(new Pair<>(null, elseIfBlock));
        }
        return new IfNode(ifBlocks);
    }

    @Override
    public String toString() {
        return "IfNode{" +
                "ifBlocks=" + ifBlocks +
                '}';
    }
}
