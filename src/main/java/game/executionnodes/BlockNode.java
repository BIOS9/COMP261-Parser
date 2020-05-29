package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.*;
import java.util.stream.Stream;

public class BlockNode implements Iterable<StatementNode>, RobotProgramNode {
    private final BlockNode parentBlock;
    private List<StatementNode> statements;
    private final Map<String, Integer> variables = new HashMap<>();
    private final Set<String> parserVariableDeclarations = new HashSet<>();

    public BlockNode(BlockNode parentBlock) {
        this.parentBlock = parentBlock;
    }

    public void setStatements(List<StatementNode> statements) {
        if(statements == null)
            throw new IllegalArgumentException("Statements cannot be null.");
        if(statements.size() < 1)
            throw new IllegalArgumentException("Statements must have at least one statement.");

        this.statements = new ArrayList<>(statements);
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        for(StatementNode statement : this) {
            statement.execute(robot);
        }
    }

    public static BlockNode parse(Scanner s, BlockNode parentBlock) {
        BlockNode block = new BlockNode(parentBlock);
        List<StatementNode> statements = new ArrayList<>();

        Parser.require(Parser.OPENBRACE, "Expected open curly brace for block section.", s);

        while (s.hasNext()) {
            if (Parser.checkFor(Parser.CLOSEBRACE, s)) {
                block.setStatements(statements);
                return block;
            }

            statements.add(StatementNode.parse(s, block));
        }

        Parser.fail("End of file reached with open block. Expected \"}\"", s);
        return null;
    }

    /**
     * Overwrites variable if it has been declared in parent scope
     * or declares it in the current scope if it has not been declared yet.
     */
    public void setVariable(String name, int value) {
        if(!replaceParentVariable(name, value)) {
            variables.put(name, value);
        }
    }

    /**
     * If parent blocks contain variable, replace it up the stack
     * If variable has not been declared above yet, return false.
     */
    private boolean replaceParentVariable(String name, int value) {
        if(variables.containsKey(name)) {
            variables.replace(name, value);
            return true;
        }

        if(parentBlock == null)
            return false;

        return parentBlock.replaceParentVariable(name, value);
    }

    public int getVariable(String name) {
        if(variables.containsKey(name)) {
            return variables.get(name);
        } else {
            if(parentBlock != null)
                return parentBlock.getVariable(name);
            else
                throw new IllegalStateException("Variable must be declared before it can be used.");
        }
    }

    /**
     * Checks if a variable has declared in this or a parent block during parsing.
     */
    public boolean isParserVariableDeclared(String name) {
        if(parserVariableDeclarations.contains(name))
            return true;
        if(parentBlock == null)
            return false;
        return parentBlock.isParserVariableDeclared(name);
    }

    /**
     * Declares a variable in this block to use during parsing.
     */
    public void declareParserVariable(String name) {
        parserVariableDeclarations.add(name);
    }

    public List<StatementNode> getStatements() {
        return Collections.unmodifiableList(statements);
    }

    public Stream<StatementNode> stream() {
        return statements.stream();
    }

    @Override
    public Iterator<StatementNode> iterator() {
        return statements.iterator();
    }

    @Override
    public String toString() {
        return "Block{" +
                "statements=" + statements +
                '}';
    }
}
