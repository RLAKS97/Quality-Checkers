package com.qualitytester.maven.quality_testing_example;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class testRobot{

    //private Robot robot;
    private String[][] robotMap;
    private int nMatrix;
    private String[] commandArray;
    private int[] robotPosition;



    @Test
    public void testChangeRobotFaceLeft() {
        String currentFace = "north";
        String expectedFace = "west";
        assertEquals(expectedFace, Robot.changeRobotFaceLeft(currentFace));
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
    public void testInputCommandInitializeFloor() {
        // Set up input stream with "I 3" as the user input
        String input = "I 3\nQ\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call inputCommand()
        Robot.inputCommand();

        // Check that floor has been initialized to a 3x3 array
        int[][] expectedrobotMap = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(expectedrobotMap, Robot.robotMap);
    }


}




