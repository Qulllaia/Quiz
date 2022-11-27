package com.example.quiz;

 class result {
    private static int counter = 0;
    public static void increaseCounter() {
        counter++;
    }
    public static int returnCounter(){
        return counter;
    }
    public static void toZero(){
        counter = 0;
    }
}
