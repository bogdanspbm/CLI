package factory;

import commands.*;

public class CommandFactory {

    // Фактори возрващающий экземпляр команды, построенной по строковому ключу
    public Command createCommand(String name) {
        switch (name) {
            case "cat": {
                return new CommandCat(name);
            }
            case "echo": {
                return new CommandEcho(name);
            }
            case "wc": {
                return new CommandWC(name);
            }
            case "pwd": {
                return new CommandPWD(name);
            }
            case "ls": {
                return new CommandLS(name);
            }
            case "grep": {
                return new CommandGrep(name);
            }
            case "exit": {
                return new CommandExit(name);
            }
            default: {
                if (isAssigment(name)) {
                    return new CommandAssign(name);
                }
            }
        }
        return new CommandOut(name);
    }

    private boolean isAssigment(String str) {
        // Нужно переписать логику более похоже на bash
        if (str.contains("=") && !str.contains(" = ")) {
            return true;
        }

        return false;
    }
}
