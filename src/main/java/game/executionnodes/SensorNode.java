package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SensorNode extends NumberNode {
    public enum Source {
        FUEL_LEFT,
        OPPONENT_LR,
        OPPONENT_FB,
        NUM_BARRELS,
        BARREL_LR,
        BARREL_FB,
        WALL_DIST
    }

    private static final Map<Pattern, Source> SOURCE_PATTERNS = new HashMap<Pattern, Source>() {{
        put(Pattern.compile("fuelLeft"), Source.FUEL_LEFT);
        put(Pattern.compile("oppLR"), Source.OPPONENT_LR);
        put(Pattern.compile("oppFB"), Source.OPPONENT_FB);
        put(Pattern.compile("numBarrels"), Source.NUM_BARRELS);
        put(Pattern.compile("barrelLR"), Source.BARREL_LR);
        put(Pattern.compile("barrelFB"), Source.BARREL_FB);
        put(Pattern.compile("wallDist"), Source.WALL_DIST);
    }};

    public final Source source;

    public SensorNode(Source source) {
        this.source = source;
    }

    public static boolean canParse(Scanner s) {
        for (Pattern p : SOURCE_PATTERNS.keySet()) {
            if(s.hasNext(p))
                return true;
        }

        return false;
    }

    public int getValue(Robot robot) {
        switch (source) {
            case BARREL_FB:
                return robot.getClosestBarrelFB();
            case BARREL_LR:
                return robot.getClosestBarrelLR();
            case FUEL_LEFT:
                return robot.getFuel();
            case WALL_DIST:
                return robot.getDistanceToWall();
            case NUM_BARRELS:
                return robot.numBarrels();
            case OPPONENT_FB:
                return robot.getOpponentFB();
            case OPPONENT_LR:
                return robot.getOpponentLR();
        }

        throw new IllegalArgumentException("Unhandled sensor source.");
    }

    public static SensorNode parse(Scanner s) {
        for (Map.Entry<Pattern, Source> source : SOURCE_PATTERNS.entrySet()) {
            if(Parser.checkFor(source.getKey(), s))
                return new SensorNode(source.getValue());
        }

        Parser.fail("Unknown sensor source.", s);
        return null;
    }

    @Override
    public String toString() {
        return "SensorNode{" +
                "source=" + source +
                '}';
    }
}
