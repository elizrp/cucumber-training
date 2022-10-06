package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterFormSteps {

    @Given("user goes to registration page")
    public void navigateToRegistrationPage() {
        System.out.println("Registration page displayed");
    }

    @Given("all fields are empty")
    public void verifyFieldsAreEmpty() {
        System.out.println("All fields in registration form are empty");
    }

    @When("user enters {string} in username field")
    public void enterUsername(String username) {
        System.out.println(String.format("Enter username: %s", username));
    }

    @When("user enters {string} in email field")
    public void enterEmail(String email) {
        System.out.println(String.format("Enter email: %s", email));
    }

    @When("user enters {string} in password field")
    public void enterPassword(String password) {
        System.out.println(String.format("Enter password: %s", password));
    }

    @When("user enters {string} in repeat password field")
    public void repeatPassword(String password) {
        System.out.println(String.format("Repeat password: %s", password));
    }

    @When("user enters {string} in phone field")
    public void enterPhoneNumber(String phone) {
        System.out.println(String.format("Enter phone: %s", phone));
    }

    @When("user enters {string} in city field")
    public void enterCity(String city) {
        System.out.println(String.format("Enter city: %s", city));
    }

    @When("user clicks on the register button")
    public void clickRegisterButton() {
        System.out.println("Click Submit");
    }

    @Then("message for successful registration is displayed")
    public void displaySuccessMessage() {
        System.out.println("Successful registration!");
    }

    @Then("user is redirected to login page")
    public void navigateToLoginPage() {
        System.out.println("Login page displayed");
    }

    @Then("user stays on register page")
    public void verifyUserIsOnRegisterPage() {
        System.out.println("User is on register page");
    }

    @Then("registration error message is displayed")
    public void displayErrorMessage() {
        System.out.println("Unsuccessful registration!");
    }
}
