package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dataBase.Db;
/*A classe DeleteLogic é definida, e ela implementa a interface AutoCloseable. 
 * Isso significa que a classe pode ser usada em um bloco try-with-resources, 
 * onde os recursos serão fechados automaticamente ao sair do bloco.
 */
public class DeleteLogic implements AutoCloseable{
	private Connection connection;
	
	//método para deletar registro do banco
    public static boolean deleteRecord(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	//Verifica se a variável connection é nula ou se a conexão está fechada. 
            if (connection == null || connection.isClosed()) {
                connection = Db.getConnection();
            }
            //query contendo a consulta SQL de exclusão. 
            String query = "DELETE FROM cerrado WHERE ID = ?";
            
            //é preparado um PreparedStatement associado a essa consulta
            preparedStatement = connection.prepareStatement(query);
            
            //o valor do parâmetro id é definido 
            preparedStatement.setInt(1, id);
            
            //A consulta é executada usando o método executeUpdate(), 
            //que retorna o número de linhas afetadas. 
            int rowsDeleted = preparedStatement.executeUpdate();
            
            //O método retorna true se pelo menos uma linha foi excluída
            return rowsDeleted > 0;
        } catch (SQLException e) {
        	//Em caso de exceção, o erro é impresso no console (usando e.printStackTrace()) 
        	//e o método retorna false para indicar que a exclusão não foi bem-sucedida.
            e.printStackTrace();
            return false;
        } finally {
            Db.closeStatement(preparedStatement);
        }
    }
	@Override
	public void close() throws Exception {
		//Implementação do método close da interface AutoCloseable. 
		//Esse método é usado para fechar a conexão com o banco de dados. 
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
		
}
}