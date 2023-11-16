package backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dataBase.Db;
/*A classe InsertionLogic é definida, e ela implementa a interface AutoCloseable. 
 * Isso significa que a classe pode ser usada em um bloco try-with-resources, 
 * onde os recursos serão fechados automaticamente ao sair do bloco.
 */
public class InsertionLogic implements AutoCloseable {
	private static Connection  connection = null;
    public InsertionLogic() {
    	try {
			if (connection == null || connection.isClosed()) {
				  connection = Db.getConnection();
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
      
    }
    //O método insertRecord é definido para inserir um registro no banco de dados.
    public  boolean insertRecord(
        JTextField fid, JTextField state, JComboBox<String> pathRow,
        JComboBox<String> mainClass, JComboBox<String> nameClass, JComboBox<String> julianDay,
        JTextField imageDate, JTextField year, JTextField areaKm, JComboBox<String> satelite, JComboBox<String> sensor, JTextField uuid, JTextField imagem) {
        
        try {
        	//É verificado se todos os campos passados para o método estão preenchidos.
            if (areFieldsFilled(fid, state, pathRow, mainClass, nameClass, julianDay, imageDate, year, areaKm, satelite, sensor, uuid, imagem)) {
            	
            	//É criada a consulta SQL para a inserção
                String insertSQL = "INSERT INTO cerrado (FID, STATE, PATH_ROW, MAIN_CLASS, CLASS_NAME, JULIAN_DAY, IMAGE_DATE, YEAR, AREA_KM, satellite, SENSOR, UUID, IMAGEM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                
                //é preparado um objeto PreparedStatement associado a essa consulta.
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                //Os valores dos campos são definidos nos parâmetros da consulta 
                String areaKmText = areaKm.getText().replace(",", ".");
                preparedStatement.setString(1, fid.getText());
                preparedStatement.setString(2, state.getText());
                preparedStatement.setString(3, pathRow.getSelectedItem().toString());
                preparedStatement.setString(4, mainClass.getSelectedItem().toString());
                preparedStatement.setString(5, nameClass.getSelectedItem().toString());
                preparedStatement.setString(6, julianDay.getSelectedItem().toString());
                preparedStatement.setString(7, imageDate.getText());
                preparedStatement.setString(8, year.getText());
                preparedStatement.setDouble(9, Double.parseDouble(areaKmText));
                preparedStatement.setString(10, satelite.getSelectedItem().toString());
                preparedStatement.setString(11, sensor.getSelectedItem().toString());
                preparedStatement.setString(12, uuid.getText());
                
                // Ler o conteúdo do arquivo de imagem e definir como BLOB
                String imagePath = imagem.getText();
				try {
					FileInputStream imagemStream = new FileInputStream(imagePath);
					preparedStatement.setBinaryStream(13, imagemStream, imagemStream.available());
				} catch (FileNotFoundException e) {
					 JOptionPane.showMessageDialog(null, "Imagem não encontrada");
					e.printStackTrace();
					
				} catch (IOException e) {
					 JOptionPane.showMessageDialog(null, "Erro ao inserir registro no banco de dados.");
					e.printStackTrace();
				}
                

                
                //A consulta é executada usando o método executeUpdate() 
                //e o objeto PreparedStatement é fechado.
                preparedStatement.executeUpdate();
                preparedStatement.close();
                
                
                //Se a inserção for bem-sucedida, uma mensagem de sucesso é exibida
                //com JOptionPane, e o método retorna true.
                JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!");
                return true;
            } else {
            	//Se algum campo estiver vazio, a função showMissingFieldsMessage()
            	//é chamada para exibir uma mensagem de erro, e o método retorna false.
                showMissingFieldsMessage();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //Em caso de exceção, o erro é impresso no console e uma mensagem de erro é exibida
            //com JOptionPane, e o método retorna false.
            JOptionPane.showMessageDialog(null, "Erro ao inserir registro no banco de dados.");
            return false;
        }
    }
    
    //O método areFieldsFilled verifica se os campos estão preenchidos. 
    //Ele aceita um número variável de campos como argumentos.
    public static boolean areFieldsFilled(Object... fields) {
        for (Object field : fields) {
            if (field instanceof JTextField) {
                if (((JTextField)field).getText().isEmpty()) {
                    return false;
                }
            } else if (field instanceof JComboBox) {
                if (((JComboBox<?>)field).getSelectedItem() == null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //O método showMissingFieldsMessage exibe uma mensagem de erro 
    //informando que campos obrigatórios não estão preenchidos.
    public static void showMissingFieldsMessage() {
        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
    }
	@Override
	public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
		
	}
}
