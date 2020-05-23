package main.java.game.executionnodes.numericoperators;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;

public class MultiplicationNode extends NumericOperatorNode {
    public MultiplicationNode(NumberNode number1, NumberNode number2) {
        super(number1, number2);
    }

    @Override
    public int getValue(Robot robot) {
        return number1.getValue(robot) * number2.getValue(robot);
    }

    @Override
    public String toString() {
        return "MultiplicationNode{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
