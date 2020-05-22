package main.java.game.executionnodes;

import main.java.game.Robot;

public abstract class StatementNode implements RobotProgramNode {
    @Override
    public abstract void execute(Robot robot);
}
