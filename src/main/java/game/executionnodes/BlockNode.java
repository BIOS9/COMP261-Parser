package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.*;
import java.util.stream.Stream;

public class BlockNode implements Iterable<StatementNode>, RobotProgramNode {
    private BlockNode parentBlock;
    private List<StatementNode> statements;

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
            if (Parser.checkFor(Parser.CLOSEBRACE, s))
                return block;

            statements.add(StatementNode.parse(s, block));
        }

        block.setStatements(statements);
        Parser.fail("End of file reached with open block. Expected \"}\"", s);
        return null;
    }

    public 

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
