package helpers;

public class TextProcessingHelper {

    public static int count(String countLevel, String text) {
        return countLevel.equals("word") ? StringHelper.getWordsCountFromText(text) :
                StringHelper.getCharsCount(text);
    }
}
