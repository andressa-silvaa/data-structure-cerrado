package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import backend.UpdateLogic;
import utils.CustomButton;
import utils.UiHelper;


public class UpdateScreen_1 extends BaseFrame {
    public UpdateScreen_1() throws IOException {
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));
        getContentPane().setBackground(Color.WHITE);
        initializeComponents();
        
        
    }


    @Override
    public void initializeComponents() {
    	ImageIcon regular = new ImageIcon("C:\\aps\\foxbuttonback1.png");
        ImageIcon hovering = new ImageIcon("C:\\aps\\foxbuttonback.png");
        ImageIcon clicking = new ImageIcon("C:\\aps\\foxbuttonback.png");

       
        CustomButton buttonimg1 = new CustomButton(regular, hovering, clicking);

        buttonimg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    RecordsScreen recordsScreen = new RecordsScreen();
                    recordsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                    recordsScreen.setLocationRelativeTo(null);
                    recordsScreen.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    
        
  
        buttonimg1.setBounds(10, 600, 150, 150);
        
      
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Update", 350, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JButton buttonSubmit = UiHelper.createRoundButton("Pesquisar", 700, 600, 150, 100, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JLabel idLabel = UiHelper.createLabel("ID: ", 400, 250, 100, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);   
        JTextField idTextField = UiHelper.createTextField(280, 300, 300, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        
        
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText();
                if (!idText.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText);
                        if (UpdateLogic.validateId(id)) {
                            try {
                                UpdateScreen_2 updateScreen_2 = new UpdateScreen_2(id);
                                updateScreen_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                updateScreen_2.setLocationRelativeTo(null);
                                updateScreen_2.setVisible(true);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "ID inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID deve ser um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Campo de ID vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        getContentPane().add(titleLabel);
        getContentPane().add(goBackLabel);
        getContentPane().add(buttonimg1);
        getContentPane().add(buttonSubmit);
        getContentPane().add(idLabel);
        getContentPane().add(idTextField);
    }
}