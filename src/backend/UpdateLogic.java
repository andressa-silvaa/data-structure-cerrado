package backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dataBase.Db;
import utils.ConversorBlob;

/*A classe UpdateLogic é definida, e ela implementa a interface AutoCloseable. 
 * Isso significa que a classe pode ser usada em um bloco try-with-resources, 
 * onde os recursos serão fechados automaticamente ao sair do bloco.
 */
public class UpdateLogic implements AutoCloseable {
	private static Connection connection;
	
	//O método validateId verifica se um registro existe no banco de dados.
    public static boolean validateId(int id) {
       
    	connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            if (connection == null || connection.isClosed()) {
                connection = Db.getConnection();
            }
            
            //O PreparedStatement é configurado com o ID a ser verificado.
            String query = "SELECT * FROM cerrado WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            
            //A consulta é executada com executeQuery(), e o resultado é verificado 
            //usando resultSet.next(). Se houver um próximo resultado, 
            //isso significa que o registro com o ID existe, e a função retorna true.
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        
        	Db.closeStatement(preparedStatement);
            Db.closeResultSet(resultSet);
        }
    }
    
    //método responsável por atualizar um registro no banco de dados.
    public static boolean updateRecord(int id, int fid, String state, String pathRow, String mainClass, String nameClass, int julianDay, String imageDate, int year, String areaKm, String satellite, String sensor, String uuid, String imagem) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (connection == null || connection.isClosed()) {
                connection = Db.getConnection();
            }
            //O PreparedStatement é configurado com a query a ser executada.
            String query = "UPDATE cerrado SET FID = ?, STATE = ?, PATH_ROW = ?, MAIN_CLASS = ?, CLASS_NAME = ?, JULIAN_DAY = ?, IMAGE_DATE = ?, YEAR = ?, AREA_KM = ?, satellite = ?, SENSOR = ?, UUID = ? , IMAGEM = ? WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            
            //Os valores dos campos são definidos nos parâmetros da consulta 
            String areaKmText = areaKm.replace(",", ".");
            preparedStatement.setInt(1, fid);
            preparedStatement.setString(2, state);
            preparedStatement.setString(3, pathRow);
            preparedStatement.setString(4, mainClass);
            preparedStatement.setString(5, nameClass);
            preparedStatement.setInt(6, julianDay);
            preparedStatement.setString(7, imageDate);
            preparedStatement.setInt(8, year);
            preparedStatement.setDouble(9, Double.parseDouble(areaKmText));
            preparedStatement.setString(10, satellite);
            preparedStatement.setString(11, sensor);
            preparedStatement.setString(12, uuid);
            preparedStatement.setInt(14, id);
            
            String imagePath = imagem;
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
            
            
            //salva a quantidade de linhas afetadas pela execução da query
            int rowsUpdated = preparedStatement.executeUpdate();
            
            //retorna true se forem feitas mudanças no banco de dados
            return rowsUpdated > 0; 
        } catch (SQLException e) {
        	//Caso haja uma execessão, é retornado false
            e.printStackTrace();
            return false;
        } finally {
       
        	Db.closeStatement(preparedStatement);
           
        }
    }

    public static Records getRegistroPorId(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Records records = null;

        try {
        	if (connection == null || connection.isClosed()) {
                connection = Db.getConnection();
            }
        	
        	//string SQL que será usada para consultar o banco de dados.
            String query = "SELECT * FROM cerrado WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            
            //O valor do ID fornecido como argumento é definido no PreparedStatement 
            //para substituir o ponto de interrogação na consulta SQL.
            preparedStatement.setInt(1, id);
            
            //A consulta é executada no banco de dados
            resultSet = preparedStatement.executeQuery();
   
            
            String imagemString = "EX: C://aps//fox.jpg";
            //verifica-se se o resultSet contém algum resultado.
            if (resultSet.next()) {
            	//os dados do registro são recuperados do resultSet e 
            	//são usados para criar um novo objeto Records.
                records = new Records(
                    resultSet.getString("FID"),
                    resultSet.getString("STATE"),
                    resultSet.getString("PATH_ROW"),
                    resultSet.getString("MAIN_CLASS"),
                    resultSet.getString("CLASS_NAME"),
                    resultSet.getInt("JULIAN_DAY"),
                    resultSet.getString("IMAGE_DATE"),
                    resultSet.getInt("YEAR"),
                    resultSet.getString("AREA_KM"),
                    resultSet.getString("SATELLITE"),
                    resultSet.getString("SENSOR"),
                    resultSet.getString("UUID"),
                    imagemString,
                    resultSet.getBinaryStream("IMAGEM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.closeStatement(preparedStatement);
            Db.closeResultSet(resultSet);
        }
        
        //retorna os registros do id fornecido
        return records;
    }

	@Override
	public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
		
	}
}