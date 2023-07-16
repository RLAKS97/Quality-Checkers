package com.qualitytester.maven.quality_testing_example;


import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandInterpreter interpreter = new CommandInterpreter();

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            interpreter.processCommand(command);
            if (command.equalsIgnoreCase("Q")) {
                break;
            }
        }

        scanner.close();
    }
}
