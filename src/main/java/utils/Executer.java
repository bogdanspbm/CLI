package utils;

import commands.Command;

import java.util.List;

public class Executer {
    public static String executeLine(String line) {
        List<Command> commandList = Parser.parse(line);
        String data = null;
        for (int i = 0; i < commandList.size(); i++) {
            Command command = commandList.get(i);
            data = command.execute(data);
        }
        return data;
    }
}
