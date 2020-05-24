package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;

public class VariableNode extends NumberNode {
    public final String variableName;
    private BlockNode parentBlock;

    public VariableNode(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public int getValue(Robot robot) {
        if(parentBlock == null)
            throw new IllegalStateException("Parent block must be set but was null when getting variable.");

        return parentBlock.getVariable(variableName);
    }

    public static VariableNode parse(Scanner s) {
        String name = Parser.require(Parser.VARPAT, "Expected variable.", s);
        return new VariableNode(name);
    }

    @Override
    public String toString() {
        return "VariableIntegerNode{" +
                "variableName='" + variableName + '\'' +
                '}';
    }
}
