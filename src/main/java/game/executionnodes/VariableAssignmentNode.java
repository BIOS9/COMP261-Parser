package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;

public class VariableAssignmentNode extends StatementNode {
    public final String name;
    public final NumberNode number;
    private BlockNode parentBlock;

    public VariableAssignmentNode(String name, NumberNode number) {
        if(name == null)
            throw new IllegalArgumentException("Name cannot be null.");
        if(number == null)
            throw new IllegalArgumentException("Number cannot be null.");

        this.name = name;
        this.number = number;
    }

    @Override
    public void execute(Robot robot) {
        if(parentBlock == null)
            throw new IllegalStateException("Parent block must be set but was null when setting variable.");
        parentBlock.setVariable(name, number.getValue(robot));
    }

    public static VariableAssignmentNode parse(Scanner s, BlockNode parentBlock) {
        String variableName = Parser.require(Parser.VARPAT, "Expected variable.", s);
        Parser.require(Parser.EQUALS, "Expected variable assignment.", s);
        NumberNode number = NumberNode.parse(s, parentBlock);
        Parser.require(Parser.SEMICOLON, "Expected semicolon.", s);
        return new VariableAssignmentNode(variableName, number);
    }

    @Override
    public String toString() {
        return "VariableAssignmentNode{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}