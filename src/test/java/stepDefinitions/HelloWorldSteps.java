package stepDefinitions;

import io.cucumber.java.en.Given;

public class HelloWorldSteps {

    @Given("^first step is done$")
    public void printHelloWorld() {
        System.out.println("Hello World!");
    }
}
