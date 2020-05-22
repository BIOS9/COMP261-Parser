package main.java.game.executionnodes.conditions;

public class GreaterThanNode extends ConditionNode {
    public final int number1, number2;

    public GreaterThanNode(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public boolean isTrue() {
        return number1 > number2;
    }

    @Override
    public String toString() {
        return "GreaterThanNode{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
