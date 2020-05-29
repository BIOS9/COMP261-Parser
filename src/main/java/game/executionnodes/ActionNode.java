package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * An action for the robot to execute..
 *
 * @author Matthew Corfiatis
 */
public class ActionNode extends StatementNode {
    private final NumberNode argument;

    public enum Action {
        TURN_LEFT,
        TURN_RIGHT,
        TURN_AROUND,
        MOVE_FORWARD,
        TAKE_FUEL,
        WAIT,
        SHIELD_ON,
        SHIELD_OFF
    }

    private static final Set<Action> actionsWithArgument = new HashSet<Action>() {{
        add(Action.MOVE_FORWARD);
        add(Action.WAIT);
    }};

    public final Action action;

    public ActionNode(Action action, NumberNode argument) {
        if(action == null)
            throw new IllegalArgumentException("Action cannot be null.");
        this.action = action;
        this.argument = argument;
    }

    public static ActionNode parse(Scanner s, Action action, BlockNode parentBlock) {
        if(Parser.checkFor(Parser.SEMICOLON, s)) {
            return new ActionNode(action, null);
        } else if(!actionsWithArgument.contains(action)) {
            Parser.fail("Expected semicolon for action.", s);
            return null;
        } else if(Parser.checkFor(Parser.OPENPAREN, s)) {
            NumberNode number = NumberNode.parse(s, parentBlock);
            Parser.require(Parser.CLOSEPAREN, "Expected closed parentheses.", s);
            Parser.require(Parser.SEMICOLON, "Expected semicolon for action.", s);
            return new ActionNode(action, number);
        } else {
            Parser.fail("Expected semicolon or argument for action.", s);
            return null;
        }
    }

    @Override
    public void execute(Robot robot) {
        if(robot == null)
            throw new IllegalArgumentException("Robot cannot be null.");

        int i = 1;
        if(argument != null)
            i = argument.getValue(robot);

        while (i > 0) {
            switch (action) {
                case TURN_LEFT:
                    robot.turnLeft();
                    break;
                case TURN_RIGHT:
                    robot.turnRight();
                    break;
                case TURN_AROUND:
                    robot.turnAround();
                    break;
                case MOVE_FORWARD:
                    robot.move();
                    break;
                case TAKE_FUEL:
                    robot.takeFuel();
                    break;
                case WAIT:
                    robot.idleWait();
                    break;
                case SHIELD_ON:
                    robot.setShield(true);
                    break;
                case SHIELD_OFF:
                    robot.setShield(false);
                    break;
            }
            --i;
        }
    }

    @Override
    public String toString() {
        return "ActionNode{" +
                "argument=" + argument +
                ", action=" + action +
                '}';
    }
}
