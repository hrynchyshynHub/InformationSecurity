package com.infosecurity.engine.laba2;

import com.infosecurity.engine.common.Encoder;


public class StackConversioEncoder implements Encoder<String> {

    private String key;
    private char[][] matrix;

    public StackConversioEncoder(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String inputString) {
        int[] keys = parseKey();
        double columns = (double) inputString.length() / (double) keys.length;
        int realColumns = (int) Math.ceil(columns);
        inputString = ensureString(inputString, realColumns);
        String[] words = cutWord(inputString, keys.length);
        matrix = new char[realColumns][words[0].length()];

        for (int i = 0; i < words.length; i++) {
            fillMatrix(i, words[i].toCharArray());
        }
        printMartix();
        String encrtyptedString = readMatrix();

        return encrtyptedString;
    }

    public String[] cutWord(String word, int piece) {
        String[] words = word.split("(?<=\\G.{" + piece + "})");
        return words;
    }

    private String ensureString(String word, int col) {
        StringBuilder stringBuilder = new StringBuilder(word);
        for (int i = word.length(); i < col * key.length() ; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private int[] parseKey() {
        char[] key = this.key.toCharArray();
        int[] keySet = new int[key.length];
        for (int i = 0; i < key.length; i++) {
            keySet[i] = Character.getNumericValue(key[i]);
        }
        return keySet;
    }

    private void fillMatrix(int row, char[] subWord) {
        for (int i = 0; i < subWord.length; i++) {
            matrix[row][i] = subWord[i];
        }
    }

    private void printMartix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMatrix(){
        return matrix;
    }

    private StringBuilder readColumn(int column) {
        column--; // key started from 1, array form 0!
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i][column]);
        }
        return sb;
    }

    public String readMatrix() {
        int[] keys = parseKey();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            sb.append(readColumn(keys[i]));
        }
        return sb.toString();
    }
}
