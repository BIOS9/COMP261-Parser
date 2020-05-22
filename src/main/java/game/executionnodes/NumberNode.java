package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;

public class NumberNode {
    private final SensorNode sensor;
    private final Integer fixedInteger;

    public NumberNode(SensorNode sensor) {
        if (sensor == null)
            throw new IllegalArgumentException("Sensor cannot be null.");

        this.sensor = sensor;
        this.fixedInteger = null;
    }

    public NumberNode(int fixedInteger) {
        this.sensor = null;
        this.fixedInteger = fixedInteger;
    }

    public int getValue(Robot robot) {
        if (sensor == null)
            return fixedInteger;
        return sensor.getValue(robot);
    }

    public static NumberNode parse(Scanner s) {
        if (s.hasNext(Parser.NUMPAT))
            return new NumberNode(Integer.parseInt(s.next()));
        return new NumberNode(SensorNode.parse(s));
    }

    @Override
    public String toString() {
        return "NumberNode{" +
                "sensor=" + sensor +
                ", fixedInteger=" + fixedInteger +
                '}';
    }
}
