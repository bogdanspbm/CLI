package utils;

import commands.Command;
import factory.CommandFactory;

import java.util.List;
import java.util.Scanner;

import static utils.Executer.executeLine;

public class Reader {

    Scanner scan = new Scanner(System.in);
    String lastLine = "";

    public Reader() {
    }

    // Считывает команды из консоли
    // Каждую введенную команду парсит и помещает в стэк
    // Пробегается по стеку, пока не опустошит его, создавая и вызывая команды
    public void start() {
        while (scan.hasNextLine()) {
            lastLine = scan.nextLine();
            String result = executeLine(lastLine);
            System.out.println(result);
        }
    }


}
