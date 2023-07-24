package com.quality.tester.thread;


import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


public class RobotTest{

    
    private String[][] robotMap;
    private int nMatrix;
    private String[] commandArray;
    private int[] robotPosition;



    @Test
    public void testChangeRobotFaceLeft() {
    	
        String currentFace = "north";
        String expectedFace = "west";
        assertEquals(expectedFace, com.quality.tester.thread.Robot.changeRobotFaceLeft(currentFace));
    }

    @Test
    public void testChangeRobotFaceRight() {
        String currentFace = "north";
        String expectedFace = "east";
        assertEquals(expectedFace, Robot.changeRobotFaceRight(currentFace));
    }
    
    @Test
    public void testChangeRobotFaceLeftFromWest() {
        String currentFace = "west";
        String expectedFace = "south";
        assertEquals(expectedFace, Robot.changeRobotFaceLeft(currentFace));
    }

    @Test
    public void testChangeRobotFaceRightFromWest() {
        String currentFace = "west";
        String expectedFace = "north";
        assertEquals(expectedFace, Robot.changeRobotFaceRight(currentFace));
    }
    
    @Test
    public void testChangeRobotFaceLeftFromSouth() {
        String currentFace = "south";
        String expectedFace = "east";
        assertEquals(expectedFace, Robot.changeRobotFaceLeft(currentFace));
    }
    
    @Test
    public void testChangeRobotFaceRightFromSouth() {
        String currentFace = "south";
        String expectedFace = "west";
        assertEquals(expectedFace, Robot.changeRobotFaceRight(currentFace));
    }
    
    @Test
    public void testChangeRobotFaceLeftFromEast() {
        String currentFace = "east";
        String expectedFace = "north";
        assertEquals(expectedFace, Robot.changeRobotFaceLeft(currentFace));
    }
    
    @Test
    public void testChangeRobotFaceRightFromEast() {
        String currentFace = "east";
        String expectedFace = "south";
        assertEquals(expectedFace, Robot.changeRobotFaceRight(currentFace));
    }
    
    @Test
    public void testChangeRobotFaceLeftMultipleTimes() {
        String currentFace = "north";
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        String expectedFace = "east";
        assertEquals(expectedFace, currentFace);
    }

    @Test
    public void testChangeRobotFaceRightMultipleTimes() {
        String currentFace = "north";
        currentFace = Robot.changeRobotFaceRight(currentFace);
        currentFace = Robot.changeRobotFaceRight(currentFace);
        currentFace = Robot.changeRobotFaceRight(currentFace);
        String expectedFace = "west";
        assertEquals(expectedFace, currentFace);
    }
    
    @Test
    public void testFullRightRotation() {
        String currentFace = "north";
        currentFace = Robot.changeRobotFaceRight(currentFace);
        currentFace = Robot.changeRobotFaceRight(currentFace);
        currentFace = Robot.changeRobotFaceRight(currentFace);
        currentFace = Robot.changeRobotFaceRight(currentFace);
        assertEquals("north", currentFace);
    }
  
    @Test
    public void testFullLeftRotation() {
        String currentFace = "north";
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        currentFace = Robot.changeRobotFaceLeft(currentFace);
        assertEquals("north", currentFace);
    }
    
    @Test
    public void testMove() {
        commandArray = new String[]{"m", "3"};
        int[] expectedPosition = new int[]{3, 0};  // assuming the robot faces north and moves 3 steps forward
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap));
    }
    
    @Test
    public void testMoveWhileFacingEast() {
        commandArray = new String[]{"m", "2"};
        int[] expectedPosition = new int[]{0, 2}; 
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "east", "up", robotMap));
    }
    
    @Test
    public void testMoveWhileFacingWest() {
        robotPosition = new int[]{0, 2};
        commandArray = new String[]{"m", "2"};
        int[] expectedPosition = new int[]{0, 0}; 
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "west", "up", robotMap));
    }
    
    @Test
    public void testMoveWhileFacingNorth() {
        commandArray = new String[]{"m", "2"};
        int[] expectedPosition = new int[]{2, 0}; 
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap));
    }
    @Test
    public void testMoveWhileFacingSouth() {
        robotPosition = new int[]{2, 0};
        commandArray = new String[]{"m", "2"};
        int[] expectedPosition = new int[]{0, 0}; 
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "south", "up", robotMap));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMoveCommand() {
        commandArray = new String[]{"m", "xyz"};
        Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap);
    }
    
    @Before
    public void setUp() {
        nMatrix = 5;
        robotPosition = new int[]{0, 0};
        robotMap = new String[nMatrix][nMatrix];
        Robot.initialiseArray(nMatrix);
    }
    
    @Test
    public void testInitialPosition() {
        assertArrayEquals(new int[]{0, 0}, robotPosition);
    }
    
    @Test
    public void testInitialPenDirection() {
        assertEquals("up", Robot.penDirection);
    }

    @Test
    public void testInitialRobotFace() {
        assertEquals("north", Robot.robotFace);
    }

    @Test
    public void testInitialRobotPosition() {
        assertArrayEquals(new int[]{0, 0}, Robot.robotPosition);
    }
    
    @Test
    public void testMoveZeroSteps() {
        commandArray = new String[]{"m", "0"};
        int[] expectedPosition = new int[]{0, 0}; 
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap));
    }
    
    @Test
    public void testMoveOutsideGrid() {
        commandArray = new String[]{"m", "6"};  // Trying to move the robot 6 steps, which exceeds the grid size (5)
        int[] expectedPosition = new int[]{0, 0};  // The robot should remain at the initial position
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap));
    }
    
    @Test
    public void testMoveWithinGridBounds() {
        commandArray = new String[]{"m", "2"};
        int[] expectedPosition = new int[]{2, 0};  // Assuming the robot faces north and moves 2 steps forward
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap));
    }

    @Test
    public void testMoveToGridBoundary() {
        commandArray = new String[]{"m", "5"};
        int[] expectedPosition = new int[]{5, 0};  // Assuming the robot faces north and moves 5 steps forward (reaches the grid boundary)
        assertArrayEquals(expectedPosition, Robot.move(robotPosition, commandArray, nMatrix, "north", "up", robotMap));
    }

    @Test
    public void testChangeRobotFaceLeftFromInvalidDirection() {
        String currentFace = "invalid";
        String expectedFace = "north";  // The robot face should not change if the input is invalid
        assertEquals(expectedFace, Robot.changeRobotFaceLeft(currentFace));
    }

    
    @Test
    public void testTrackRobot() {
        Robot.initialiseArray(5);
        String[][] expectedMap = new String[5][5];
        expectedMap[2][2] = "*";
        Robot.trackRobot(2, 2, 2, 2, "down", Robot.robotMap);
        assertArrayEquals(expectedMap, Robot.robotMap);
    }
    

    @Test
    public void testMoveWithPenUp() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{3, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
        assertEquals(null, Robot.robotMap[3][0]); // Check if it did not mark the path
    }
    
    @Test
    public void testMoveWithInvalidPenPosition() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{3, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "invalid_pen_position", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
        assertEquals(null, Robot.robotMap[3][0]); // Check if it did not mark the path
    }
    


    @Test
    public void testTrackRobotWithNegativeCoordinates() {
        Robot.initialiseArray(5);
        String[][] expectedMap = new String[5][5]; // Map should not change
        Robot.trackRobot(-1, -1, -1, -1, "down", Robot.robotMap); // Invalid coordinates
        assertArrayEquals(expectedMap, Robot.robotMap);
    }
    //@Test
    public void testMainMethod() {
        String input = "I 3\nM 2\nL\nM 3\nP\nQ\n";
        String expectedOutput = "Welcome to RoboticPen!!!\n" +
                "Use below commands!!!\n" +
                "[U|u] Pen up \n" +
                "[D|d] Pen down \n" +
                "[R|r] Turn right \n" +
                "[L|l] Turn left \n" +
                "[M s|m s] Move forward s spaces (s is a non-negative integer)\n" +
                "[P|p] Print the floor mapped \n" +
                "[C|c] Print current position of the pen and whether it is up or down and its \n" +
                "facing direction \n" +
                "[Q|q] Stop the program \n" +
                "[I n|i n] Initialize the system: The values of the array floor are zeros and the robot \n" +
                "is back to [0, 0], pen up and facing north. n size of the array, an integer \n" +
                "greater than zero \n" +
                "[H|h] Replay all the commands entered by the user as a history \n" +
                "Enter command: Position: 2 0 - Pen: up - Facing: north\n" +
                "Enter command: Enter command: Enter command:  **  **  **  **  ** \n" +
                " **  **  **  **  ** \n" +
                " **  **  **  **  ** \n" +
                "\n" +
                "Enter command: End of Program\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setIn(inStream);
            System.setOut(new PrintStream(outStream));

            Robot.main(null);

            String actualOutput = outStream.toString();
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        
    }
    //@Test
    public void testMoveDownwardsOutOfBounds() {
        // Set up the robot at the bottom of the grid
        int[] startPosition = new int[]{0, 0};
        Robot.robotPosition = startPosition;

        // Try to move downwards by 1 step
        String[] commandArray = new String[]{"M", "1"};
        int[] expectedPosition = new int[]{0, 0}; // The robot should not move

        // Redirect System.out to capture the printed message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the move method
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "south", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);

        // Check if the message was printed
        String expectedMessage = "Space not available for robot to move downwards\n";
        assertEquals(expectedMessage, outContent.toString());
    }
    @Test
    public void testInitializeMatrixWithValidCommand() {
        // Set up output stream capturing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method with a valid command "I 5" as input
        Robot.InitializeMatrix("I 5");

        // Check the printed output (should be empty for valid input)
        assertEquals("", outContent.toString());
    }

    //@Test
    public void testInitializeMatrixWithInvalidCommand() {
        // Set up output stream capturing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method with an invalid command "I 0" as input
        Robot.InitializeMatrix("I 0");
        // Check the printed output for the invalid command message
        String expectedOutput = "The given command is not valid. Please enter a positive value!!!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    //@Test
    public void testPrintArray() {
        // Set up output stream capturing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Initialize a 3x3 robotMap with some data
        Robot.robotMap = new String[][]{
            {"*", "*", null},
            {null, "*", "*"},
            {"*", null, "*"}
        };

        // Call the method
        Robot.printArray(3);

        // Check the printed output against the expected value
        String expectedOutput = "    *    \n  * *  \n*   *  \n\n";
        assertEquals(expectedOutput, outContent.toString());
    }
//    @Test
//    public void testMoveOutOfBounds() {
//        Robot.robotPosition = new int[]{0, 0}; // Starting at the top-left corner
//        String[] commandArray = new String[]{"M", "6"}; // Trying to move 6 steps, which exceeds the grid size (5)
//
//        // Capture the output stream to check the printed message
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        // Call the move method
//        Robot.move(Robot.robotPosition, commandArray, 5, "north", "up", Robot.robotMap);
//
//        // Check the printed output against the expected message
//        String expectedMessage = "Space not available for robot to move sidewards";
//        assertEquals(expectedMessage, outContent.toString().trim());
//    }
    
}
