package de.hft_stuttgart.cpl;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

class EXceLlenSeTest {

    @Test
    void testPassExcercise() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C14R7 = 'Hello' " +
                "for j from 1 to 9 do C14R7 = C14R7 & ' World' end");
    }

    @Test
    void testMultiplicationWithParentheses() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("(2+3)*4\n");
    }

    @Test
    void testMultipleExpressions() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("(2+3)*42+3*4\n5+6*7\n");
    }

    @Test
    void testBasicAssignment() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C1R1 = 42\n");
    }

    @Test
    void testAssignmentWithFormattedStringExpression() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C2R1 = 3.14159 FORMATTED '\"%.2f\"'\n");
    }

    @Test
    void testAssignmentWithNumberExpression() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C3R1 = C1R1 + C2R1\n");
    }

    @Test
    void testAssignmentWithString() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C4R1 = 'Hello'\n");
    }

    @Test
    void testAssignmentWithStringExpression() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C4R1 = 'Hello' & 'World'\n");
    }

    @Test
    void testAssignmentWithTimestampExpression() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C5R1 = {2023-06-01 12:00:00} + 3600\n");
    }

    @Test
    void testBooleanExpressionAssignment() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C6R1 = C3R1 > C1R1\n");
    }

    @Test
    void testForLoop() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("for i from 1 to 5 do C7+iR1 = 'Iteration ' & i end\n");
    }

    @Test
    void testAssignmentWithNegativeDecimalLiteral() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C1R1 = -10\n");
    }

    @Test
    void testAssignmentWithScientificNotation() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C2R1 = 1.23E-4\n");
    }

    @Test
    void testAssignmentWithEmptyStringLiteral() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C3R1 = ''\n");
    }

    @Test
    void testAssignmentWithCellReference() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C4R1 = C1R1 + C2R1 * C3R1\n");
    }

    @Test
    void testAssignmentWithNestedStringExpression() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C5R1 = 'Hello ' & ('Beautiful ' & 'World')\n");
    }

    @Test
    void testBooleanExpressionWithTimestamp() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("C6R1 = {2023-06-01 12:00:00} <= {2023-06-02 12:00:00}\n");
    }

    @Test
    void testForLoopWithNegativeStep() throws ParseException, de.hft_stuttgart.cpl.ParseException {
        testParserSingle("for j from 5 to 1 do C7+jR1 = 'Iteration ' & j end\n");
    }

    void testParserSingle(String input) throws ParseException, de.hft_stuttgart.cpl.ParseException {
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        EXceLlenSe parser = new EXceLlenSe(bis);
        while (true) {
            try {
                parser.parseOneLine();
            } catch (de.hft_stuttgart.cpl.ParseException ex) {
                if (ex.getMessage().equals("End Of Input")) {
                    break;
                } else {
                    throw ex;
                }
            }
        }
    }
}