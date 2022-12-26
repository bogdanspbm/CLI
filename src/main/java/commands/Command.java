package commands;

import java.util.*;


public abstract class Command {

    private int optionsToAdd = 0;

    String name = "";
    List<String> params = new ArrayList<>();
    List<String> options = new ArrayList<>();
    Map<String, Integer> optionMap = new HashMap<>();

    protected boolean pipeShared = true;

    public Command(String name) {
        this.name = name;
        generateOptionsMap();
    }

    public boolean getPipeShared() {
        return pipeShared;
    }

    // Абстрактный метод вызова команды
    public String execute(String param) {
        if (param != null) {
            params.add(param);
        }
        return buildOutput();
    }

    public abstract String buildOutput();

    // Абстрактный метод, возвращающий количество параметров, которое требуется для исполнения команды
    // -1 - любое количество
    // 0 - inf - точное количество
    public abstract int getParamsCount();

    protected void generateOptionsMap() {

    }

    public void readParams(Queue<String> params) {
        String param = "";

        if (getParamsCount() != -1) {
            int i = 0;
            for (; i < getParamsCount() && params.size() > 0 && !params.peek().equals("|"); i++) {
                param = params.poll();
                i = addParamOrOption(param, i);
            }
        } else {
            while (params.size() > 0 && !params.peek().equals("|")) {
                param = params.poll();
                this.params.add(param);
            }
        }
    }

    private boolean tryExtractMergedOption(String param) {
        int valueIndex = -1;
        for (int i = 0; i < param.length(); i++) {
            try {
                Integer.parseInt(param.substring(i));
                valueIndex = i;
                break;
            } catch (Exception e) {

            }
        }

        if (valueIndex != -1) {
            if (optionMap.containsKey(param.substring(0, valueIndex))) {
                options.add(param.substring(0, valueIndex));
                options.add(param.substring(valueIndex));
                return true;
            }
        }

        return false;
    }

    private int addParamOrOption(String param, int i) {
        if (param.startsWith("-") || optionsToAdd > 0) {
            i = i - 1;

            if (optionMap.containsKey(param) && optionsToAdd == 0) {
                optionsToAdd += optionMap.get(param);
            } else if (tryExtractMergedOption(param)) {
                return i;
            } else if (optionsToAdd > 0) {
                optionsToAdd--;
            }

            options.add(param);
        } else {
            params.add(param);
        }

        return i;
    }
}
