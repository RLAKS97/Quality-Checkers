package com.qualitytester.maven.quality_testing_example;

public class CommandInterpreter {
    private Robot robot;

    public void processCommand(String command) {
        if (command.length() < 1) {
            return;
        }
        switch (command.charAt(0)) {
            case 'I':
                int size = Integer.parseInt(command.split(" ")[1]);
                robot = new Robot(size);
                break;
            case 'C':
                System.out.println(robot.getPosition());
                break;
            case 'D':
                robot.penDown();
                break;
            case 'U':
                robot.penUp();
                break;
            case 'M':
                int steps = Integer.parseInt(command.split(" ")[1]);
                robot.move(steps);
                break;
            case 'R':
                robot.turnRight();
                break;
            case 'P':
                robot.printFloor();
                break;
            case 'Q':
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
}

