package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginFormSteps {

    @Given("user goes to login page")
    public void navigateToLoginPage() {
        System.out.println("Login page displayed");
    }

    @When("user enters {} as username and {} as password")
    public void enterUsernameAndPassword(String username, String password) {
        System.out.println(String.format("Enter username: %s and password: %s", username, password));
    }

    @When("user clicks on the submit button")
    public void clickSubmitButton() {
        System.out.println("Click Submit");
    }

    @Then("user home page is displayed")
    public void displayHomePage() {
        System.out.println("Home page displayed");
    }

    @Given("the following user exists")
    public void createMultipleUsers(DataTable dataTable) {
        System.out.println("The user in the data table exists");
    }

    @But("the following user does not exist")
    public void verifyUserNotExists(DataTable dataTable) {
        System.out.println("The user in the data table does not exist");
    }

    @Then("user stays on login page")
    public void verifyUserIsOnLoginPage() {
        System.out.println("User is on login page");
    }

    @Then("error message is displayed")
    public void displayErrorMessage() {
        System.out.println("Error message displayed");
    }

    @Then("username and password fields are cleared")
    public void clearUsernameAndPasswordFields() {
        System.out.println("Username and password fields cleared");
    }

    @Then("there is a message about number of login attempts left")
    public void displayLoginAttemptsMessage() {
        System.out.println("Login attempts left: X");
    }
}
