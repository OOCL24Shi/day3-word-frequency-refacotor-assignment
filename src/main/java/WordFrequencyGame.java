import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map = getListMap(wordFrequencyList);

                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequency);
                }
                wordFrequencyList = list;

                wordFrequencyList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency w : wordFrequencyList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getValue(), arr);
            } else {
                map.get(wordFrequency.getValue()).add(wordFrequency);
            }
        }

        return map;
    }


}
