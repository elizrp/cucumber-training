package helpers;

import static helpers.StringHelper.*;

public class TextProcessingHelper {

    public static int count(String countLevel, String text) {
        return countLevel.equals("word") ? getWordsCountFromText(text) :
                getCharsCount(text);
    }
}
