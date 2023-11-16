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
import javax.swing.JPanel;
import utils.CustomButton;
import utils.UiHelper;



public class Ordering extends BaseFrame {
	//O construtor Ordering é criado. Ele define a imagem de fundo
    public Ordering() throws IOException {
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
        // // Configuração do botão "Voltar" para retornar à tela de registros
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
         

        //posicionamento do botão na tela
        buttonimg1.setBounds(10, 600, 150, 150);
        
        //criação de rótulos e botões
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Ordering", 320, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JButton insertionSort = UiHelper.createRoundButton("Insertion Sort", 100, 300, 200, 60, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JButton quickSort = UiHelper.createRoundButton("Quick Sort", 370, 300, 150, 60, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JButton bubbleSort = UiHelper.createRoundButton("Bubble Sort", 600, 300, 150, 60, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        
        //É configurado um ouvinte de ação para o botão "Merge Sort" 
        //para abrir uma tela de desempenho quando o botão é pressionado.
        insertionSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	PerformanceInsertionSort performanceFrame = new PerformanceInsertionSort();
                    performanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                    performanceFrame.setLocationRelativeTo(null);
                    performanceFrame.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
        //É configurado um ouvinte de ação para o botão "Quick Sort" 
        //para abrir uma tela de desempenho quando o botão é pressionado.
        quickSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PerformanceQuickSort performanceFrame = new PerformanceQuickSort();
                    performanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    performanceFrame.setLocationRelativeTo(null);
                    performanceFrame.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
        //É configurado um ouvinte de ação para o botão "Heap Sort" 
        //para abrir uma tela de desempenho quando o botão é pressionado.
        bubbleSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	PerformanceBubbleSort performanceFrame = new PerformanceBubbleSort();
                    performanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    performanceFrame.setLocationRelativeTo(null);
                    performanceFrame.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
        //remoção de layout automático
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        //adição de elementos a tela
        getContentPane().add(titleLabel);
        getContentPane().add(insertionSort);
        getContentPane().add(quickSort);
        getContentPane().add(bubbleSort);
        getContentPane().add(goBackLabel);
        getContentPane().add(buttonimg1);
    }
}