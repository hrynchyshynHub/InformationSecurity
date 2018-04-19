package com.infosecurity.engine.laba3;

import com.infosecurity.engine.common.Decoder;
import java.util.*;

public class CesarDecoder implements Decoder<String>{

    private static final String englishAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String englishAlphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String decode(String s) {
        Character e = 'e';
        // Get list of chars
        List<Character> characters = toCharsList(s);

        // count all letters
        Map<Character, Integer> map = countLetter(characters);

        // max count define 'e'
        Character mostPopular = getMaxCountLetter(map);

        // calculate key
        int key = mostPopular.compareTo(e);

        // decode string using key
        String decodeString = decode(key, s);

        return decodeString;
    }

    private List<Character> toCharsList(String s){
        List<Character> characters = new ArrayList<>();
        for(Character c : s.toCharArray()){
            characters.add(c);
        }
        return characters;
    }

    private Map<Character, Integer> countLetter(List<Character> characters){
        Set<Character> set = new LinkedHashSet<>(characters);
        Map<Character, Integer> map = new LinkedHashMap<>();
        set.stream().forEach( c -> {
            Integer sum = Math.toIntExact(characters.stream().filter(x -> x == c).count());
            map.put(c, sum);
        });
        return map;
    }

    private Character getMaxCountLetter(Map<Character, Integer> map) {
       return map.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get()
                .getKey();
    }

    private String decode(int key, String s){
        char[] charsArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length(); i++){
            int index = englishAlphabet.indexOf(charsArray[i]);
            if(index != -1){
                sb.append(checkInArray(index, englishAlphabet, key));
            }else{
                index = englishAlphabetUpper.indexOf(charsArray[i]);
                if( index != -1){
                    sb.append(checkInArray(index, englishAlphabetUpper, key));
                }else {
                    sb.append(charsArray[i]);
                }
            }
        }
        return sb.toString();
    }

    private char checkInArray(int index, String array , int key){
        index = (index - key) % array.length();
        char encrtyptChar = array.charAt(index);
        return encrtyptChar;
    }
}
