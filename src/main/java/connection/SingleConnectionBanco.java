package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String url = "jdbc:postgresql://localhost:5432/java-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "mclmaia";
	private static Connection connection = null;
	
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		
		conectar();
	}
	
	public SingleConnectionBanco() {/*quando estiver uma instancia vai conectar*/
		conectar();
	}

	
	private static void conectar () {
		try {
			
			if (connection == null) {
				
				Class.forName("org.postgresql.Driver");/*Carrega o driver de conex�o com banco*/
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false);/*Evitar altera��o no banco sem permiss�o*/
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
