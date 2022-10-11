package helpers;

import static helpers.StringHelper.getCharsCount;
import static helpers.StringHelper.getWordsCountFromText;

public class TextProcessingHelper {

    public static int count(String countLevel, String text) {
        return countLevel.equals("word") ? getWordsCountFromText(text) :
                getCharsCount(text);
    }
}
