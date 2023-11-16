package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.InsertionLogic;
import dataBase.Db;
import utils.CustomButton;
import utils.UiHelper;


public class InsertionScreen extends BaseFrame {
	//o construtor InsertionScreen é criado. 
	//Ele recebe uma imagem como plano de fundo, 
	//define o plano de fundo do painel principal como branco 
	//e chama o método initializeComponents() 
	//para configurar os componentes da tela de inserção.
    public InsertionScreen() throws IOException {
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));
        getContentPane().setBackground(Color.WHITE);
        initializeComponents();
          
    }

    @Override
    public void initializeComponents() {
    	//3 imagens para os botoes sao definidas de acordo com o estado do mouse
    	ImageIcon regular = new ImageIcon("C:\\aps\\foxbuttonback1.png");
        ImageIcon hovering = new ImageIcon("C:\\aps\\foxbuttonback.png");
        ImageIcon clicking = new ImageIcon("C:\\aps\\foxbuttonback.png");

        //um botao imagem é criado
        CustomButton buttonimg1 = new CustomButton(regular, hovering, clicking);
        
        //é definido uma ação a esse botão, quando clicado ele dispensa a tela atual,
        //e abre uma nova tela do tipo RecordsScreen
        buttonimg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	try{
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
    




        
        
        //é definido a posição do botão
        buttonimg1.setBounds(10, 600, 150, 150);
        
        //componentes da tela(rótulos,botões)....
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Insertion", 320, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JLabel fidLabel = UiHelper.createLabel("FID: *", 50, 180, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel stateLabel = UiHelper.createLabel("STATE: *", 50, 230, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel pathRowLabel = UiHelper.createLabel("PATH ROW: *", 50, 280, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel mainClassLabel = UiHelper.createLabel("MAIN CLASS: *", 50, 330, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel nameCLassLabel = UiHelper.createLabel("NAME CLASS: *", 50, 380, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel julianDayLabel = UiHelper.createLabel("JULIAN DAY: *", 50, 430, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel imageDateLabel = UiHelper.createLabel("IMAGE DATE: *", 450, 180, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel yearLabel = UiHelper.createLabel("YEAR: *", 450, 230, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel areaKmLabel = UiHelper.createLabel("AREA KM: *", 450, 280, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel sateliteLabel = UiHelper.createLabel("SATELITE: *", 450, 330, 150, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel sensorLabel = UiHelper.createLabel("SENSOR: *", 450, 380, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel uuidLabel = UiHelper.createLabel("UUID: *", 450, 430, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JLabel imagemLabel = UiHelper.createLabel("IMAGEM: *", 450, 480, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JButton buttonSave = UiHelper.createRoundButton("Salvar", 700, 600, 100, 100, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        

        
        //criação de textfields
        JTextField fid = UiHelper.createTextField(190, 180, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        JTextField state = UiHelper.createTextField(190, 230, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        JTextField imageDate = UiHelper.createTextField(600, 180, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        JTextField year = UiHelper.createTextField(600, 230, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        JTextField areaKm = UiHelper.createTextField(600, 280, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        JTextField uuid = UiHelper.createTextField(600, 430, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        JTextField imagem = UiHelper.createTextField(600, 480, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
       
        //definição das opções de escolha para os elementos ComboBox
        String[] pathRowOptions = {"222/70"};
        String[] mainClassOptions = {"desmatamento"};
        String[] nameClassOptions = {"d2002"};
        String[] sateliteOptions = {"Landsat8"};
        String[] sensorOptions = {"OLI"};
        String[] julianDayOptions = {"0"};
        
        //criação dos elementos tipo ComboBox
        JComboBox<String> pathRow = UiHelper.createComboBox(pathRowOptions, 190, 280, 200, 30, new Font("Chiller", Font.BOLD, 26),Color.BLACK);
        JComboBox<String> mainClass = UiHelper.createComboBox(mainClassOptions, 190, 330, 200, 30, new Font("Chiller", Font.BOLD, 26),Color.BLACK);
        JComboBox<String> nameClass = UiHelper.createComboBox(nameClassOptions, 190, 380, 200, 30, new Font("Chiller", Font.BOLD, 26),Color.BLACK);
        JComboBox<String> satelite = UiHelper.createComboBox(sateliteOptions, 600, 330, 200, 30, new Font("Chiller", Font.BOLD, 26),Color.BLACK);
        JComboBox<String> sensor = UiHelper.createComboBox(sensorOptions, 600, 380, 200, 30, new Font("Chiller", Font.BOLD, 26),Color.BLACK);
        JComboBox<String> julianDay = UiHelper.createComboBox(julianDayOptions, 190, 430, 200, 30, new Font("Chiller", Font.BOLD, 26),Color.BLACK);
        
        //evento para o botão de salvar, quando clicado, é feita uma inserção 
        //com os métodos de outras classes
        buttonSave.addActionListener(e -> {

        	InsertionLogic insertionLogic = new InsertionLogic();
        	boolean inseridoComSucesso = insertionLogic.insertRecord(
                fid, state, pathRow, mainClass, nameClass, julianDay,
                imageDate, year, areaKm, satelite, sensor, uuid,imagem);
            
            //caso a inserção ocorra, os campos de textos são resetados
            if (inseridoComSucesso) {
            	fid.setText("");
                state.setText("");
                pathRow.setSelectedIndex(0);
                mainClass.setSelectedIndex(0);
                nameClass.setSelectedIndex(0);
                julianDay.setSelectedIndex(0);
                imageDate.setText("");
                year.setText("");
                areaKm.setText("");
                satelite.setSelectedIndex(0);
                sensor.setSelectedIndex(0);
                uuid.setText("");
                imagem.setText("");
            }
        });
        
        //remoção de layout, para posicionar elementos manualmente 
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        //adicionando os elementos a janela.
        getContentPane().add(titleLabel);
        getContentPane().add(goBackLabel);
        getContentPane().add(buttonimg1);
        getContentPane().add(fid);
        getContentPane().add(state);
        getContentPane().add(pathRow);
        getContentPane().add(mainClass);
        getContentPane().add(nameClass);
        getContentPane().add(julianDay);
        getContentPane().add(imageDate);
        getContentPane().add(year);
        getContentPane().add(areaKm);
        getContentPane().add(satelite);
        getContentPane().add(sensor);
        getContentPane().add(uuid);
        getContentPane().add(fidLabel);
        getContentPane().add(stateLabel);
        getContentPane().add(pathRowLabel);
        getContentPane().add(mainClassLabel);
        getContentPane().add(nameCLassLabel);
        getContentPane().add(julianDayLabel);
        getContentPane().add(imageDateLabel);
        getContentPane().add(yearLabel);
        getContentPane().add(areaKmLabel);
        getContentPane().add(sateliteLabel);
        getContentPane().add(sensorLabel);
        getContentPane().add(uuidLabel);
        getContentPane().add(buttonSave);
        getContentPane().add(imagemLabel);
        getContentPane().add(imagem);
    }
}