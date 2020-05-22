package main.java.game.executionnodes;

import main.java.game.Robot;

/**
 * An action for the robot to execute..
 *
 * @author Matthew Corfiatis
 */
public class ActionNode implements RobotProgramNode {
    public enum Action {
        TURN_LEFT,
        TURN_RIGHT,
        TURN_AROUND,
        MOVE_FORWARD,
        TAKE_FUEL,
        WAIT
    }

    public final Action action;

    public ActionNode(Action action) {
        this.action = action;
    }

    @Override
    public void execute(Robot robot) {
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
        }
    }
}
