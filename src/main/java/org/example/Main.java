package org.example;

public class Main{

    public static void main(String[] args) {
        View view = new View();
        Presenter presenter = new Presenter(new MyCalculator(), view);

        presenter.runGUI();

    }
}