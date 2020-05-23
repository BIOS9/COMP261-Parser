package main.java.game.executionnodes;

import main.java.game.Robot;
import main.java.parser.Parser;

import java.util.Scanner;

public class NumberNode {
    private final Integer fixedInteger;

    public NumberNode(int fixedInteger) {
        this.fixedInteger = fixedInteger;
    }

    public int getValue(Robot robot) {
        return fixedInteger;
    }

    public static NumberNode parse(Scanner s) {
        if (s.hasNext(Parser.NUMPAT))
            return new NumberNode(Integer.parseInt(s.next()));
        else {
            Parser.fail("Invalid fixed integer.", s);
            return null;
        }
    }

    @Override
    public String toString() {
        return "NumberNode{" +
                "fixedInteger=" + fixedInteger +
                '}';
    }
}
