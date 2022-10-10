package stepDefinitions;

import helpers.Calculator;
import helpers.CalculatorProcessingHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static helpers.CalculatorProcessingHelper.*;
import static org.junit.Assert.*;

public class CalculatorSteps {

    private static final String columnNumberErrorMessage = "Column number must be 0 or 1!";

    private int int1, int2;
    private float float1, float2;

    // use Object type to make it generic
    private Object actualResult;

    private List<String> firstColumn, secondColumn;
    int firstColumnResult, secondColumnResult;

    // setup type of calculator
    @ParameterType("sum|subtract")
    public Calculator operation(String operation) {
        return CalculatorProcessingHelper.setupCalculator(operation);
    }

    // numeric calculations steps
    @Given("user has two integer numbers - {int} and {int}")
    public void setIntegerNumbers(Integer a, Integer b) {
        int1 = a;
        int2 = b;
    }

    @Given("user has two float numbers - {float} and {float}")
    public void setFloatNumbers(float a, float b) {
        float1 = a;
        float2 = b;
    }

    @When("user wants to {operation} the integer numbers")
    public void calculateForIntegers(Calculator calculator) {
        // parse to string, so it can be used in the verification
        actualResult = Integer.toString(calculator.calculate(int1, int2));
    }

    @When("user wants to {operation} the float numbers")
    public void calculateForFloats(Calculator calculator) {
        // parse to string, so it can be used in the verification
        actualResult = Float.toString(calculator.calculate(float1, float2));
    }

    // use Object type to make it generic
    @Then("the result is {}")
    public void verifyResult(Object expectedResult) {
        assertEquals("The expected result does not equal the actual result", expectedResult, actualResult);
    }

    // column calculations steps
    @Given("user has the following numbers")
    public void setupTableWithNumbers(DataTable table) {
        firstColumn = table.column(0);
        secondColumn = table.column(1);
    }

    @When("user wants/wishes to {operation} all numbers in {int} column")
    public void calculateNumbersByColumn(Calculator calculator, int columnIndex) {
        switch (columnIndex) {
            case 0:
                firstColumnResult = findTempCalculationOfColumn(firstColumn, calculator);
                break;
            case 1:
                secondColumnResult = findTempCalculationOfColumn(secondColumn, calculator);
                break;
            default:
                fail(columnNumberErrorMessage);
        }
    }

    @Then("the result in the {int} column is {int}")
    public void verifyColumnResult(int column, int expectedResult) {
        if (!(column == 0 || column == 1)) {
            fail(columnNumberErrorMessage);
        }
        assertEquals(String.format("The expected result for column %d does not equal the actual result", column + 1),
                expectedResult, column == 0 ? firstColumnResult : secondColumnResult);
    }

    @And("the calculation of the second column is {} than the calculation of the first column")
    public void compareColumnCalculations(String operator) {
        if (!(operator.equals("bigger") || operator.equals("less"))) {
            fail("Comparison operator must be \"bigger\" or \"less\"!");
        }
        assertTrue(String.format("The calculation of the second column is NOT %s than the first column", operator),
                operator.equals("bigger") ? secondColumnResult > firstColumnResult : secondColumnResult < firstColumnResult);
    }
}
