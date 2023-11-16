package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {
	
	private static Connection conn = null;

	
	private static Properties loadProperties() {
		//leitura do arquivo db.properties usando um objeto FileInputStream
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			//Cria um objeto Properties que será usado para armazenar as propriedades do banco de dados.
			Properties props = new Properties();
			
			//Carrega as propriedades do banco de dados a partir do arquivo db.properties.
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			//Lança uma exceção personalizada DbException passando a mensagem de erro da exceção IOException
			throw new DbException(e.getMessage());
		}
	}
	

	public static Connection getConnection() {
		//Verifica se a conexão conn é nula.
		if (conn == null) {
			try {
				//chama o método loadProperties para carregar as propriedades do banco de dados a partir do arquivo db.properties.
				Properties props = loadProperties();
				
				//Obtém a URL de conexão com o banco de dados a partir das propriedades carregadas.
				String url = props.getProperty("dburl");
				
				//Estabelece a conexão com o banco de dados usando a classe DriverManager e as informações de URL e propriedades.
				conn = DriverManager.getConnection(url, props);
				
			}
			catch (SQLException e) {
				//Lança uma exceção personalizada DbException passando a mensagem de erro da exceção SQLException.
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	public static void closeConnection(Connection conn) {
		//Verifica se a conexão conn não é nula.
		if (conn != null) {
			try {
				//Fecha a conexão usando o método close() da classe Connection.
				conn.close();
			} catch (SQLException e) {
				//Lança uma exceção personalizada DbException passando a mensagem de erro da exceção SQLException.
				throw new DbException(e.getMessage());
			}
		}
	}
	

	public static void closeStatement(Statement st) {
		//Verifica se o objeto Statement st não é nulo. 
		if (st != null) {
			try {
				//Fecha o objeto Statement usando o método close(). 
				st.close();
			} catch (SQLException e) {
				//Lança uma exceção personalizada DbException, passando a mensagem de erro da exceção SQLException.
				throw new DbException(e.getMessage());
			}
		}
	}
	

	public static void closeResultSet(ResultSet rs) {
		//Verifica se o objeto ResultSet rs não é nulo.
		if (rs != null) {
			try {
				//Fecha o objeto ResultSet usando o método close(). 
				rs.close();
			} catch (SQLException e) {
				//Lança uma exceção personalizada DbException, passando a mensagem de erro da exceção SQLException. 
				throw new DbException(e.getMessage());
			}
		}
	}
}