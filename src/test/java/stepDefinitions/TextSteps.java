package stepDefinitions;

import helpers.StringHelper;
import helpers.TextProcessingHelper;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextSteps implements En {

    private String word1, word2, sentence1, sentence2, text;
    private boolean isCaseSensitive;
    private int actualResult;

    // words steps with lambda expressions
    public TextSteps() {

        When("the first word is {word}", (String input) -> {
            word1 = input;
        });

        And("the second word is {word}", (String input) -> {
            word2 = input;
        });

        Then("verify words are equal", () -> {
            assertTrue("Words are not equal!", StringHelper.areWordsEqual(word1, word2, isCaseSensitive));
        });
    }

    @ParameterType("on|off")
    public boolean mode(String mode) {
        return mode.equals("on");
    }

    @ParameterType("word|char")
    public String symbolLevel(String level) {
        return level;
    }

    // background step
    @Given("case-sensitivity is {mode}")
    public void setCaseSensitivityMode(boolean mode) {
        isCaseSensitive = mode;
    }

    // sentences steps
    @When("the first sentence is {}")
    public void setFirstSentence(String input) {
        sentence1 = input;
    }

    @When("the second sentence is {}")
    public void setSecondSentence(String input) {
        sentence2 = input;
    }

    @Then("verify sentences are equal")
    public void verifySentencesAreEqual() {
        assertTrue("Sentences are not equal!", StringHelper.areSentencesEqual(sentence1, sentence2, isCaseSensitive));
    }

    // count in text steps
    @Given("the following text exists")
    public void setText(String docString) {
        text = docString;
    }

    @When("user wants to find the {symbolLevel} count")
    public void countInText(String symbolLevel) {
        actualResult = TextProcessingHelper.count(symbolLevel, text);
    }

    @Then("verify the count is {int}")
    public void verifyCountInText(int expectedResult) {
        assertEquals("Incorrect count result!", expectedResult, actualResult);
    }
}
