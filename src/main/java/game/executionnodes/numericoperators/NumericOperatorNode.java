package main.java.game.executionnodes.numericoperators;

import main.java.game.Robot;
import main.java.game.executionnodes.BlockNode;
import main.java.game.executionnodes.NumberNode;
import main.java.parser.Parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class NumericOperatorNode extends NumberNode {
    private static final Pattern
            ADD = Pattern.compile("add"),
            SUBTRACT = Pattern.compile("sub"),
            MULTIPLY = Pattern.compile("mul"),
            DIVIDE = Pattern.compile("div");

    public final NumberNode number1, number2;

    public NumericOperatorNode(NumberNode number1, NumberNode number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public abstract int getValue(Robot robot);

    public static NumericOperatorNode parse(Scanner s, BlockNode parentBlock) {
        Class<? extends NumericOperatorNode> numericOperatorClass;

        if(Parser.checkFor(ADD, s))
            numericOperatorClass = AdditionNode.class;
        else if(Parser.checkFor(SUBTRACT, s))
            numericOperatorClass = SubtractionNode.class;
        else if(Parser.checkFor(MULTIPLY, s))
            numericOperatorClass = MultiplicationNode.class;
        else if(Parser.checkFor(DIVIDE, s))
            numericOperatorClass = DivisionNode.class;
        else {
            Parser.fail("Expected numeric operator.", s);
            return null;
        }

        Parser.require(Parser.OPENPAREN, "Expected open parentheses.", s);
        NumberNode number1 = NumberNode.parse(s, parentBlock);
        Parser.require(Parser.COMMA, "Expected comma.", s);
        NumberNode number2 = NumberNode.parse(s, parentBlock);
        Parser.require(Parser.CLOSEPAREN, "Expected close parentheses.", s);

        try {
            Constructor<? extends NumericOperatorNode> constructor = numericOperatorClass.getConstructor(NumberNode.class, NumberNode.class);
            return constructor.newInstance(number1, number2);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
            Parser.fail("Failed to instantiate numeric operator node. " + ex.getMessage(), s);
            return null;
        }
    }
}
