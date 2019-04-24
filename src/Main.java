import java.util.*;


public class Main {

    public static void main(String[] args) {
        SpellChecker bleh = new SpellChecker("/Users/Rhea/IdeaProjects/SpellChecker/src/words.txt");
        bleh.getIncorrectWords("/Users/Rhea/IdeaProjects/SpellChecker/src/test.txt");

        List<String> incorrectWords = bleh.getIncorrectWords("/Users/Rhea/IdeaProjects/SpellChecker/src/test.txt");
        for (String word : incorrectWords){
            System.out.println("for " + word + ": ");
            System.out.println(bleh.getSuggestions(word));
        }
    }
}
