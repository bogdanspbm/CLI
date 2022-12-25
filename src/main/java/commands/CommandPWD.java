package commands;

import global.SharedVariables;

public class CommandPWD extends Command {
    public CommandPWD(String name) {
        super(name);

    }

    @Override
    public String buildOutput() {
        return SharedVariables.getCurPath();
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
