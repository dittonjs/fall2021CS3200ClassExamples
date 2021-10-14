package com.usu.recyclerview;

import java.util.ArrayList;

public class FakeDatabase {
    public ArrayList<String> getData(int amount) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < amount; i ++) {
            data.add("I am some data " + i);
        }

        return data;
    }
}
