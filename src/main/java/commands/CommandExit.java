package commands;

public class CommandExit extends Command {
    public CommandExit(String name) {
        super(name);
    }

    @Override
    public String execute(String param) {
        System.exit(0);
        return buildOutput();
    }

    @Override
    public String buildOutput() {
        return "0";
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
