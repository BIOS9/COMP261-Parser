package main.java.game.executionnodes;

import main.java.game.Robot;

public class BlockNode implements RobotProgramNode {
    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");
    }
}
