package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Records;
import backend.UpdateLogic;
import utils.ConversorBlob;
import utils.CustomButton;
import utils.UiHelper;

public class ImageScreen extends BaseFrame{
	private int id;
    public ImageScreen(int id) throws IOException {
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));
    	this.id = id;
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
                    UpdateScreen_2 updateScreen_2 = new UpdateScreen_2(id);
                    updateScreen_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                    updateScreen_2.setLocationRelativeTo(null);
                    updateScreen_2.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
  
        buttonimg1.setBounds(10, 600, 150, 150);
		   JPanel contentPane = (JPanel) getContentPane();
	       contentPane.setLayout(null);
           Records records = UpdateLogic.getRegistroPorId(id);
		
		if (records != null) {
			InputStream imageData = records.getBlob();
			if (imageData != null) {
	            // Converte os dados binários em uma imagem.
	            Image image = ConversorBlob.inputStreamToImage(imageData);

	            // Cria um JLabel para exibir a imagem centralizada.
	            JLabel imageLabel = new JLabel(new ImageIcon(image));
	            imageLabel.setBounds(200, 50, 500, 500);
	            
	            
	            getContentPane().add(imageLabel);
	            
	        }else {
	        	JPanel whiteSquare = new JPanel();
	            whiteSquare.setBackground(Color.WHITE);
	            whiteSquare.setBounds(200, 50, 500, 500);
	            whiteSquare.setLayout(null);
	            getContentPane().add(whiteSquare);
	            
	           
	            JLabel nullLabel = new JLabel("NULL");
	            nullLabel.setFont(new Font("Chiller", Font.BOLD, 70));
	            nullLabel.setForeground(Color.BLACK);  
	            nullLabel.setBounds(200, 150, 150, 150);  
	            whiteSquare.add(nullLabel);
	        }
		}
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        getContentPane().add(goBackLabel);
        getContentPane().add(buttonimg1);
	}


	
}