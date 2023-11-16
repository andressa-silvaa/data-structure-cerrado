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

import backend.DeleteLogic;
import utils.CustomButton;
import utils.UiHelper;


public class DeleteScreen extends BaseFrame {
	//define a interface do usuário (UI) para a tela de exclusão de registros. 
	//Aqui, a imagem de fundo é carregada a partir de um arquivo.
    public DeleteScreen() throws IOException {
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));
        getContentPane().setBackground(Color.WHITE);
        initializeComponents();
        
    }


    @Override
    public void initializeComponents() {
    	//Aqui são carregadas três imagens diferentes para os estados normais, 
    	//de hover e de clique de um botão personalizado.
    	ImageIcon regular = new ImageIcon("C:\\aps\\foxbuttonback1.png");
        ImageIcon hovering = new ImageIcon("C:\\aps\\foxbuttonback.png");
        ImageIcon clicking = new ImageIcon("C:\\aps\\foxbuttonback.png");

        //Um botão personalizado chamado buttonimg1 é criado usando as imagens
        CustomButton buttonimg1 = new CustomButton(regular, hovering, clicking);
        
        
        /*Um ouvinte de ação (ActionListener) é adicionado ao botão buttonimg1. 
         *Quando esse botão é clicado, ele fecha a tela atual (this) e abre a tela RecordsScreen. 
         */
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
        
        //define a posição e o tamanho do buttonimg1 na tela.
        buttonimg1.setBounds(10, 600, 150, 150);
        
        // criação de alguns componentes de texto, botao..etc
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Deletar", 350, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JButton buttonSubmit = UiHelper.createRoundButton("Apagar", 700, 600, 100, 100, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JLabel idLabel = UiHelper.createLabel("ID: ", 400, 250, 100, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);   
        JTextField id = UiHelper.createTextField(280, 300, 300, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        
        
        /*Um ouvinte de ação é adicionado ao botão buttonSubmit. 
         * Quando esse botão é clicado, ele lê o ID inserido, 
         * verifica se é válido, tenta excluir o registro com esse ID 
         * chamando o método deleteRecord da classe DeleteLogic. 
         * Em seguida, exibe mensagens de confirmação ou erro.
         */
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = id.getText();

                if (idStr.isEmpty() || !idStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "ID inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idValue = Integer.parseInt(idStr);

                if (DeleteLogic.deleteRecord(idValue)) {
                    JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
                    id.setText("");  
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o registro no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
  




        //os componentes criados  são adicionados ao painel de conteúdo da janela . 
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        getContentPane().add(titleLabel);
        getContentPane().add(goBackLabel);
        getContentPane().add(buttonimg1);
        getContentPane().add(buttonSubmit);
        getContentPane().add(idLabel);
        getContentPane().add(id);
    }
}