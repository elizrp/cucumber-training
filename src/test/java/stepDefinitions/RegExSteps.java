package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static helpers.StringHelper.areSentencesEqual;
import static helpers.StringHelper.areWordsEqual;
import static helpers.TextProcessingHelper.count;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinitions.TextSteps.isCaseSensitive;

public class RegExSteps {

    private String firstWord, secondWord, firstSentence, secondSentence, text;

    // words regex steps
    @Given("^first word is ([cC]ucumber[a-zA-Z]+)$")
    public void setFirstWord(String word) {
        firstWord = word;
    }

    @Given("^first occurrence of ([a-zA-Z]+) is removed$")
    public void removeFirstOccurrenceOfSymbol(String symbol) {
        firstWord = firstWord.replaceFirst(symbol, "");
    }

    @When("^second word is ([cC]ucumber[a-zA-Z]+)$")
    public void setSecondWord(String word) {
        secondWord = word;
    }

    @Given("^first word is ([cC]ucumber[0-9]+)$")
    public void setFirstWordWithDigits(String word) {
        firstWord = word;
    }

    @And("^all occurrences of ([0-9]+) are removed$")
    public void removeAllOccurrencesOfSymbol(String symbol) {
        firstWord = firstWord.replaceAll(symbol, "");
    }

    @When("^second word is (cucumber)$")
    public void setSecondWordWithDigits(String word) {
        secondWord = word;
    }

    @Then("verify the words are equal")
    public void verifyWordsAreEqual() {
        assertTrue("Words are not equal!", areWordsEqual(firstWord, secondWord, isCaseSensitive));
    }

    // sentences regex steps
    @Given("^first sentence is ((\"Cucumber\\sis\\samazing((\\(\\)\\[\\]\\{\\}\\:)+\")))$")
    public void setFirstSentence(String sentence) {
        firstSentence = sentence;
    }

    @And("^first occurrence of ((\\(|\\)|\\[|\\]|\\{|\\}|\\:)+) is removed$")
    public void removeFirstOccurrenceInSentence(String symbol) {
        firstSentence = firstSentence.replaceFirst(String.format("\\%s", symbol), "");
    }

    @When("^second sentence is ((\"cucumber\\sis\\samazing((\\(\\)\\[\\]\\{\\}\\:)+\")))$")
    public void setSecondSentence(String sentence) {
        secondSentence = sentence;
    }

    @Then("verify the sentences are equal")
    public void verifySentencesAreEqual() {
        assertTrue("Sentences are not equal!", areSentencesEqual(firstSentence, secondSentence, isCaseSensitive));
    }

    @Given("^first sentence is ((\"Cucumber\\sis\\samazing((\\.\\?\\!\\;\\,\\-\\')+\")))$")
    public void setFirstSentenceSpecial(String sentence) {
        firstSentence = sentence;
    }

    @And("^all occurrences of ((\\.|\\?|\\!|\\;|\\,|\\-|\\')+) are removed$")
    public void removeAllOccurrencesOfInSentence(String symbol) {
        firstSentence = firstSentence.replaceAll(String.format("\\%s", symbol), "");
    }

    @When("^second sentence is ((\"cucumber\\sis\\samazing\"))$")
    public void setSecondSentenceSpecial(String sentence) {
        secondSentence = sentence;
    }

    @Given("input text")
    public void setInputText(String input) {
        text = input;
    }

    @When("^all whitespaces before ((\\.|\\?|\\!|\\;|\\,|\\-|\\')+) are removed$")
    public void removeAllWhitespacesBeforeSymbol(String symbol) {
        text = text.replaceAll(String.format("(\\s){1,}\\%s", symbol), symbol);
    }

    @When("^all consecutive whitespace chars are replaced with a single space$")
    public void removeAllWhitespaces() {
        text = text.replaceAll("(\\s){2,}", " ");
    }

    @Then("verify the input text {symbolLevel} count is equal to {int}")
    public void verifyCountInText(String symbolLevel, int expectedResult) {
        assertEquals("Incorrect count result!", expectedResult, count(symbolLevel, text));
    }
}
