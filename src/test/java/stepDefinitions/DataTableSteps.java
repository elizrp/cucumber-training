package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

public class DataTableSteps {

    @Given("the following student names exist")
    public void extractDataTableIntoList(DataTable dataTable) {
        List<String> studentNames = dataTable.asList();
        for (String name : studentNames) {
            System.out.println(name);
        }
    }

    @Given("the following credentials exist")
    public void extractDataTableIntoMap(DataTable dataTable) {
        Map<String, String> rows = dataTable.asMap(String.class, String.class);

        for (Map.Entry<String, String> row : rows.entrySet())
            System.out.println(String.format("User %s has %s for password", row.getKey(), row.getValue()));
    }
}
