package main;


import java.io.IOException;
import javax.swing.JFrame;
import ui.HomeScreen; 

public class Main {
    public static void main(String[] args) throws IOException {
        
        HomeScreen frame = new HomeScreen();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}