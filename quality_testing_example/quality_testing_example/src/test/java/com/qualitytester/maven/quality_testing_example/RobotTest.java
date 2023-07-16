package com.qualitytester.maven.quality_testing_example;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;

// Rest of the code...

public class RobotTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;
    private int[][] robotMap;

    @Before
    public void setUp() {
        robotMap = new int[5][5];
    }

    @After
    public void restoreStreams() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testChangeRobotFaceLeft() {
        String initialFace = "north";
        String expectedFace = "west";
        String result = Robot.changeRobotFaceLeft(initialFace);
        System.out.println("Initial Face: " + initialFace);
        System.out.println("Expected Face: " + expectedFace);
        System.out.println("Result: " + result);
        System.out.println("Test Result: " + (result.equals(expectedFace) ? "Passed" : "Failed"));
    }

    @Test
    public void testChangeRobotFaceRight() {
        String initialFace = "north";
        String expectedFace = "east";
        String result = Robot.changeRobotFaceRight(initialFace);
        System.out.println("Initial Face: " + initialFace);
        System.out.println("Expected Face: " + expectedFace);
        System.out.println("Result: " + result);
        System.out.println("Test Result: " + (result.equals(expectedFace) ? "Passed" : "Failed"));
    }

    @Test
    public void testTrackRobot() {
        int oldX = 1;
        int oldY = 2;
        int newX = 3;
        int newY = 4;
        String penDirection = "down";

        String[][] expectedRobotMap =
                {
                        {"0", "0", "0", "0", "0"},
                        {"0", "0", "1", "1", "1"},
                        {"0", "0", "1", "1", "1"},
                        {"0", "0", "1", "1", "1"},
                        {"0", "0", "0", "0", "0"}
                };

        String[][] result = Robot.trackRobot(oldX, oldY, newX, newY, penDirection, new String[5][5]);
        System.out.println("Initial Robot Map:");
        printRobotMap(new String[5][5]);
        System.out.println("Expected Robot Map:");
        printRobotMap(expectedRobotMap);
        System.out.println("Result Robot Map:");
        printRobotMap(result);
        System.out.println("Test Result: " + (Arrays.deepEquals(result, expectedRobotMap) ? "Passed" : "Failed"));
    }

    public void printRobotMap(String[][] robotMap) {
        for (int i = 0; i < robotMap.length; i++) {
            for (int j = 0; j < robotMap[i].length; j++) {
                System.out.print(robotMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testTrackRobotPenDown() {
        String[][] robotMap = new String[3][3];
        String[][] result = Robot.trackRobot(0, 0, 2, 2, "down", robotMap);
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                assertEquals("1", result[i][j]);
                System.out.println("Test Result: " + (result[i][j].equals("1") ? "Passed" : "Failed"));
            }
        }
    }

    @Test
    public void testTrackRobotPenUp() {
        String[][] robotMap = new String[3][3];
        String[][] result = Robot.trackRobot(0, 0, 2, 2, "up", robotMap);
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                assertNull(result[i][j]);
                System.out.println("Test Result: " + (result[i][j] == null ? "Passed" : "Failed"));
            }
        }
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRobotProgram() {
        String input = "I 5\nM 2\nR\nL\nU\nD\nC\nP\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Position: 0 0 - Pen: up - Facing: north\n" +
                "* * * * * \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testInvalidCommand() {
        String input = "X\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Enter a valid command\n" +
                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testMoveOutOfBounds() {
        String input = "I 3\nM 5\nC\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Cannot move robot beyond the available space\n" +
                "Position: 0 0 - Pen: up - Facing: north\n" +
                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testEmptyMatrix() {
        String input = "I 0\nC\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Position: 0 0 - Pen: up - Facing: north\n" +
                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPenDownMovement() {
        String input = "I 5\nD\nM 2\nC\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Position: 0 2 - Pen: down - Facing: north\n" +
                "* * *     \n" +
                "* * *     \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPenUpMovement() {
        String input = "I 5\nD\nU\nM 2\nC\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Position: 0 2 - Pen: up - Facing: north\n" +
                                "          \n" +
                                "          \n" +
                                "          \n" +
                                "          \n" +
                                "          \n" +
                                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testFaceRight() 
	{
        String input = "I 3\nR\nC\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Position: 0 0 - Pen: up - Facing: east\n" +
                                "  *       \n" +
                                "          \n" +
                                "          \n" +
                                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testFaceLeft() 
	{
        String input = "I 3\nL\nC\nQ\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Robot.main(new String[]{});

        String expectedOutput = "Position: 0 0 - Pen: up - Facing: west\n" +
                                "      *   \n" +
                                "          \n" +
                                "          \n" +
                                "End of Program\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }


}
