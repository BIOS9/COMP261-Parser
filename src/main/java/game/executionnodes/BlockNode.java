package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.*;
import java.util.stream.Stream;

public class BlockNode extends StatementNode implements Iterable<StatementNode> {
    private final BlockNode parentBlock;
    private List<StatementNode> statements;
    public static final Map<String, Integer> variables = new HashMap<>();

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
        List<StatementNode> statements = new ArrayList<>();

        Parser.require(Parser.OPENBRACE, "Expected open curly brace for block section.", s);

        BlockNode block = new BlockNode(parentBlock);

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

    public static void setVariable(String name, int value) {
        variables.put(name, value);
    }

    public static int getVariable(String name) {
        if(variables.containsKey(name)) {
            return variables.get(name);
        } else {
            return 0;
        }
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
        return "BlockNode{" +
                "statements=" + statements +
                '}';
    }
}
