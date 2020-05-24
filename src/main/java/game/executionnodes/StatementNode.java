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
            TURNAROUND = Pattern.compile("turnAround"),
            WAIT = Pattern.compile("wait"),
            TAKEFUEL = Pattern.compile("takeFuel"),
            SHIELDON = Pattern.compile("shieldOn"),
            SHIELDOFF = Pattern.compile("shieldOff"),
            LOOP = Pattern.compile("loop"),
            IF = Pattern.compile("if"),
            WHILE = Pattern.compile("while"),
            VARIABLE = Pattern.compile("$");

    @Override
    public abstract void execute(Robot robot);

    public static StatementNode parse(Scanner s) {
        if (Parser.checkFor(MOVE, s)) {
            return ActionNode.parse(s, ActionNode.Action.MOVE_FORWARD);
        } else if (Parser.checkFor(TURNL, s)) {
            return ActionNode.parse(s, ActionNode.Action.TURN_LEFT);
        } else if (Parser.checkFor(TURNR, s)) {
            return ActionNode.parse(s, ActionNode.Action.TURN_RIGHT);
        } else if (Parser.checkFor(WAIT, s)) {
            return ActionNode.parse(s, ActionNode.Action.WAIT);
        } else if (Parser.checkFor(TAKEFUEL, s)) {
            return ActionNode.parse(s, ActionNode.Action.TAKE_FUEL);
        } else if (Parser.checkFor(SHIELDON, s)) {
            return ActionNode.parse(s, ActionNode.Action.SHIELD_ON);
        } else if (Parser.checkFor(SHIELDOFF, s)) {
            return ActionNode.parse(s, ActionNode.Action.SHIELD_OFF);
        } else if (Parser.checkFor(TURNAROUND, s)) {
            return ActionNode.parse(s, ActionNode.Action.TURN_AROUND);
        } else if (Parser.checkFor(LOOP, s)) {
            return LoopNode.parse(s);
        } else if (Parser.checkFor(IF, s)) {
            return IfNode.parse(s);
        } else if (Parser.checkFor(WHILE, s)) {
            return WhileNode.parse(s);
        } else {
            Parser.fail("Expected next token to be statement", s);
            return null;
        }
    }
}
