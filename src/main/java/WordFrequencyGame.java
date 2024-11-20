import java.util.*;

public class WordFrequencyGame {

    public static final String S = "\\s+";
    public static final String SPACE = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(S).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordFrequency> wordFrequencyList = getInitialWordFrequency(sentence);

                wordFrequencyList = getWordFrequencies(wordFrequencyList);

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordFrequency w : wordFrequencyList) {
                    String s = w.getWord() + SPACE + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = getListMap(wordFrequencyList);

        List<WordFrequency> list = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()) {
            WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
            list.add(wordFrequency);
        }
        wordFrequencyList = list;

        wordFrequencyList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        return wordFrequencyList;
    }

    private static List<WordFrequency> getInitialWordFrequency(String sentence) {
        String[] words = sentence.split(S);

        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        wordFrequencyList = Arrays.stream(words)
                .map(word -> new WordFrequency(word,1))
                .toList();
        return wordFrequencyList;
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!map.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            } else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }

        return map;
    }


}
