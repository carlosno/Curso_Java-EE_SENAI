package Salao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaMysql {

	private String usuario ="root";
	private String senha ="Senai1234";
	private String driver = "com.mysql.jdbc.Driver";
	private String caminho ="jdbc:mysql://localhost/salao";
	
	public Connection conexao;
	

	public boolean iniciarConexao(){
		
		boolean conectado = false;
		
		try{
			Class.forName(driver);
			
			conexao = DriverManager.getConnection(caminho,usuario,senha);
			 conectado=true;
			 
		}catch(Exception exc ){
			
			exc.printStackTrace();
			
		}
		
		
		return conectado;
	}
	}
