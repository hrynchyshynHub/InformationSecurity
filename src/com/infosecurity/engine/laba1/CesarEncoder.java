package com.infosecurity.engine.laba1;

import com.infosecurity.engine.common.Encoder;

public class CesarEncoder implements Encoder<String> {

    private static final String ukrAlphabetLower = "абвгґдеєжзиіїйклмнопрстуфхцшщьюя";
    private static final String ukrAlphabetUpper = "АБВГҐДЕЄЖЗИШЇЙКЛМНОПРСТУФХЦШЩЬЮЯ";
    private Integer key;

    public CesarEncoder(Integer key) {
        this.key = key;
    }

    @Override
    public String encrypt(String t) {
        char[] charsArray = t.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < t.length(); i++){
           int index = ukrAlphabetLower.indexOf(charsArray[i]);
           if(index != -1){
               sb.append(checkInArray(index, ukrAlphabetLower));
           }else{
              index = ukrAlphabetUpper.indexOf(charsArray[i]);
              if( index != -1){
                  sb.append(checkInArray(index, ukrAlphabetUpper));
              }else {
                  sb.append(charsArray[i]);
              }
           }
        }
        return sb.toString();
    }

    private char checkInArray(int index, String array){
        index = (index + key) % array.length();
        char encrtyptChar = array.charAt(index);
        return encrtyptChar;
    }

}
