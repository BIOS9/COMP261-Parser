package main.java.game.executionnodes;

import main.java.game.Robot;

import java.util.Scanner;

public class LoopNode extends StatementNode {
    public final BlockNode block;
    private final BlockNode parentBlock;

    public LoopNode(BlockNode block, BlockNode parentBlock) {
        if(block == null)
            throw new IllegalArgumentException("Block cannot be null.");
        this.block = block;

        if(parentBlock == null)
            throw new IllegalArgumentException("Parent block cannot be null.");
        this.parentBlock = parentBlock;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        while (!robot.isDead()) {
            block.execute(robot);
        }
    }

    public static LoopNode parse(Scanner s, BlockNode parentBlock) {
        return new LoopNode(BlockNode.parse(s, parentBlock), parentBlock);
    }

    @Override
    public String toString() {
        return "Loop{" +
                "block=" + block +
                '}';
    }
}
