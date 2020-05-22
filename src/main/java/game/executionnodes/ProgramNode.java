package main.java.game.executionnodes;

import main.java.game.Robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
