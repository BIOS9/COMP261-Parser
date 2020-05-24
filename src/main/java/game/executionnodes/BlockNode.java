package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.*;
import java.util.stream.Stream;

public class BlockNode implements Iterable<StatementNode>, RobotProgramNode {
    private final List<StatementNode> statements;
    private final Map<String, Integer> variables;
    private BlockNode parentBlock;

    public BlockNode(List<StatementNode> statements, Map<String, Integer> variables) {
        if(statements == null)
            throw new IllegalArgumentException("Statements cannot be null.");
        if(statements.size() < 1)
            throw new IllegalArgumentException("Statements must have at least one statement.");
        if(variables == null)
            throw new IllegalArgumentException("Variables cannot be null.");

        this.statements = new ArrayList<>(statements);
        this.variables = variables;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        for(StatementNode statement : this) {
            statement.execute(robot);
        }
    }

    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    public int getVariable(String name) {
        if(variables.containsKey(name)) {
            return variables.get(name);
        } else {
            if(parentBlock != null)
                return parentBlock.getVariable(name);
            else
                throw new IllegalArgumentException("Variable must be declared before it can be used.");
        }
    }

    public static BlockNode parse(Scanner s, BlockNode parentBlock) {
        List<StatementNode> statements = new ArrayList<>();
        Map<String, Integer> variables = new HashMap<>();

        Parser.require(Parser.OPENBRACE, "Expected open curly brace for block section.", s);

        while (s.hasNext()) {
            if (Parser.checkFor(Parser.CLOSEBRACE, s))
                return new BlockNode(statements, variables);

            StatementNode statement = StatementNode.parse(s);
            if(statement != null)
                statements.add(statement);
        }

        Parser.fail("End of file reached with open block. Expected \"}\"", s);
        return null;
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
