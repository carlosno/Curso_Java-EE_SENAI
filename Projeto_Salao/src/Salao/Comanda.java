package Salao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comanda {

	private Date data;
	private String status;
	private String horario;
	private int codigo;
	private int servicos_codigo,funcionario_matricula,clientes_numerocel;
	private float valor;
	
	
	public Comanda(){
		this.codigo=0;
		this.data=null;
		this.status="";
		this.horario="";
		
	}
	
	
	public Comanda(String data){
		
		try {
			this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Comanda(String data, String horario){
		
		try {
			this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.horario=horario;
		this.status="em aberto";
	}
	
	
	public boolean cadastraComanda(Comanda c,int servicos_codigo,int funcionario_matricula, int clientes_numerocel ){
		boolean comandaCadastrada = false;
		
		try{
			String comandoSQL = "INSERT INTO comanda VALUES (null, ?, ?, ?, ?, ?, ?)";
			
			//Iniciando a conexão
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			//import java.sql
			//O objeto ps recebe a conexão vinculada ao comando SQL
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			//Passagem de valor para o ?
			ps.setTimestamp(1, new Timestamp(c.data.getTime()));
			ps.setString(2, c.status);
			ps.setString(3, c.horario);
			ps.setInt(4, servicos_codigo);
			ps.setInt(5, funcionario_matricula);
			ps.setInt(6, clientes_numerocel);
			
			
			//Executa o comando no banco de dados e retorna
			//a quantidade de linhas afetadas. Se for diferente de
			//zero significa que o comando foi executado
			if(ps.executeUpdate() != 0){
				comandaCadastrada = true;
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
		return comandaCadastrada;
	}

	public String listaComanda(Comanda c){
		String html = "";
		
		try{
			String comandoSQL = "SELECT * FROM comanda where data=?";
		
		
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
		
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ps.setTimestamp(1, new Timestamp(c.data.getTime()));
			ResultSet resultado = ps.executeQuery();
			
			//Passagem por todas as linhas do que o SELECT retornou
			html += "<select name='comanda'>";
			while(resultado.next()){
				//O método getString pega o valor da coluna especificada
				html += "<option value='" + resultado.getInt("codigo") + "'>" +  resultado.getString("status") + " - " + resultado.getInt("clientes_numerocel")  + "</option>";
			}
			
			html += "</select>";
			con.conexao.close();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
		return html;
	}

	public boolean receberComanda(int codigo){
		
		boolean comandarecebida=false;
		
		try {
			String comandoSQL = "update comanda set status ='paga' where codigo=?";
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			ps.setInt(1,codigo);
			
			if(ps.executeUpdate() != 0){
				comandarecebida = true;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return comandarecebida;
	}

	
public Comanda buscaComandaCodigo(String codigo){
			
		 Comanda c = new Comanda();
				
			try {
				String comandoSQL="select a.codigo,a.status,a.horario,a.servicos_codigo,"
						+ "a.funcionario_matricula,a.clientes_numerocel,b.valor from comanda a,"
						+ " servicos b where b.codigo=a.servicos_codigo and a.codigo = ?";
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();

				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				ps.setInt(1, Integer.parseInt(codigo));
				ResultSet resultado = ps.executeQuery();
				
				while(resultado.next()){
								
				c.codigo=resultado.getInt("codigo");
				c.status=resultado.getString("status");
				c.data=resultado.getDate("data");
				c.servicos_codigo=resultado.getInt("servicos_codigo");
				c.funcionario_matricula=resultado.getInt("funcionario_matricula");
				c.clientes_numerocel=resultado.getInt("clientes_numerocel");
				c.valor=resultado.getFloat("valor");
				}
				
				con.conexao.close();
			} catch (Exception exc) {
				// TODO: handle exception
				exc.printStackTrace();
			}
			
			return c;
		}

}
	

