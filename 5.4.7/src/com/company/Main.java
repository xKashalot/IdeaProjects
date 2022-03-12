package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
}

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeInt(3);
        objectOutputStream.writeObject(new Animal("Fox"));
        objectOutputStream.writeObject(new Animal("Wolf"));
        objectOutputStream.writeObject(new Animal("Dog"));
        objectOutputStream.close();
        System.out.println(Arrays.toString(deserializeAnimalArray(byteArrayOutputStream.toByteArray())));
    }

    public static Animal[] deserializeAnimalArray(byte[] data) throws IOException, ClassNotFoundException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            int num = ois.readInt();
            Animal[] animals = new Animal[num];
            for (int i = 0; i < num; ++i) {
                animals[i] = (Animal) ois.readObject();
            }
            return animals;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }




}
