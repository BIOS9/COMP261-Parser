package main.java.game.executionnodes;

import main.java.parser.Parser;

import java.util.*;
import java.util.regex.Pattern;

public class VariableDeclarationNode {
    private static final Pattern DECLARATION_PATTERN = Pattern.compile("(\\$[A-Za-z][A-Za-z0-9]*,\\s*)*(\\$[A-Za-z][A-Za-z0-9]*);");
    private final Set<String> variableNames;

    public VariableDeclarationNode(Set<String> variableNames) {
        if(variableNames == null)
            throw new IllegalArgumentException("variableNames must not be null.");

        this.variableNames = variableNames;
    }

    public boolean isVariableDeclared(String variable) {
        return variableNames.contains(variable);
    }

    public static boolean canParse(Scanner s) {
        Pattern originalDelim = s.delimiter();
        s.useDelimiter("\\s+");

        boolean canParse = s.hasNext(DECLARATION_PATTERN);
        System.out.println("Can parse: " + canParse);
        s.useDelimiter(originalDelim);
        return canParse;
    }

    public static VariableDeclarationNode parse(Scanner s) {
        Set<String> variables = new HashSet<>();
        variables.add(Parser.require(Parser.VARPAT, "Expected variable declaration.", s));
        while (s.hasNext()) {
            if(Parser.checkFor(Parser.COMMA, s)) {
                String var = Parser.require(Parser.VARPAT, "Expected variable declaration.", s);
                if(!variables.add(var))
                    Parser.fail("Duplicate variable declaration: " + var, s);
            } else if(Parser.checkFor(Parser.SEMICOLON, s)) {
                return new VariableDeclarationNode(variables);
            } else {
                Parser.fail("Unexpected token while parsing variable declaration.", s);
            }
        }
        Parser.fail("Reached end of string while parsing variable declaration.", s);
        return null;
    }

    @Override
    public String toString() {
        return "VariableDeclarationNode{" +
                "variableNames=" + variableNames +
                '}';
    }
}
