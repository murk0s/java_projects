package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class MyArrayList <T> {

    private T[] elements;
    private int size;


    public MyArrayList() {
        this.elements = (T[]) new Object[0];
    }

    public MyArrayList(MyArrayList <? extends T> myArrayList) {
        this();
        addAll(myArrayList);
    }

    public MyArrayList(Collection <? extends T> collection) {
        this();
        addAll(collection);
    }

    public void add(T object){
        this.elements = Arrays.copyOf(elements, elements.length+1);
        elements[size] = object;
        size++;
    }

    public T get (int k){
        return elements[k];
    }

    public T[] getElements () { return elements; }

    public void remove (int k) {
        if (k<size) {
            System.arraycopy(elements, k+1, elements, k, size-k-1);
            size --;
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void addAll(MyArrayList <? extends T> myArrayList) {
        elements = Arrays.copyOf(elements, elements.length+myArrayList.getElements().length);
        System.arraycopy(myArrayList.getElements(), 0, elements, size, myArrayList.getElements().length);
        size += myArrayList.getElements().length;
    }

    public void addAll(Collection <? extends T> collection) {
        Object[] objects = collection.toArray();
        elements = Arrays.copyOf(elements, elements.length+objects.length);
        System.arraycopy(objects, 0, elements, size, objects.length);
        size += objects.length;
    }

    private void sortrRec(int len){
        boolean sorted = true;
        for (int i = 0; i < len; i++) {
            if (((Comparable <T>) elements[i]).compareTo(elements[i+1]) > 0) {
                Object o = elements[i];
                elements[i] = elements[i+1];
                elements[i+1] = (T) o;
                sorted = false;
            }
        }
        if (sorted) return; //если в цикле ничего не поменяли, значит массив уже отсортирован, выходим из рекурсии

        if (len > 1) sortrRec(len-1);
    }
    public void sort() {
        sortrRec(size-1);
    }

    public static <T extends Comparable <? super T>> void sort(MyArrayList <T> myArrayList){
    //public static <T extends Object> void sort(MyArrayList <T> myArrayList, Comparator<? super T> c){
        myArrayList.sort();
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

}
