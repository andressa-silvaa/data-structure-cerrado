package utils;



import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UiHelper {

	//cria um label a partir das definições dos parâmetros 
    public static JLabel createLabel(String text, int x, int y, int width, int height, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

  //cria um Jbutto do tipo RoundButton a partir das definições dos parâmetros 
    public static JButton createRoundButton(String text, int x, int y, int width, int height, Font font, Color color) {
        RoundButton button = new RoundButton(text);
        button.setFont(font);
        button.setForeground(color);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBounds(x, y, width, height);
        return button;
    }
    
    //cria um Jtextarea a partir das definições dos parâmetros 
    public static JTextArea createTextArea(int x, int y, int width, int height, Font font, Color color) {
        JTextArea textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setForeground(color);
        textArea.setBounds(x, y, width, height);
        textArea.setOpaque(false);
        //permite a quebra de linha no texto
        textArea.setLineWrap(true);
        //define que a quebra de linha ocorre apenas entre palavras
        textArea.setWrapStyleWord(true);
        // define a área de texto como somente leitura
        textArea.setEditable(false);
        return textArea;
        
    }
    //cria um Jtextfield a partir das definições dos parâmetros 
    public static JTextField createTextField(int x, int y, int width, int height, Font font, Color color) {
        JTextField textField = new JTextField();
        textField.setFont(font);
        textField.setForeground(color);
        textField.setBounds(x, y, width, height);
        return textField;
    }
    public static JComboBox<String> createComboBox(String[] items, int x, int y, int width, int height, Font font, Color color) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(font);
        comboBox.setForeground(color);
        comboBox.setBounds(x, y, width, height);
        return comboBox;
    }

}