/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.refatoringexamples.extractmethod;

/**
 *
 * @author patricia.macedo
 */
public class ExtractMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String titulo1 = "Duplicate Code";
        String titulo2 = "Extract Method";

        printTitle(4, '*', titulo1);
        printTitle(3, '-', titulo2);
    }

    private static void printTitle(int top_margin, char char_bar, String title) {
        printBlankLines(top_margin);
        printCharLine(title.length(), char_bar);
        System.out.println("\n" + title);
    }

    private static void printCharLine(int length, char c) {
        for (int i = 0; i < length; i++) {
            System.out.print(c);
        }
    }

    private static void printBlankLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }
}
