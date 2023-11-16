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
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import algorithm.InsertionSort;
import dataBase.Db;
import utils.CustomButton;
import utils.UiHelper;

//declaração da classe PerformanceHeapSort, que estende BaseFrame e 
//implementa a interface AutoCloseable.
public class PerformanceInsertionSort extends BaseFrame implements AutoCloseable {
	// variáveis de instância da classe. 
    private JTable table;
    private Connection connection;
    private long executionTime;
    JTextArea textArea;
   
    //construtor da classe . Ele configura a imagem de fundo
    public PerformanceInsertionSort() throws IOException {
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));
        getContentPane().setBackground(Color.WHITE);
        initializeComponents();
        loadDataFromDatabase();
        
    }
    
//Este método é responsável por carregar dados do banco de dados, 
//usando uma conexão com a tabela "cerrado".
public void loadDataFromDatabase() {
    connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        if (connection == null || connection.isClosed()) {
            connection = Db.getConnection();
        }

        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM cerrado");
        //Inicializa uma lista para armazenar os dados do banco de dados.
        List<String[]> data = new ArrayList<>();
        // Itera sobre as linhas do conjunto de resultados.
        while (resultSet.next()) {
        	//Cria um array de strings com os valores da linha atual.
            String[] rowData = new String[] {
                resultSet.getString("id"),
                resultSet.getString("fid"),
                resultSet.getString("state"),
                resultSet.getString("path_row"),
                resultSet.getString("main_class"),
                resultSet.getString("class_name"),
                resultSet.getString("def_cloud"),
                resultSet.getString("julian_day"),
                resultSet.getString("image_date"),
                resultSet.getString("year"),
                resultSet.getString("area_km"),
                resultSet.getString("scene_id"),
                resultSet.getString("source"),
                resultSet.getString("satellite"),
                resultSet.getString("sensor"),
                resultSet.getString("uuid")
            };
            // Adiciona o array de strings à lista de dados.
            data.add(rowData);
        }
        // Registra o tempo de início da ordenação.
        long startTime = System.currentTimeMillis();


        InsertionSort insertionSort = new InsertionSort();
        // Ordena os dados usando o algoritmo merge Sort.
        insertionSort.insertionSort(data);

        // Registra o tempo de término da ordenação.
        long endTime = System.currentTimeMillis();

        // Calcula o tempo de execução da ordenação.
        this.executionTime = endTime - startTime;
        // Atualiza a área de texto com o tempo de execução.
        updateTextArea();

        // Obtém o modelo da tabela.
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Itera sobre os dados ordenados.
        for (String[] rowData : data) {
            model.addRow(rowData);
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
		ImageIcon regular = new ImageIcon("C:\\aps\\foxbuttonback1.png");
        ImageIcon hovering = new ImageIcon("C:\\aps\\foxbuttonback.png");
        ImageIcon clicking = new ImageIcon("C:\\aps\\foxbuttonback.png");

        //cria um botao imagem e posiciona ele na tela
        CustomButton buttonimg1 = new CustomButton(regular, hovering, clicking);
        buttonimg1.setBounds(10, 600, 150, 150);
        //define um evento para o botão,
        //quando clicado, a tela Ordering é aberta
        buttonimg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Ordering orderingFrame = new Ordering();
                    orderingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                    orderingFrame.setLocationRelativeTo(null);
                    orderingFrame.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
   


        //define alguns elementos
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        this.textArea = UiHelper.createTextArea(10, 150, 800, 100,new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Desempenho", 300, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        
        //cria um documento padrão e o vincula ao textarea
        DefaultStyledDocument doc = new DefaultStyledDocument();
        textArea.setDocument(doc);
        //define o alinhamento a esquerda da formatação do documento
        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), leftAlign, false);
        //define conteúdo de texto
        textArea.setText("Tempo de execução: " + executionTime + " milissegundos");
        //solicita uma repintura do componente textArea.
        textArea.repaint();
        //remove layout
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        //adiciona os elementos do frame
        getContentPane().add(titleLabel);
        getContentPane().add(goBackLabel);
        getContentPane().add(textArea);
        getContentPane().add(buttonimg1);

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
	//Este método atualiza a área de texto com o tempo de execução do algoritmo Heap Sort, 
	//garantindo que a atualização seja feita na thread de interface do usuário.
	private void updateTextArea() {
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            textArea.setText("Tempo de execução: " + executionTime + " milissegundos");
	        }
	    });
	}


	@Override
	public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
		
	}
}