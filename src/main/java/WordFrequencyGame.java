import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String S = "\\s+";
    public static final String SPACE = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = getInitialWordFrequency(sentence);

            wordFrequencyList = getWordFrequencies(wordFrequencyList);

            return getResult(wordFrequencyList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private static String getResult(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToWordFrequency = getListMap(wordFrequencyList);

        return wordToWordFrequency.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount())
                .collect(Collectors.toList());
    }

    private static List<WordFrequency> getInitialWordFrequency(String sentence) {
        String[] words = sentence.split(S);

        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        return wordFrequencyList = Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
