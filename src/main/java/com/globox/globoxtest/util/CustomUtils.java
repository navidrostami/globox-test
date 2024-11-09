package com.globox.globoxtest.util;

public class CustomUtils {

     public static String parseList(String listStr) {
        if (listStr != null && !listStr.equals("\\N")) {
            return listStr;
        }
        return null;
    }

    public static Integer parseInt(String intStr) {
        if (intStr != null && !intStr.equals("\\N")) {
            try {
                return Integer.parseInt(intStr);
            } catch (NumberFormatException e) {
                // Log the error and continue
                System.err.println("Invalid int format: " + intStr);
            }
        }
        return null;
    }

    public static Double parseDouble(String doubleStr) {
        if (doubleStr != null && !doubleStr.equals("\\N")) {
            try {
                return Double.parseDouble(doubleStr);
            } catch (NumberFormatException e) {
                // Log the error and continue
                System.err.println("Invalid double format: " + doubleStr);
            }
        }
        return null;
    }
}
