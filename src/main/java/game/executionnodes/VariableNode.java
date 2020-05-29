package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;
import main.java.parser.ParserFailureException;

import java.util.Scanner;

public class VariableNode extends NumberNode {
    public final String variableName;
    private BlockNode parentBlock;

    public VariableNode(String variableName, BlockNode parentBlock) {
        if(variableName == null)
            throw new IllegalArgumentException("Name cannot be null.");
        if(parentBlock == null)
            throw new IllegalArgumentException("Parent block cannot be null.");

        this.parentBlock = parentBlock;
        this.variableName = variableName;
    }

    @Override
    public int getValue(Robot robot) {
        if(parentBlock == null)
            throw new IllegalStateException("Parent block must be set but was null when getting variable.");

        return parentBlock.getVariable(variableName);
    }

    public static VariableNode parse(Scanner s, BlockNode parentBlock) {
        String name = Parser.require(Parser.VARPAT, "Expected variable.", s);
        if(!parentBlock.isParserVariableDeclared(name))
            throw new ParserFailureException("Variable " + name + " Was not declared in this scope. Variables must be declared before they can be used.");
        return new VariableNode(name, parentBlock);
    }

    @Override
    public String toString() {
        return "VariableIntegerNode{" +
                "variableName='" + variableName + '\'' +
                '}';
    }
}