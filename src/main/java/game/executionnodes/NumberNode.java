package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.numericoperators.NumericOperatorNode;
import main.java.parser.Parser;

import java.util.Scanner;


public abstract class NumberNode {
    public abstract int getValue(Robot robot);

    public static NumberNode parse(Scanner s, BlockNode parentBlock) {
        if(s.hasNext(Parser.NUMPAT)) {
            String value = s.next(Parser.NUMPAT);
            return new FixedNumberNode(Integer.parseInt(value));
        } else if(s.hasNext(Parser.VARPAT)) {
            return VariableNode.parse(s);
        } else if(SensorNode.canParse(s)) {
            return SensorNode.parse(s, parentBlock);
        } else {
            return NumericOperatorNode.parse(s, parentBlock);
        }
    }
}
