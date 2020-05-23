package main.java.game.executionnodes.conditions;

import main.java.game.Robot;
import main.java.game.executionnodes.NumberNode;
import main.java.game.executionnodes.SensorNode;


public abstract class NumericComparisonNode extends ConditionNode{
    public final SensorNode sensor;
    public final NumberNode number;

    public NumericComparisonNode(SensorNode sensor, NumberNode number) {
        if(sensor == null)
            throw new IllegalArgumentException("Sensor cannot be null.");
        if(number == null)
            throw new IllegalArgumentException("Number cannot be null.");

        this.sensor = sensor;
        this.number = number;
    }

    public abstract boolean evaluate(Robot robot);
}
