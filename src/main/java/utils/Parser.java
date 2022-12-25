package utils;

import commands.Command;
import factory.CommandFactory;

import java.util.*;

public class Parser {

    static CommandFactory factory = new CommandFactory();

    // Разбивает входную строку на слова
    // В первой версии по пробелам
    // В будущей версии также будет учтен другой синтаксис
    public static List<Command> parse(String line) {
        Queue<String> tokens = tokenize(line);
        List<Command> result = new ArrayList<>();

        while (tokens.size() > 0) {
            String token = tokens.poll();
            if (!token.equals("|")) {
                Command command = factory.createCommand(token);
                command.readParams(tokens);
                result.add(command);
            }
        }
        return result;
    }

    private static Queue<String> tokenize(String line) {
        List<String> words = new ArrayList<>();

        boolean inCommas = false;
        char usedComma = '"';

        StringBuilder word = new StringBuilder();
        char[] input = line.toCharArray();

        for (int i = 0; i < input.length; i++) {

            char ch = input[i];

            boolean wasAdded = false;

            if (!inCommas && (ch == '\'' || ch == '"')) {
                // Start Commas
                usedComma = ch;
                inCommas = true;
            } else if (ch == ' ' && !inCommas) {
                // Split on space
                if (word.toString() != "") {
                    words.add(word.toString());
                }
                wasAdded = true;
                word = new StringBuilder();
            } else if (ch == '|' && !inCommas) {
                // Split on pipe
                if (word.toString() != "") {
                    words.add(word.toString());
                }
                words.add("|");
                wasAdded = true;
                word = new StringBuilder();
            } else if (inCommas && ch == usedComma) {
                inCommas = false;
            } else {
                word.append(ch);
            }

            if (!wasAdded && i == input.length - 1) {
                words.add(word.toString());
            }
        }


        for (int i = 0; i < words.size(); i++) {
            words.set(i, VariableStorage.getStorage().replaceKeys(words.get(i)));
        }

        Queue<String> queue = new LinkedList<>();

        for (String el : words) {
            queue.add(el);
        }

        return queue;
    }
}
