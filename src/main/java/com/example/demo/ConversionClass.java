package com.example.demo;

public class ConversionClass {


    //Base 62 alphabet in a String
    private static String allAllowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private char[] allowedChars = allAllowedString.toCharArray();

    private int baseUrlLength = allowedChars.length;

    /*
    This method takes in a number variable and its return value is the decoded link.
     */
    public String encodeUrlMethod(long input){
        var encoded = new StringBuilder();
        if(input == 0) {
            return String.valueOf(allowedChars[0]);
        }
        while (input > 0) {
            encoded.append(allowedChars[(int) (input % baseUrlLength)]);
            input = input / baseUrlLength;
        }

        return encoded.reverse().toString();
    }

    /*
  This method takes in a string and returns a number (decoded)
   */
    public long decodeUrlMethod(String input) {
        var allChars = input.toCharArray();
        var length = allChars.length;
        var counter = 1;
        var decoded = 0;
        for (int i = 0; i < length; i++) {
            decoded += allAllowedString.indexOf(allChars[i]) * Math.pow(baseUrlLength, length - counter);
            counter++;
        }
        return decoded;
    }
}
