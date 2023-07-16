package com.qualitytester.maven.quality_testing_example;

import org.junit.Test;
import static org.junit.Assert.*;

public class RobotTest2 {

    @Test
    public void testChangeRobotFaceLeft() {
        assertEquals("west", Robot.changeRobotFaceLeft("north"));
        assertEquals("south", Robot.changeRobotFaceLeft("west"));
        assertEquals("east", Robot.changeRobotFaceLeft("south"));
        assertEquals("north", Robot.changeRobotFaceLeft("east"));
    }

    @Test
    public void testChangeRobotFaceRight() {
        assertEquals("east", Robot.changeRobotFaceRight("north"));
        assertEquals("west", Robot.changeRobotFaceRight("south"));
        assertEquals("north", Robot.changeRobotFaceRight("west"));
        assertEquals("south", Robot.changeRobotFaceRight("east"));
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
    public void testInitialiseArray() {
        Robot.initialiseArray(3);
        assertEquals("up", Robot.penDirection);
        assertEquals("north", Robot.robotFace);
        assertEquals(3, Robot.robotMap.length);
        assertEquals(3, Robot.robotMap[0].length);
    }

    @Test
    public void testMove() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{3, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }
    @Test
    public void testMoveNorthBeyondBounds() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{4, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{4, 0}; // Should not move as it is beyond bounds
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveSouthBeyondBounds() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{0, 0}; // Should not move as it is beyond bounds
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "south", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveEastBeyondBounds() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 4};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{0, 4}; // Should not move as it is beyond bounds
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "east", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveWestBeyondBounds() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{0, 0}; // Should not move as it is beyond bounds
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "west", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testTrackRobotWithInvalidCoordinates() {
        Robot.initialiseArray(5);
        String[][] expectedMap = new String[5][5]; // Map should not change
        Robot.trackRobot(6, 6, 6, 6, "down", Robot.robotMap); // Invalid coordinates
        assertArrayEquals(expectedMap, Robot.robotMap);
    }

    @Test
    public void testChangeRobotFaceLeftFromInvalidDirection() {
        assertEquals("north", Robot.changeRobotFaceLeft("invalid")); // Returns north for any invalid direction
    }

    @Test
    public void testChangeRobotFaceRightFromInvalidDirection() {
        assertEquals("north", Robot.changeRobotFaceRight("invalid")); // Returns north for any invalid direction
    }

    @Test
    public void testInitialiseArrayWithZeroSize() {
        Robot.initialiseArray(0);
        assertEquals("up", Robot.penDirection);
        assertEquals("north", Robot.robotFace);
        assertEquals(0, Robot.robotMap.length);
    }

    @Test
    public void testMoveWithPenDown() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{3, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "down", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
        assertEquals("*", Robot.robotMap[3][0]); // Check if it marked the path
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
    public void testMoveZeroSteps() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "0"};
        int[] expectedPosition = new int[]{0, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testInvalidMoveCommand() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"INVALID", "3"};
        int[] expectedPosition = new int[]{0, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testChangeRobotFaceRightFromNullDirection() {
        assertEquals("north", Robot.changeRobotFaceRight(null));
    }

    @Test
    public void testChangeRobotFaceLeftFromNullDirection() {
        assertEquals("north", Robot.changeRobotFaceLeft(null));
    }

    @Test
    public void testInitialiseArrayWithNegativeSize() {
        Robot.initialiseArray(-3);
        assertEquals(0, Robot.robotMap.length);  // Assuming it initializes the array with 0 size when given a negative value.
    }

    @Test
    public void testMoveWithNegativeSteps() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{2, 2};
        String[] commandArray = new String[]{"M", "-2"};
        int[] expectedPosition = new int[]{2, 2}; // Should not move as the steps are negative.
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveWithNonNumericSteps() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{2, 2};
        String[] commandArray = new String[]{"M", "ABC"};
        int[] expectedPosition = new int[]{2, 2}; // Should not move as the steps are not a number.
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveWithEmptyCommand() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{2, 2};
        String[] commandArray = new String[]{""};
        int[] expectedPosition = new int[]{2, 2}; // Should not move as the command is empty.
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveWithNullCommand() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{2, 2};
        String[] commandArray = null;
        int[] expectedPosition = new int[]{2, 2}; // Should not move as the command is null.
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveWithInvalidDirection() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{2, 2};
        String[] commandArray = new String[]{"M", "2"};
        int[] expectedPosition = new int[]{2, 2}; // Should not move as the direction is invalid.
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "invalid_direction", "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testMoveWithNullDirection() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{2, 2};
        String[] commandArray = new String[]{"M", "2"};
        int[] expectedPosition = new int[]{2, 2}; // Should not move as the direction is null.
        int[] newPosition = Robot.move(startPosition, commandArray, 5, null, "up", Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
    }

    @Test
    public void testTrackRobotWithNullDirection() {
        Robot.initialiseArray(5);
        String[][] expectedMap = new String[5][5]; // Map should not change
        Robot.trackRobot(2, 2, 2, 2, null, Robot.robotMap); // Invalid direction
        assertArrayEquals(expectedMap, Robot.robotMap);
    }

    @Test
    public void testTrackRobotWithNegativeCoordinates() {
        Robot.initialiseArray(5);
        String[][] expectedMap = new String[5][5]; // Map should not change
        Robot.trackRobot(-1, -1, -1, -1, "down", Robot.robotMap); // Invalid coordinates
        assertArrayEquals(expectedMap, Robot.robotMap);
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
    public void testMoveWithNullPenPosition() {
        Robot.initialiseArray(5);
        int[] startPosition = new int[]{0, 0};
        String[] commandArray = new String[]{"M", "3"};
        int[] expectedPosition = new int[]{3, 0};
        int[] newPosition = Robot.move(startPosition, commandArray, 5, "north", null, Robot.robotMap);
        assertArrayEquals(expectedPosition, newPosition);
        assertEquals(null, Robot.robotMap[3][0]); // Check if it did not mark the path
    }
}




