package com.java.datastructures;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("yupp");
        BasicLinkedList<String> Sample = new BasicLinkedList<String>();
        System.out.println(Sample.size());
        Sample.add("one");
        Sample.add("two");
        Sample.add("three");
        Sample.add("four");
//        Sample.insert("first", 1);
//        System.out.println(Sample.remove());
//        System.out.println(Sample.remove());
//        System.out.println(Sample.remove());
        System.out.println(Sample.find("two"));
        System.out.println(Sample.toString());
    }
}
