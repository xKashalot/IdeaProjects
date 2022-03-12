package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>();
        int count = 0;
        while (count < 20) {

            array.add("Privet");
            count++;
        }
        array.remove(5);
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
    }

    public static class DynamicArray<T> {
        private Object[] arr = {};
        private Integer size = null;

        public DynamicArray() {
            arr = new Object[5];
            size = 5;
        }

        public void add(T el) {
            if (size == arr.length) {
                arr = Arrays.copyOf(arr, arr.length + 1);
            }
            arr[arr.length - 1] = el;
            size++;
        }

        public void remove(int index) {
            System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        }

        public T get(int index) {
            while (size == null) {
                throw  new ArrayIndexOutOfBoundsException();
            }
            return (T) arr[index];
        }

        public int size(){
            return arr.length;
        }

    }
}
