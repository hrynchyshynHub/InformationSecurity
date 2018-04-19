package com.infosecurity.engine.laba3;

import com.infosecurity.engine.common.Decoder;

import java.nio.charset.Charset;
import java.util.*;

public class CesarDecoder implements Decoder<String>{


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
        int key = Character.getNumericValue(mostPopular) - Character.getNumericValue(e);

        // decode string using key
        String decodeString = decode(key, s);

        return decodeString;
    }

    private List<Character> toCharsList(String s){
        List<Character> characters = new ArrayList<>();
        for(Character c : s.toCharArray()){
            if(Character.isAlphabetic(c)){
                characters.add(c);
            }
        }
        return characters;
    }

    private Map<Character, Integer> countLetter(List<Character> characters){
        Set<Character> set = new LinkedHashSet<>(characters);
        set.remove(' ');
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
        StringBuilder sb = new StringBuilder();
        for(Character currentCharacter : s.toCharArray()){
            if(Character.isAlphabetic(currentCharacter)){
                System.out.print(currentCharacter + " = current char(" + Character.getNumericValue(currentCharacter) + ")  -->  ");
                Character newCharacter = null;

                int outOfAlphabeticOffset = Character.getNumericValue(currentCharacter) - key;

                int offset = Character.getNumericValue(currentCharacter) - 10;

                if(outOfAlphabeticOffset < 10){
                    int modifyKey = key - offset;
                    newCharacter = Character.forDigit(36 - modifyKey, 36);
                }else{
                    newCharacter = Character.forDigit(Character.getNumericValue(currentCharacter) - key, 36);
                }
                System.out.println(newCharacter + " = new char(" + Character.getNumericValue(newCharacter) + ")");
                sb.append(newCharacter);
            }else{
                sb.append(currentCharacter);
            }
        }
        return sb.toString();
    }

}
