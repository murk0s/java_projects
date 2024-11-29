package org.example;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List <Integer> x = new ArrayList<>();
        x.add(6);
        x.add(5);
        x.add(7);
        MyArrayList <Integer> arrInt = new MyArrayList<>();

        arrInt.add(3);
        arrInt.add(2);
        arrInt.add(1);
        arrInt.add(4);
        arrInt.addAll(x);
        MyArrayList <Integer> arrInt2 = new MyArrayList<>(arrInt);
        arrInt2.addAll(arrInt);
        System.out.println(arrInt2);
        MyArrayList.sort(arrInt2);


        System.out.println(arrInt2);

    }

    public static <T> T someMethod (ArrayList<T> arrayList)  {

    }
}