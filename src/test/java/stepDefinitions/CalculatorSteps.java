package stepDefinitions;

import helpers.Calculator;
import helpers.DifferenceCalculatorHelper;
import helpers.SumCalculatorHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class CalculatorSteps {

    private Calculator calculator;

    private int int1, int2;
    private float float1, float2;

    // use Object type to make it generic
    private Object actualResult;

    private List<String> firstColumn;
    private List<String> secondColumn;
    int tempCalc, firstColumnResult, secondColumnResult;

    // setup type of calculator
    @ParameterType("sum|subtract")
    public Calculator operation(String operation) {
        switch (operation) {
            case "sum":
                this.calculator = new SumCalculatorHelper();
                break;
            case "subtract":
                this.calculator = new DifferenceCalculatorHelper();
                break;
            default:
                return null;
        }
        return calculator;
    }

    // numeric calculations steps
    @Given("user has two integer numbers - {int} and {int}")
    public void setIntegerNumbers(Integer a, Integer b) {
        int1 = a;
        int2 = b;
    }

    @Given("user has two float numbers - (.*) and (.*)$")
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
    @Then("the result is (.*)$")
    public void verifyResult(Object expectedResult) {
        assertEquals("The expected result does not equal the actual result", expectedResult, actualResult);
    }

    // column calculations steps
    @Given("user has the following numbers")
    public void setupTableWithNumbers(DataTable table) {
        firstColumn = table.column(0);
        secondColumn = table.column(1);
    }

    @When("user wants to {operation} all numbers in {int} column")
    public void calculateNumbersByColumn(Calculator calculator, int column) {
        switch (column) {
            case 0:
                for (String element : firstColumn) {
                    // for subtract operation the first iteration is number-temp (where temp=0),
                    // while from the second on -> temp-number
                    tempCalc = (firstColumn.indexOf(element) == 0) ? calculator.calculate(Integer.parseInt(element), tempCalc)
                            : calculator.calculate(tempCalc, Integer.parseInt(element));
                }
                firstColumnResult = tempCalc;
                tempCalc = 0;
                break;
            case 1:
                for (String element : secondColumn) {
                    // for subtract operation the first iteration is number-temp (where temp=0),
                    // while from the second on -> temp-number
                    tempCalc = (secondColumn.indexOf(element) == 0) ? calculator.calculate(Integer.parseInt(element), tempCalc)
                            : calculator.calculate(tempCalc, Integer.parseInt(element));
                }
                secondColumnResult = tempCalc;
                break;
            default:
                fail("Column must be between 0-1!");
        }
    }

    @Then("the result in the {int} column is {int}")
    public void verifyColumnResult(int column, int expectedResult) {
        if (!(column == 0 || column == 1)) {
            fail("Column number must be 0 or 1!");
        }
        assertEquals(String.format("The expected result for column %d does not equal the actual result", column + 1),
                expectedResult, column == 0 ? firstColumnResult : secondColumnResult);

    }

    @And("the calculation of the second column is (.*) than the calculation of the first column$")
    public void compareColumnCalculations(String operator) {
        if (!(operator.equals("bigger") || operator.equals("less"))) {
            fail("Comparison operator must be \"bigger\" or \"less\"!");
        }
        assertTrue(String.format("The calculation of the second column is NOT %s than the first column", operator),
                operator.equals("bigger") ? secondColumnResult > firstColumnResult : secondColumnResult < firstColumnResult);
    }
}
