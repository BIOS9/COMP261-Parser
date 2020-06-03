package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;
import main.java.parser.ParserFailureException;

import java.util.*;
import java.util.stream.Stream;

public class ProgramNode implements Iterable<StatementNode>, RobotProgramNode {
    private final BlockNode block;

    public ProgramNode(BlockNode block) {
        this.block = block;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        if(block == null)
            return;

        block.execute(robot);
    }

    public static ProgramNode parse(Scanner s) {
        try {
            List<StatementNode> statementNodes = new ArrayList<>();

            VariableDeclarationNode varDecl = null;

            if(VariableDeclarationNode.canParse(s))
                varDecl = VariableDeclarationNode.parse(s);

            BlockNode block = new BlockNode(null, varDecl);

            while (s.hasNext()) {
                StatementNode statement = StatementNode.parse(s, block);
                if (statement == null)
                    Parser.fail("Statement cannot be null", s);
                statementNodes.add(statement);
            }
            block.setStatements(statementNodes);

            return new ProgramNode(block);
        } catch (IllegalArgumentException ex) {
            throw new ParserFailureException(ex.getMessage());
        }
    }

    public List<StatementNode> getStatements() {
        return block.getStatements();
    }

    public Stream<StatementNode> stream() {
        return block.stream();
    }

    @Override
    public Iterator<StatementNode> iterator() {
        return block.iterator();
    }

    @Override
    public String toString() {
        return "ProgramNode{" +
                "block=" + block +
                '}';
    }
}
