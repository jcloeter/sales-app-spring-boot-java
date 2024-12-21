package com.example.vanillajava.service;

import java.util.ArrayList;
import java.util.List;

public class ListService extends AbstractCollection {

    @Override
    public void run(){
        System.out.println(this.getClass().getCanonicalName());
    }

    private void arraySandbox() {
        List<Integer> muhFirstList = new ArrayList<Integer>();

        for (Integer x = 0; x < 3; x++) {
            muhFirstList.add(x);
            System.out.print(muhFirstList);
        }

        if (muhFirstList.size() > 19) {
            System.err.println(muhFirstList.get(19));
        }


        if (muhFirstList.contains(4)) {
            System.err.println("got eem");
        }

        for (int i = 0; muhFirstList.size() > i; i++){
            System.out.println("Repetition " + i + " Value " + muhFirstList.get(i));
        }
    }
}
