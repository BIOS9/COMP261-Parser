package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;
import main.java.parser.ParserFailureException;

import java.util.*;
import java.util.stream.Stream;

public class ProgramNode implements Iterable<StatementNode>, RobotProgramNode {
    private final List<StatementNode> statements;

    public ProgramNode(List<StatementNode> statements) {
        if(statements == null)
            this.statements = Collections.emptyList();
        else
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

    public static ProgramNode parse(Scanner s) {
        try {
            List<StatementNode> statementNodes = new ArrayList<>();

            while (s.hasNext()) {
                StatementNode statement = StatementNode.parse(s);
                if (statement == null)
                    Parser.fail("Statement cannot be null", s);
                statementNodes.add(statement);
            }

            return new ProgramNode(statementNodes);
        } catch (IllegalArgumentException ex) {
            throw new ParserFailureException(ex.getMessage());
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
        return "Program{" +
                "statements=" + statements +
                '}';
    }
}
