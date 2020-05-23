package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;
import main.java.game.executionnodes.SensorNode;


public abstract class NumericComparisonNode extends ConditionNode{
    public final NumberNode number1, number2;

    public NumericComparisonNode(NumberNode number1, NumberNode number2) {
        if(number1 == null)
            throw new IllegalArgumentException("Number1 cannot be null.");
        if(number2 == null)
            throw new IllegalArgumentException("Number2 cannot be null.");

        this.number1 = number1;
        this.number2 = number2;
    }

    public abstract boolean evaluate(Robot robot);
}
