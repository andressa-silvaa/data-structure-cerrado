package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import utils.CustomButton;
import utils.UiHelper;

public class HomeScreen extends BaseFrame {
	
    public HomeScreen() throws IOException {
    	
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));

        
        initializeComponents();
     

    }

    @Override
    public void initializeComponents() {
    	
    	//três imagens diferentes são carregadas para definir os estados normais, 
    	//de hover e de clique de um botão personalizado.
    	final ImageIcon regular = new ImageIcon("C:\\aps\\foxbutton1.png");
        final ImageIcon hovering = new ImageIcon("C:\\aps\\foxbutton.png");
        final ImageIcon clicking = new ImageIcon("C:\\aps\\foxbutton.png");

        //Um botão personalizado chamado customButton é criado e posicionado na tela.
        CustomButton customButton = new CustomButton(regular, hovering, clicking);
        customButton.setBounds(700, 600, 150, 150);

        //Um ouvinte de ação é adicionado ao customButton. 
        //Quando o botão é clicado, ele fecha a tela atual, 
        //e abre a tela RecordsScreen.
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    	
            	try {
            
            		RecordsScreen recordsScreen = new RecordsScreen ();
            		recordsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            		recordsScreen.setLocationRelativeTo(null);
            		recordsScreen.setVisible(true);
            		
            	    
           	    
            	} catch (IOException ex) {
            	    ex.printStackTrace();
            	}
            	dispose();
 
            }
        });






        //Um componente de área de texto chamado textArea é criado.
        JTextArea textArea = UiHelper.createTextArea(10, 200, 800, 300,new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        
        //Um documento de estilo padrão é definido para o textArea.
        DefaultStyledDocument doc = new DefaultStyledDocument();
        textArea.setDocument(doc);
        
        //O texto no textArea é alinhado à esquerda.
        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), leftAlign, false);
        
        
        //Texto é definido no textArea.
        textArea.setText("O Cerrado, "
                + "bioma brasileiro importante, "
                + "enfrenta problemas ambientais graves, "
                + "como desmatamento ilegal, "
                + "queimadas e perda de habitat de espécies ameaçadas."
                + " Sua preservação é crucial para o equilíbrio ambiental"
                + " e proteção da biodiversidade.");

        //Um painel de conteúdo é obtido a partir do conteúdo da janela 
        //e seu layout é definido como nulo, 
        //permitindo a colocação manual de componentes na tela.
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
  
        //Dois rótulos (titleLabel e nextLabel) são criados 
        JLabel titleLabel = UiHelper.createLabel("Cerrado e Crime Ambiental", 80, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JLabel nextLabel = UiHelper.createLabel("Avançar", 730, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        
        //Os componentes criados são adicionados ao painel de conteúdo.
        contentPane.add(customButton);
        contentPane.add(titleLabel);
        contentPane.add(nextLabel);
        contentPane.add(textArea);
    }


}