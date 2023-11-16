package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dataBase.Db;
import utils.CustomButton;
import utils.UiHelper;


//declaração da classe PerformanceHeapSort, que estende BaseFrame e 
//implementa a interface AutoCloseable.
public class RecordsScreen extends BaseFrame implements AutoCloseable {
	// variáveis de instância da classe.
    private JTable table;
    private Connection connection;
    private static final String BACKGROUND_IMAGE_PATH = "C:\\aps\\fox.jpeg.jpg";

   
    //construtor da classe . Ele configura a imagem de fundo
    public RecordsScreen() throws IOException {
    	super(ImageIO.read(new File(BACKGROUND_IMAGE_PATH)));
        initializeComponents();
        loadDataFromDatabase();
    }
    //Este método é responsável por carregar dados do banco de dados, 
    //usando uma conexão com a tabela "cerrado". 
    private void loadDataFromDatabase() {
        this.connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            if (connection == null || connection.isClosed()) {
                connection = Db.getConnection();
            }
            // Cria uma instrução SQL para consultar o banco de dados
            statement = connection.createStatement();
            // Executa a consulta SQL para selecionar todos os registros da tabela "cerrado"
            resultSet = statement.executeQuery("SELECT * FROM cerrado");
            // Obtém o modelo da tabela onde os dados serão exibidos
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Itera pelos resultados da consulta e adiciona cada registro como uma nova linha na tabela
            while (resultSet.next()) {
                model.addRow(new Object[] { resultSet.getString("id"),resultSet.getString("fid"), resultSet.getString("state"), resultSet.getString("path_row"), resultSet.getString("main_class"), resultSet.getString("class_name"), resultSet.getString("def_cloud"), resultSet.getString("julian_day"), resultSet.getString("image_date"), resultSet.getString("year"), resultSet.getString("area_km"), resultSet.getString("scene_id"), resultSet.getString("source"), resultSet.getString("satellite"), resultSet.getString("sensor"), resultSet.getString("uuid") });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Db.closeResultSet(resultSet);
            Db.closeStatement(statement);
          
        }
    }

	@Override
	public void initializeComponents() {
		final ImageIcon regular = new ImageIcon("C:\\aps\\foxbutton1.png");
        final ImageIcon hovering = new ImageIcon("C:\\aps\\foxbutton.png");
        final ImageIcon clicking = new ImageIcon("C:\\aps\\foxbutton.png");

        final ImageIcon regular2 = new ImageIcon("C:\\aps\\foxbuttonback1.png");
        final ImageIcon hovering2 = new ImageIcon("C:\\aps\\foxbuttonback.png");
        final ImageIcon clicking2 = new ImageIcon("C:\\aps\\foxbuttonback.png");
        //cria 2 botao imagem e posiciona ele na tela
        CustomButton buttonimg2 = new CustomButton(regular, hovering, clicking);
        CustomButton buttonimg1 = new CustomButton(regular2, hovering2, clicking2);
        //define um evento para o botão,
        //quando clicado, a tela HomeScreen é aberta
        buttonimg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    HomeScreen homeScreen = new HomeScreen();
                    homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    homeScreen.setLocationRelativeTo(null);
                    homeScreen.setVisible(true);  
    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
                
            }
        });
        //define um evento para o botão,
        //quando clicado, a tela Ordering é aberta
        buttonimg2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                try {
                	Ordering ordering = new Ordering();
                	ordering.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                	ordering.setLocationRelativeTo(null);
                	ordering.setVisible(true);
            	    dispose();
            	} catch (IOException ex) {
            	    ex.printStackTrace();
            	}
            }
        });



        //posiciona os botoes
        buttonimg2.setBounds(700, 600, 150, 150);
        buttonimg1.setBounds(10, 600, 150, 150);
        
        //cria botoes tipo RoundButton
        JButton button1 = UiHelper.createRoundButton("INSERIR", 100, 150, 150, 60, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JButton button2 = UiHelper.createRoundButton("ATUALIZAR", 340, 150, 180, 60, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JButton button3 = UiHelper.createRoundButton("DELETAR", 600, 150, 150, 60, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        //define um evento para o botão,
        //quando clicado, a tela InsertionScreen é aberta
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    
                try {
                	InsertionScreen insertion = new InsertionScreen();
                	insertion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                	insertion.setLocationRelativeTo(null);
                	insertion.setVisible(true);
            	    dispose();
            	} catch (IOException ex) {
            	    ex.printStackTrace();
            	}
            }
        });
        //define um evento para o botão,
        //quando clicado, a tela UpdateScreen_1 é aberta
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {	
 
                try {
                	UpdateScreen_1 updateScreen_1 = new UpdateScreen_1();
                	updateScreen_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                	updateScreen_1.setLocationRelativeTo(null);
                	updateScreen_1.setVisible(true);
            	    dispose();
            	} catch (IOException ex) {
            	    ex.printStackTrace();
            	}
            }
        });
        //define um evento para o botão,
        //quando clicado, a tela DeleteScreen é aberta
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                	DeleteScreen deleteScreen = new DeleteScreen();
                	deleteScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                	deleteScreen.setLocationRelativeTo(null);
                	deleteScreen.setVisible(true);
            	    dispose();
            	} catch (IOException ex) {
            	    ex.printStackTrace();
            	}
            }
        });
        //criação de rótulos
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Records", 340, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JLabel nextLabel = UiHelper.createLabel("Avançar", 730, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        //remoção de layout
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        //adição de elementos para a tela
        getContentPane().add(titleLabel);
        getContentPane().add(button1);
        getContentPane().add(button2);
        getContentPane().add(button3);
        getContentPane().add(goBackLabel);
        getContentPane().add(nextLabel);
        getContentPane().add(buttonimg1);
        getContentPane().add(buttonimg2);
        
        //define as colunas da tabela
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] { "id","fid", "state", "path_row", "main_class", "class_name", "def_cloud", "julian_day", "image_date", "year", "area_km", "scene_id", "source", "satellite", "sensor", "uuid" });
        //cria a variável table para conter o padrão de tabela
        table = new JTable(model);
        //configura o comportamento de redimensionamento automático das colunas da tabela 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        
        //cria um painel de rolagem 
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //define a posição
        scrollPane.setBounds(130, 250, 600, 300);
        //adiciona o elemento para a tela
        getContentPane().add(scrollPane);
        
		
	}

	@Override
	public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
		
	}

	
 

}