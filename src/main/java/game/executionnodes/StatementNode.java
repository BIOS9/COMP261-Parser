package main.java.game.executionnodes;

import jdk.nashorn.internal.ir.Block;
import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class StatementNode implements RobotProgramNode {
    private static final Pattern
            MOVE = Pattern.compile("move"),
            TURNL = Pattern.compile("turnL"),
            TURNR = Pattern.compile("turnR"),
            WAIT = Pattern.compile("wait"),
            LOOP = Pattern.compile("loop"),
            TAKEFUEL = Pattern.compile("takeFuel");

    @Override
    public abstract void execute(Robot robot);

    public static StatementNode parse(Scanner s) {
        if (Parser.checkFor(MOVE, s)) {
            Parser.require(Parser.SEMICOLON, "Expected semicolon.", s);
            return new ActionNode(ActionNode.Action.MOVE_FORWARD);
        } else if (Parser.checkFor(TURNL, s)) {
            Parser.require(Parser.SEMICOLON, "Expected semicolon.", s);
            return new ActionNode(ActionNode.Action.TURN_LEFT);
        } else if (Parser.checkFor(TURNR, s)) {
            Parser.require(Parser.SEMICOLON, "Expected semicolon.", s);
            return new ActionNode(ActionNode.Action.TURN_RIGHT);
        } else if (Parser.checkFor(WAIT, s)) {
            Parser.require(Parser.SEMICOLON, "Expected semicolon.", s);
            return new ActionNode(ActionNode.Action.WAIT);
        } else if (Parser.checkFor(TAKEFUEL, s)) {
            Parser.require(Parser.SEMICOLON, "Expected semicolon.", s);
            return new ActionNode(ActionNode.Action.TAKE_FUEL);
        } else if (Parser.checkFor(LOOP, s)) {
            return new LoopNode(BlockNode.parse(s));
        } else {
            Parser.fail("Expected next token to be statement", s);
            return null;
        }
    }
}
