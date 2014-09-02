package Salao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Servicos {


	private String descricao;
	private float valor;
	private String tipo;
	

	public Servicos(String descricao, String valor, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
		this.valor = Float.parseFloat(valor);
	}

	public Servicos(){}
	
	public void cadastrarServico(){
		
		

		try{
		String comandoSQL = "INSERT INTO servicos "
				+ " VALUES (null, ?, ?, ?)";
			ConectaMysql con = new ConectaMysql();
				
			con.iniciarConexao();

				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				ps.setString(1, this.descricao); 
				ps.setFloat(2,this.valor); 
				ps.setString(3, this.tipo);
				ps.executeUpdate();

				con.conexao.close();

		}catch(Exception exc){
		exc.printStackTrace();
		}
			
			}
			
	public Servicos buscaServicoCodigo(String codigo){
		
		Servicos s1 = new Servicos();
				
			try {
				String comandoSQL="select * from servicos where codigo = ?";
				ConectaMysql con = new ConectaMysql();
				con.iniciarConexao();

				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
				ps.setInt(1, Integer.parseInt(codigo));
				ResultSet resultado = ps.executeQuery();
				
				while(resultado.next()){
								
				s1.descricao=resultado.getString("descricao");
				s1.valor=resultado.getFloat("valor");
				s1.tipo=resultado.getString("tipo");
				}
				
				con.conexao.close();
			} catch (Exception exc) {
				// TODO: handle exception
				exc.printStackTrace();
			}
			
			return s1;
		}

	
public String buscaServicoTipo(String tipo){
		
		String nomes = "";
		
		try {
			String comandoSQL="select * from servicos where tipo = ?";
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			ps.setString(1, tipo);
			
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()){
				
				nomes+="Codigo :";
				nomes+=resultado.getInt("codigo");
				nomes+="<br/>";
				
				nomes+="Descricao :";
				nomes+=resultado.getString("descricao");
				nomes+="<br/>";
			
				nomes+="Valor :";
				nomes+=resultado.getFloat("valor");
				nomes+="<br/>";
			
				nomes+="Tipo :";
				nomes+=resultado.getString("tipo");
				nomes+="<br/>";
			}
			con.conexao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nomes;
}
	public void editarServico(int codigo){
	
	try {
		String comandoSQL = "update servicos set descricao=?,valor=?,tipo=? where codigo=?";
				
			ConectaMysql con = new ConectaMysql();
				
			con.iniciarConexao();

				PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);


				ps.setString(1, this.descricao); 
				ps.setFloat(2,this.valor); 
				ps.setString(3, this.tipo);
				ps.setInt(4, codigo);
				ps.executeUpdate();

				con.conexao.close();
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}
	
	public String buscaServicos(){
		String nomes = "";
		
		try {
			String comandoSQL="select * from servicos";
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery();
			
			nomes += "<select name='servicos'>";
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