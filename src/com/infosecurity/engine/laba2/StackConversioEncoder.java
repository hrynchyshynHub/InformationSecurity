package com.infosecurity.engine.laba2;

import com.infosecurity.engine.laba1.Encoder;


public class StackConversioEncoder implements Encoder<String> {

    private String key;
    private char[][] matrix;

    public StackConversioEncoder(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String t) {
        int[] keys = parseKey();
        double piece = (double) t.length() / (double) keys.length;
        int p = (int) Math.ceil(piece);
        t = ensureString(t, p);
        String[] words = cutWord(t, p);
        matrix = new char[words[0].length()][keys.length];

        for (int i = 0; i < keys.length; i++) {
            fillMatrix(keys[i], words[i].toCharArray());
        }
        printMartix();
        String encrtyptedString = readMatrix();

        return encrtyptedString;
    }

    public String[] cutWord(String word, int piece) {
        String[] words = word.split("(?<=\\G.{" + piece + "})");
        return words;
    }

    private String ensureString(String word, int piece) {
        StringBuilder stringBuilder = new StringBuilder(word);
        stringBuilder.ensureCapacity(piece * key.length());

        for (int i = word.length(); i < piece * key.length(); i++) {
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

    private void fillMatrix(int column, char[] subWord) {
        column--; // key started from 1, array form 0!
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = subWord[i];
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

    private StringBuilder readColumn(int column) {
        column--; // key started from 1, array form 0!
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i][column]);
        }
        return sb;
    }

    private String readMatrix() {
        int[] keys = parseKey();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            sb.append(readColumn(keys[i]));
        }
        return sb.toString();
    }
}
