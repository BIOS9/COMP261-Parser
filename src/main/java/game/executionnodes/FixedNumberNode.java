package main.java.game.executionnodes;

import main.java.game.Robot;

public class FixedNumberNode extends NumberNode {
    public final int value;

    public FixedNumberNode(int value) {
        this.value = value;
    }

    @Override
    public int getValue(Robot robot) {
        return value;
    }

    @Override
    public String toString() {
        return "FixedNumberNode{" +
                "value=" + value +
                '}';
    }
}
