package br.com.sfchatter.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tiago_de_Oliveira on 3/31/2014.
 */
public class StringUtils {
    public static Set<String> getHashTagsOnText(String text){
        Set<String> result = new HashSet<String>();
        String[] words = text.split(" ");
        for (String word : words) {
            if (word.startsWith("#")) {
                result.add(word);
            }
        }
        return result;
    }
}
