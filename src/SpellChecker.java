import java.util.Scanner;
import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class SpellChecker implements SpellCheckerInterface{

    HashSet<String> dictionary = new HashSet<String>();

    public SpellChecker(String filename) {
        File dict = new File(filename);
        try {
            Scanner sc = new Scanner(dict);

            while (sc.hasNext()){
                dictionary.add(sc.next().toLowerCase());
            }
        }
        catch (FileNotFoundException fe){
            System.err.println("error: file " + filename + " not found");
        }
    }


    public List<String> getIncorrectWords(String filename) {
        ArrayList<String> incorrectWords = new ArrayList<>();

        try {
            File in = new File(filename);
            Scanner sc = new Scanner(in);

            while (sc.hasNext()){
                String next = sc.next();
                next = next.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                if (!dictionary.contains(next)){
                    incorrectWords.add(next);
                }
            }
        }
        catch(FileNotFoundException fe){
            System.err.println("error: file " + filename + " not found");
        }
        return incorrectWords;
    }



    private static HashSet<String> newWords = new HashSet<>();
    public Set<String> getSuggestions (String word) {
        newWords.clear();
        HashSet<String> suggestions = new HashSet<>();
        ArrayList<Character> WordAsList = new ArrayList<>();
        for (char ch : word.toCharArray()){
            WordAsList.add(ch);
        }

        addChar(WordAsList);
        removeChar(WordAsList);
        swapChars(WordAsList);

        for (String aWord : newWords){
            if (dictionary.contains(aWord)){
                suggestions.add(aWord);
            }
        }
        return suggestions;
    }

    public static void addChar(ArrayList<Character> listedWord){
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            addChar2(listedWord, alphabet);
        }
    }

    public static void addChar2(ArrayList<Character> list, char c){
        for (int i = 0; i < list.size() + 1; i++){
            ArrayList<Character> tempList = new ArrayList<>(list);
            tempList.add(i, c);
            addWord(tempList);
        }
    }

    public static void removeChar(ArrayList<Character> listedWord){
        for (int i = 0; i < listedWord.size(); i++){
            ArrayList<Character> newList = new ArrayList<>(listedWord);
            newList.remove(i);
            addWord(newList);
        }
    }

    public static void swapChars(ArrayList<Character> listedWord){
        for (int i = 0; i < listedWord.size() - 1; i++){
            int j = i + 1;
            ArrayList<Character> newList = new ArrayList<>(listedWord);
            newList.set(i, listedWord.get(j));
            newList.set(j, listedWord.get(i));
            addWord(newList);
        }
    }

    public static void addWord(List<Character> aList){
        StringBuilder builder = new StringBuilder(aList.size());

        for(Character ch: aList)
        {
            builder.append(ch);
        }

        String newWord = builder.toString();
        newWords.add(builder.toString());
    }
    }
