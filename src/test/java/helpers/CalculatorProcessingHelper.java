package helpers;

import java.util.List;

public class CalculatorProcessingHelper {

    public static Calculator setupCalculator(String operation) {
        switch (operation) {
            case "sum":
                return new SumCalculatorHelper();
            case "subtract":
                return new DifferenceCalculatorHelper();
            default:
                return null;
        }
    }

    public static int findTempCalculationOfColumn(List<String> column, Calculator calculator) {
        int tempCalc = 0;
        for (String element : column) {
            // for subtract operation the first iteration is number-temp (where temp=0),
            // while from the second on -> temp-number
            tempCalc = (column.indexOf(element) == 0) ? calculator.calculate(Integer.parseInt(element), tempCalc)
                    : calculator.calculate(tempCalc, Integer.parseInt(element));
        }
        return tempCalc;
    }
}
