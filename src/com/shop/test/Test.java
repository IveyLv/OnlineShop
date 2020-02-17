package com.shop.test;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> list = GetLeastNumbers_Solution(input, 4);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length) {
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] < input[j + 1]) {
                        int temp = input[j];
                        input[j] = input[j + 1];
                        input[j + 1] = temp;
                    }
                    if (j == input.length - i - 1) {
                        list.add(input[j]);
                    }
                }
            }
        } else {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k - i - 1; j++) {
                    if (input[j] < input[j + 1]) {
                        int temp = input[j];
                        input[j] = input[j + 1];
                        input[j + 1] = temp;
                    }
                    if (j == k - i - 1) {
                        list.add(input[j]);
                    }
                }
            }
        }
        return list;
    }

}
