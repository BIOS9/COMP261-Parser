package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.game.executionnodes.numericoperators.NumericOperatorNode;
import main.java.parser.Parser;

import java.util.Scanner;


public abstract class NumberNode {
    public abstract int getValue(Robot robot);

    public static NumberNode parse(Scanner s, BlockNode parentBlock) {
        if(Parser.checkFor(Parser.OPENPAREN, s)) {
            NumberNode node = NumberNode.parse(s, parentBlock);
            Parser.require(Parser.CLOSEPAREN, "Expected closing parentheses for numeric expression.", s);
            return node;
        } else if(s.hasNext(Parser.NUMPAT)) {
            String value = s.next(Parser.NUMPAT);
            return new FixedNumberNode(Integer.parseInt(value));
        } else if(s.hasNext(Parser.VARPAT)) {
            return VariableNode.parse(s, parentBlock);
        } else if(SensorNode.canParse(s)) {
            return SensorNode.parse(s, parentBlock);
        } else {
            return NumericOperatorNode.parse(s, parentBlock);
        }
    }
}
