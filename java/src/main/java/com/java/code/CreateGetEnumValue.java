package com.java.code;

public class CreateGetEnumValue {

    public static void main(String[] args) {

        createGetEnumValue("merchantInfo","list");
    }

    public static void createGetEnumValue(String objectName, String listName) {

        System.out.println("for (" + firstWordToUpperCase(objectName) + " " + objectName + " : " + listName + ") {");
    }


    private static String firstWordToUpperCase(String words) {

        String firstWord = words.substring(0, 1);
        return words.replaceFirst(firstWord, firstWord.toUpperCase());
    }
}
