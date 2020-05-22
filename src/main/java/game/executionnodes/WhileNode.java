package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.conditions.ConditionNode;

public class WhileNode extends StatementNode {
    public final ConditionNode condition;
    public final BlockNode block;

    public WhileNode(ConditionNode condition, BlockNode block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        while (condition.isTrue()) {
            block.execute(robot);
        }
    }

    @Override
    public String toString() {
        return "WhileNode{" +
                "condition=" + condition +
                ", block=" + block +
                '}';
    }
}
