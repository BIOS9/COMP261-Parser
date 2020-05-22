package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.conditions.ConditionNode;

public class IfNode extends StatementNode {
    public final ConditionNode condition;
    public final BlockNode block;

    public IfNode(ConditionNode condition, BlockNode block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public void execute(Robot robot) {
        if (robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        if(condition.isTrue()) {
            block.execute(robot);
        }
    }

    @Override
    public String toString() {
        return "IfNode{" +
                "condition=" + condition +
                ", block=" + block +
                '}';
    }
}
