package com.example.myfirstapp;

import java.util.ArrayList;

public class DataHolder {

    // variables
    private static ArrayList<ArrayList<String>> doubleList = new ArrayList<ArrayList<String>>();
    private static String name = "";

    // accessors
    public static ArrayList<ArrayList<String>> getList() {
        return doubleList;
    }
    public static ArrayList<String> getSingle(int i){
        return doubleList.get(i);
    }
    public static String getListName(int i, int j){
        return doubleList.get(i).get(j);
    }
    public static int getLength(){
        return doubleList.size();
    }
    public static String getName() {
        return name;
    }

    // mutators
    public static void addList(ArrayList<String> list) {
        doubleList.add(list);
    }
    public static void removeList(ArrayList<String> list){
        doubleList.remove(list);
    }
    public static void setName(String newName){
        name = newName;
    }
}
