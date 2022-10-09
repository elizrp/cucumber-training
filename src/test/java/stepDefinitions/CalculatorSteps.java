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
        if (column == 0) {
            for (String element : firstColumn) {
                if (firstColumn.indexOf(element) == 0) {
                    tempCalc = calculator.calculate(Integer.parseInt(element), tempCalc);
                } else {
                    tempCalc = calculator.calculate(tempCalc, Integer.parseInt(element));
                }
            }
            firstColumnResult = tempCalc;
            tempCalc = 0;
        } else if (column == 1) {
            for (String element : secondColumn) {
                if (secondColumn.indexOf(element) == 0) {
                    tempCalc = calculator.calculate(Integer.parseInt(element), tempCalc);
                } else {
                    tempCalc = calculator.calculate(tempCalc, Integer.parseInt(element));
                }
            }
            secondColumnResult = tempCalc;
        } else {
            System.out.println("Column must be between 0-1!");
        }
    }

    @Then("the result in the {int} column is {int}")
    public void verifyColumnResult(int column, int expectedResult) {
        if (column == 0) {
            assertEquals("The expected result for column 1 does not equal the actual result", expectedResult, firstColumnResult);
        } else {
            assertEquals("The expected result for column 2 does not equal the actual result", expectedResult, secondColumnResult);
        }
    }

    @And("the calculation of the second column is (.*) than the calculation of the first column$")
    public void compareColumnCalculations(String operator) {
        if (operator.equals("bigger")) {
            assertTrue("The calculation of the second column is NOT bigger than the first column",
                    secondColumnResult > firstColumnResult);
        } else if (operator.equals("less")) {
            assertTrue("The calculation of the second column is NOT less than the first column",
                    secondColumnResult < firstColumnResult);
        } else {
            fail("Comparison operator must be \"bigger\" or \"less\"!");
        }
    }
}
