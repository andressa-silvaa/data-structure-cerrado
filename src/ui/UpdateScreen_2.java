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

import backend.Records;
import backend.UpdateLogic;
import utils.CustomButton;
import utils.UiHelper;


public class UpdateScreen_2 extends BaseFrame {
	private int id;
	private JTextField fid;
	private JTextField state;
	private JTextField pathRow;
	private JTextField mainClass;
	private JTextField nameClass;
	private JTextField julianDay;
	private JTextField imageDate;
	private JTextField year;
	private JTextField areaKm;
	private JTextField satelite;
	private JTextField sensor;
	private JTextField uuid;
	private JTextField imagem;

    public UpdateScreen_2(int id) throws IOException {
    	super(ImageIO.read(new File("C:\\aps\\fox.jpeg.jpg")));
        getContentPane().setBackground(Color.WHITE);
        this.id = id; 
        initializeComponents();
        preencherCampos(id);
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
                    UpdateScreen_1 updateScreen_1 = new UpdateScreen_1();
                    updateScreen_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                    updateScreen_1.setLocationRelativeTo(null);
                    updateScreen_1.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
  
        buttonimg1.setBounds(10, 600, 150, 150);
        
      
        JLabel goBackLabel = UiHelper.createLabel("Voltar", 50, 570, 150, 30, new Font("Chiller", Font.BOLD, 40), Color.WHITE);
        JLabel titleLabel = UiHelper.createLabel("Update", 340, 10, 800, 100, new Font("Chiller", Font.BOLD, 80), Color.WHITE);
        JLabel fidLabel = UiHelper.createLabel("FID: ", 50, 180, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
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
        JLabel imagemLabel = UiHelper.createLabel("IMAGEM: *", 50, 480, 100, 30, new Font("Chiller", Font.BOLD, 24), Color.WHITE);
        JButton buttonSave = UiHelper.createRoundButton("Salvar", 700, 600, 100, 100, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        JButton imagemButton = UiHelper.createRoundButton("ver imagem", 450, 480, 350, 30, new Font("Chiller", Font.BOLD, 30), Color.WHITE);
        
        imagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ImageScreen imageScreen = new ImageScreen(id);
                    imageScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                    imageScreen.setLocationRelativeTo(null);
                    imageScreen.setVisible(true);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        
        fid = UiHelper.createTextField(190, 180, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        state = UiHelper.createTextField(190, 230, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        pathRow = UiHelper.createTextField(190, 280, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        mainClass = UiHelper.createTextField(190, 330, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        nameClass = UiHelper.createTextField(190, 380, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        julianDay = UiHelper.createTextField(190, 430, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        imageDate = UiHelper.createTextField(600, 180, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        year = UiHelper.createTextField(600, 230, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        areaKm = UiHelper.createTextField(600, 280, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        satelite = UiHelper.createTextField(600, 330, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        sensor = UiHelper.createTextField(600, 380, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        uuid = UiHelper.createTextField(600, 430, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        imagem = UiHelper.createTextField(190, 480, 200, 30, new Font("Chiller", Font.BOLD, 26), Color.BLACK);
        
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
             

       

               
                    boolean updateSuccessful = UpdateLogic.updateRecord(
                    	    id,
                    	    Integer.parseInt(fid.getText()),
                    	    state.getText(),
                    	    pathRow.getText(),
                    	    mainClass.getText(),
                    	    nameClass.getText(),
                    	    Integer.parseInt(julianDay.getText()),
                    	    imageDate.getText(),
                    	    Integer.parseInt(year.getText()),
                    	    areaKm.getText(),
                    	    satelite.getText(),
                    	    sensor.getText(),
                    	    uuid.getText(),
                    	    imagem.getText());

                    if (updateSuccessful) {
       
                        JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
                        UpdateScreen_1 updateScreen_1 = new UpdateScreen_1();
                        updateScreen_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                        updateScreen_1.setLocationRelativeTo(null);
                        updateScreen_1.setVisible(true);
                    } else {
                    
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                    dispose(); 
                } catch (NumberFormatException ex) {
                
                    JOptionPane.showMessageDialog(null, "ID inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
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
        getContentPane().add(imagemButton);
        
    }
    private void preencherCampos(int id) {
    	Records records = UpdateLogic.getRegistroPorId(id);

        if (records != null) {
            fid.setText(records.getFid());
            state.setText(records.getState());
            pathRow.setText(records.getPathRow());
            mainClass.setText(records.getMainClass());
            nameClass.setText(records.getNameClass());
            julianDay.setText(String.valueOf(records.getJulianDay()));
            imageDate.setText(records.getImageDate());
            year.setText(String.valueOf(records.getYear()));
            areaKm.setText(records.getAreaKm());
            satelite.setText(records.getSatellite());
            sensor.setText(records.getSensor());
            uuid.setText(records.getUuid());
            imagem.setText(records.getImagem());
        }

}
}