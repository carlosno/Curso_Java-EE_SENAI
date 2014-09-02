package Salao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clientes {

	private int numerocel;
	private String nome;
	private String email;
	public Date nascimento;
	
	
	public int getnumerocel(){
		
		return numerocel;
	}
	
	public String getnome(){
		
		return this.nome;
	}	
	
	public String getemail(){
		
		return email;
	}
	
	public Date getdata(){
		return nascimento;
		
	}
	public Clientes(){
		//Construtor vazio
		this.numerocel = 0;
		this.nome = "";
		this.email = "";
		
		try {
			nascimento = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2011");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Clientes(String numerocel, String nome, String email,String data){
		
		this.numerocel= Integer.parseInt(numerocel);
		this.nome=nome;
		this.email=email;
	
		try {
			nascimento = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2011");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	

	//M�todo para cadastrar um cliente a partir do seu objeto montado no construtor
		public boolean cadastraCliente(Clientes c){
			boolean clienteCadastrado = false;
			
			try{
				String comandoSQL = "INSERT INTO clientes VALUES (?, ?, ?, ?)";
				
				//Iniciando a conex�o
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();
				
				//import java.sql
				//O objeto ps recebe a conex�o vinculada ao comando SQL
				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				
				//Passagem de valor para o ?
				ps.setInt(1, c.numerocel);
				ps.setString(2, c.nome);
				ps.setString(3, c.email);
				ps.setTimestamp(4, new Timestamp(c.nascimento.getTime()));
				
				
				//Executa o comando no banco de dados e retorna
				//a quantidade de linhas afetadas. Se for diferente de
				//zero significa que o comando foi executado
				if(ps.executeUpdate() != 0){
					clienteCadastrado = true;
				}
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return clienteCadastrado;
		}


		//M�todo para buscar um determinado cliente atrav�s do seu c�digo
		//� retornado um objeto tamb�m do tipo Cliente para que seja poss�vel recuperar
		//as informa��es de cada campo de maneira mais f�cil
		public Clientes buscaCliente(String numerocel){
			Clientes c1 = new Clientes();
			
			try{
				String comandoSQL = "SELECT * FROM clientes WHERE numerocel = ?";
				//A classe PreparedStatement � utilizada para passar um
				//comando ao banco de dados. Nela precisamos indicar
				//aonde ser� executado o comando, ou seja, em qual conex�o
				
				//Iniciando a conex�o
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();
				
				//import java.sql
				//O objeto ps recebe a conex�o vinculada ao comando SQL
				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				
				//Passagem de valor para o ?
				ps.setInt(1, Integer.parseInt(numerocel));
				
				//Quando vamos executar um SELECT temos um retorno do 
				//banco de dados. Precisamos guardar o valor retornado
				//em um objeto (ResultSet) import java.sql
				ResultSet resultado = ps.executeQuery();
				
				//Passagem por todas as linhas do que o SELECT retornou
				while(resultado.next()){
					//O m�todo getString pega o valor da coluna especificada
					c1.numerocel = resultado.getInt("numerocel");
					c1.nome = resultado.getString("nome");
					c1.email = resultado.getString("email");
					c1.nascimento = resultado.getDate("nascimento");
					
				}
				con.conexao.close();
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return c1;
		}
		
		//M�todo para atualizar um cliente atrav�s do seu c�digo e dos valores
		//preenchidos no objeto Cliente que � passado como par�metro do m�todo
		public boolean atualizaCliente(String numerocel, Clientes c){
			boolean clienteAtualizado = false;
			
			try{
				String comandoSQL = "UPDATE clientes SET nome = ?, email = ?, nascimento = ? WHERE numerocel = ?";
				
				//Iniciando a conex�o
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();
				
				//import java.sql
				//O objeto ps recebe a conex�o vinculada ao comando SQL
				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				
				//Passagem de valor para o ?
				ps.setString(1, c.nome);
				ps.setString(2, c.email);
				ps.setTimestamp(3, new Timestamp(c.nascimento.getTime()));
				ps.setInt(4, c.numerocel);
				
				
				if(ps.executeUpdate() != 0){
					clienteAtualizado = true;
				}
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return clienteAtualizado;
		}
		
		//M�todo para remover um cliente a partir do seu c�digo
		public boolean removeCliente(String numerocel){
			boolean clienteRemovido = false;
			
			try{
				String comandoSQL = "DELETE FROM clientes WHERE numerocel = ?";
				
				//Iniciando a conex�o
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();
				
				//import java.sql
				//O objeto ps recebe a conex�o vinculada ao comando SQL
				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				
				//Passagem de valor para o ?
				ps.setInt(1, Integer.parseInt(numerocel));
				
				if(ps.executeUpdate() != 0){
					clienteRemovido = true;
				}
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return clienteRemovido;
		}
		
		
		public String buscaCliente(){
			String html = "";
			
			try{
				String comandoSQL = "SELECT * FROM clientes";
				//A classe PreparedStatement � utilizada para passar um
				//comando ao banco de dados. Nela precisamos indicar
				//aonde ser� executado o comando, ou seja, em qual conex�o
				
				//Iniciando a conex�o
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();
				
				//import java.sql
				//O objeto ps recebe a conex�o vinculada ao comando SQL
				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				
				//Quando vamos executar um SELECT temos um retorno do 
				//banco de dados. Precisamos guardar o valor retornado
				//em um objeto (ResultSet) import java.sql
				ResultSet resultado = ps.executeQuery();
				
				//Passagem por todas as linhas do que o SELECT retornou
				while(resultado.next()){
					//O m�todo getString pega o valor da coluna especificada
					html += "<div class='numerocel'>"+ resultado.getInt("numerocel") + "</div>";
					html += "<div class='nome'>"+ resultado.getString("nome") + "</div>";
					html += "<div class='email'>"+ resultado.getString("email") + "</div>";
					html += "<div class='nascimento'>"+ resultado.getDate("nascimento") + "</div>";
					html += "<div class='limpa'>";
				}
				con.conexao.close();
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return html;
		}
		
		
		public String listaClientes(){
			String html = "";
			
			try{
				String comandoSQL = "SELECT * FROM clientes";
				//A classe PreparedStatement � utilizada para passar um
				//comando ao banco de dados. Nela precisamos indicar
				//aonde ser� executado o comando, ou seja, em qual conex�o
				
				//Iniciando a conex�o
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();
				
				//import java.sql
				//O objeto ps recebe a conex�o vinculada ao comando SQL
				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				
				//Quando vamos executar um SELECT temos um retorno do 
				//banco de dados. Precisamos guardar o valor retornado
				//em um objeto (ResultSet) import java.sql
				ResultSet resultado = ps.executeQuery();
				
				//Passagem por todas as linhas do que o SELECT retornou
				html += "<select name='cliente'>";
				while(resultado.next()){
					//O m�todo getString pega o valor da coluna especificada
					html += "<option value='" + resultado.getString("numerocel") + "'>" +  resultado.getString("numerocel") + " - " + resultado.getString("nome")  + "</option>";
					
				}
				
				html += "</select>";
				con.conexao.close();
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			return html;
		}


	}



