package main.java.game.executionnodes;

import main.java.game.Robot;

public class LoopNode implements RobotProgramNode {
    public final BlockNode block;

    public LoopNode(BlockNode block) {
        if(block == null)
            throw new IllegalArgumentException("Block cannot be null.");
        this.block = block;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");
    }

    @Override
    public String toString() {
        return "Loop{" +
                "block=" + block +
                '}';
    }
}
