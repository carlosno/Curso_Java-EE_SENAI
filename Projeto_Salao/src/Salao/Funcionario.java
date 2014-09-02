package Salao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;



public class Funcionario {

	private String nome;
	private int telefone;
	private float comissao;
	private int codigo_servicos;
	
	
	public Funcionario(){}
	
	public Funcionario(String nome, String telefone, String comissao) {
		this.nome = nome;
		this.telefone = Integer.parseInt(telefone);
		this.comissao = Float.parseFloat(comissao);
	}

	public void cadastarFuncionario(){
		
		

try{
String comandoSQL = "INSERT INTO funcionario "
		+ " VALUES (null, ?, ?, ?)";
	ConectaMysql con = new ConectaMysql();
		
	con.iniciarConexao();

		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);


		ps.setString(1, this.nome); 
		ps.setInt(2,this.telefone); 
		ps.setFloat(3, this.comissao);


		ps.executeUpdate();

		con.conexao.close();

}catch(Exception exc){
exc.printStackTrace();
}
	
	}
	

	public String buscaFuncionario(String nome){
		
		String nomes = "";
		
		try {
			String comandoSQL="select * from funcionario where nome = ?";
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			ps.setString(1, nome);
			
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()){
				
				nomes+="Matricula :";
				nomes+=resultado.getInt("matricula");
				nomes+="<br/>";
				
				nomes+="Nomes :";
				nomes+=resultado.getString("nome");
				nomes+="<br/>";
			
				nomes+="Telefone :";
				nomes+=resultado.getInt("telefone");
				nomes+="<br/>";
			
				nomes+="Comissao :";
				nomes+=resultado.getInt("comissao");
				nomes+="<br/>";
			}
			con.conexao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nomes;
	}
	
	public void editarFuncionario(int matricula){
		
		try {
			String comandoSQL = "update funcionario set nome=?,telefone=?,comissao=? where matricula=?";
					
				ConectaMysql con = new ConectaMysql();
					
				con.iniciarConexao();

					PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);


					ps.setString(1, this.nome); 
					ps.setInt(2,this.telefone); 
					ps.setFloat(3, this.comissao);
					ps.setInt(4,matricula);

					ps.executeUpdate();

					con.conexao.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public boolean cadastraServicoFuncionario(int matricula,int codigo_servico){
		boolean servicofuncionario = false;
		
		try{
			String comandoSQL = "INSERT INTO servicos_funcionario VALUES (?, ?)";
			
			//Iniciando a conexão
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			//import java.sql
			//O objeto ps recebe a conexão vinculada ao comando SQL
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			//Passagem de valor para o ?
			ps.setInt(1, codigo_servico);
			ps.setInt(2, matricula);
			
			
			//Executa o comando no banco de dados e retorna
			//a quantidade de linhas afetadas. Se for diferente de
			//zero significa que o comando foi executado
			if(ps.executeUpdate() != 0){
				servicofuncionario = true;
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
		return servicofuncionario;
	}

	public boolean removeServicoFuncionario(int matricula,int codigo_servico){
		boolean removeservicofuncionario = false;
		
		try{
			String comandoSQL = "delete from servicos_funcionario where servicos_codigo = ? and funcionario_matricula = ?";
			
			//Iniciando a conexão
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			//import java.sql
			//O objeto ps recebe a conexão vinculada ao comando SQL
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			//Passagem de valor para o ?
			ps.setInt(1, codigo_servico);
			ps.setInt(2, matricula);
			
			
			//Executa o comando no banco de dados e retorna
			//a quantidade de linhas afetadas. Se for diferente de
			//zero significa que o comando foi executado
			if(ps.executeUpdate() != 0){
				removeservicofuncionario = true;
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
		return removeservicofuncionario;
	}
	
	
	public String buscafuncionario2(){
		String nomes = "";
		
		try {
			String comandoSQL="select * from funcionario";
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery();
			
			nomes += "<select name='funcionario'>";
			while(resultado.next()){
				nomes += "<option value='" + resultado.getString("matricula") + "'>" + resultado.getString("nome")  + "</option>";
			}
			nomes += "</select>";
			
			con.conexao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nomes;
}

	public String buscaServicofuncionario(int codigo){
		String nomes = "";
		
		try {
			String comandoSQL="select a.descricao, a.codigo from servicos_funcionario b,servicos a  "
					+ "where b.servicos_codigo=a.codigo and funcionario_matricula=?";
			
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			ps.setInt(1, codigo);
			
			
			ResultSet resultado = ps.executeQuery();
			
			nomes += "<select name='servicos1'>";
			while(resultado.next()){
				nomes += "<option value='" + resultado.getString("codigo") + "'>" + resultado.getString("descricao")  + "</option>";
			}
			nomes += "</select>";
			
			con.conexao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nomes;
}
	
}
