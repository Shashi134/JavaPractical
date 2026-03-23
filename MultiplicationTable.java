package com.java_exp6;

import java.util.Scanner;
public class MultiplicationTable {

	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

          System.out.print("Enter a number: ");
          int num = sc.nextInt();

        // Multiplication Table
        System.out.println("\nMultiplication Table of " + num + ":");
        for(int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }

        // Square
        int square = num * num;
        System.out.println("\nSquare of " + num + " is: " + square);

        sc.close();
        }
       }

