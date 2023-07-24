package com.quality.tester.thread;

import java.util.Scanner;

public class Robot {
    static String[][] robotMap;
    static String penDirection = null, robotFace = null;
    private static Scanner sc;
    static int nMatrix = 0;
    static int mark1;
	static int mark2;
	static int mark3;
	static int mark4;
    static int[] robotPosition = new int[2];
    

    public static String changeRobotFaceLeft(String robotFace) {
        if (robotFace.equals("north"))
            robotFace = "west";
        else if (robotFace.equals("west"))
            robotFace = "south";
        else if (robotFace.equals("south"))
            robotFace = "east";
        else
            robotFace = "north";
        return robotFace;
    }

    public static String changeRobotFaceRight(String robotFace) {
        if (robotFace.equals("north"))
            robotFace = "east";
        else if (robotFace.equals("south"))
            robotFace = "west";
        else if (robotFace.equals("west"))
            robotFace = "north";
        else
            robotFace = "south";
        return robotFace;
    }

    public static String[][] trackRobot(int oldX, int oldY, int newX, int newY, String penDirection, String[][] robotMap) {
        int i, j;
        if (penDirection.equals("down")) {
            // For negative turns
            if (oldX > newX || oldY > newY) {
                for (i = newX; i <= oldX; i++) {
                    for (j = newY; j <= oldY; j++) {
                        if (i >= 0 && i < robotMap.length && j >= 0 && j < robotMap[0].length) {
                            robotMap[i][j] = "*";
                        }
                    }
                }
            }

            for (i = Math.max(0, oldX); i <= Math.min(newX, robotMap.length - 1); i++) {
                for (j = Math.max(0, oldY); j <= Math.min(newY, robotMap[0].length - 1); j++) {
                    robotMap[i][j] = "*";
                }
            }

        }
        return robotMap;
    }

    public static void initialiseArray(int nMatrix) {
        penDirection = "up";
        robotFace = "north";
        robotMap = new String[nMatrix][nMatrix];
    }
    
    public static int[] move(int[] robotPosition, String []commandArray, int nMatrix, String robotFace, String penDirection, String[][] robotMap) {
        int mark1 = robotPosition[1];
        int mark2 = robotPosition[0];
        int walk = Integer.parseInt(commandArray[1]);
        
        
        if (robotFace.equals("north")) {
            if (robotPosition[0] + walk <= nMatrix) {
                robotPosition[0] = robotPosition[0] + walk;
            } else {
                System.out.println("Space not available for robot to move upwards");
            }
        } else if (robotFace.equals("south")) {
            if (robotPosition[0] - walk >= 0) {
                robotPosition[0] = robotPosition[0] - walk;
            } else {
                System.out.println("Space not available for robot to move downwards");
            }
        } else if (robotFace.equals("east")) {
            if (robotPosition[1] + walk <= nMatrix) {
                robotPosition[1] = robotPosition[1] + walk;
            } else {
                System.out.println("Space not available for robot to move sidewards");
            }
        } else if (robotFace.equals("west")) {
            if (robotPosition[1] - walk >= 0) {
                robotPosition[1] = robotPosition[1] - walk;
            } else {
                System.out.println("Space not available for robot to move sidewards");
            }
        }

        int mark3 = robotPosition[1];
        int mark4 = robotPosition[0];

        // Assuming trackRobot method modifies robotMap based on the robot's movement
        robotMap = trackRobot(mark1, mark2, mark3, mark4, penDirection, robotMap);

        return robotPosition;
    }

    
    public static void printArray(int nMatrix) {
    	for (int i = robotMap.length - 1; i >= 0; i--) {
            for (int j = 0; j < robotMap[i].length; j++) {
                if (robotMap[j][i] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(robotMap[j][i] + " ");
                }
            }
            System.out.println("\n");
    	}
    }
    
    public static void inputCommand(){
    	int[] robotPosition = new int[2];
        sc = new Scanner(System.in);
        System.out.print("Welcome to RoboticPen!!!\n"
                + "Use below commands!!!\n"
                + "[U|u] Pen up \n"
                + "[D|d] Pen down \n"
                + "[R|r] Turn right \n"
                + "[L|l] Turn left \n"
                + "[M s|m s] Move forward s spaces (s is a non-negative integer)\n"
                + "[P|p] Print the floor mapped \n"
                + "[C|c] Print current position of the pen and whether it is up or down and its \n"
                + "facing direction \n"
                + "[Q|q] Stop the program \n"
                + "[I n|i n] Initialize the system: The values of the array floor are zeros and the robot \n"
                + "is back to [0, 0], pen up and facing north. n size of the array, an integer \n"
                + "greater than zero \n"
                + "[H|h] Replay all the commands entered by the user as a history \n");
        String[] commandArray;
        //int walk;
        boolean flag = true;
        while (flag) {
        	System.out.print("Enter command: ");
            String command = sc.nextLine();
            commandArray = command.split("\\s");
            try {
            	switch(commandArray[0]){
            	case "i":
            	case "I":
            		InitializeMatrix(command);
            		robotPosition[1]=0;
            		robotPosition[0]=0;
                    break;
            	case "m":
            	case "M":
            		move(robotPosition, commandArray, nMatrix, robotFace, penDirection, robotMap);
                    break;
            	case "r":
            	case "R":
            		robotFace = changeRobotFaceRight(robotFace);
            		break;
            	case "l":
            	case "L":
            		robotFace = changeRobotFaceLeft(robotFace);
            		break;
            	case "u":
            	case "U":
            		penDirection = "up";
            		break;
            	case "d":
            	case "D":
            		penDirection = "down";
            	case "c":
            	case "C":
            	{
            		System.out.print("Position: " + robotPosition[1]+" "+robotPosition[0]);
                	System.out.print(" - Pen: " + penDirection);
                	System.out.println(" - Facing: " + robotFace);
                	}
                	break;
            	case "q":
            	case "Q":
            		System.out.println("End of Program");
                	flag = false;
                	break;
            	case "p":
            	case "P":
            		printArray(nMatrix);
            		break;
            	default:
            		System.out.println("Enter a valid command:");
            	}
            }
                catch (NumberFormatException ex) {
                System.out.println("Invalid number format. Please try again with a valid command.");
            }
        }
    }

    public static void InitializeMatrix(String command) {
		nMatrix = Integer.parseInt(command.substring(2).trim());
		if (nMatrix > 0) {
		    initialiseArray(nMatrix);
		} else {
		    System.out.print("The given command is not valid. Please enter a positive value!!!");
		}
	}

    public static void main(String[] args) {
    	inputCommand();
    }

	public static void printPositionPenFacing(int[] robotPosition2, String penDirection2, String robotFace2) {
		// TODO Auto-generated method stub
		
	}


}

