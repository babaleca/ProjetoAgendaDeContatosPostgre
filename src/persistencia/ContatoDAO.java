/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import modelos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ejmcc
 */
public class ContatoDAO implements IcrudContato{
  //Atributos
  // (no Postgre não precisa de nomeDoArquivoNoDisco)

  //Metodo Construtor
  public ContatoDAO(){
  }

  // Método auxiliar simples para validar e-mail
  private boolean emailValido(String email) {
      if (email == null) return false;
      email = email.trim();
      if (email.isEmpty()) return false;

      int posArroba = email.indexOf('@');
      int posPonto  = email.lastIndexOf('.');

      if (posArroba <= 0) return false;
      if (posPonto <= posArroba + 1) return false;
      if (posPonto == email.length() - 1) return false;

      return true;
  }

  //Metodos Sobrecarregados
  @Override
  public void incluir(Contato objeto) throws Exception {
    try{
      // validações básicas
      if (objeto.getNomeCompleto() == null || objeto.getNomeCompleto().trim().isEmpty()) {
          throw new Exception("Nome é obrigatório.");
      }
      if (!emailValido(objeto.getEmail())) {
          throw new Exception("E-mail inválido.");
      }
      if (objeto.getTelefone() == null) {
          throw new Exception("Telefone é obrigatório.");
      }

      // verifica se já existe contato com o mesmo ID
      if (buscarID(objeto.getIdContato()) != null) {
          throw new Exception("Já existe um contato com o identificador " + objeto.getIdContato());
      }

      String sql = "INSERT INTO contatos (id, nome, email, telefone) VALUES (?, ?, ?, ?)";

      Connection con = ConexaoPostgre.getConexao();
      PreparedStatement ps = con.prepareStatement(sql);

      ps.setInt(1, objeto.getIdContato());
      ps.setString(2, objeto.getNomeCompleto());
      ps.setString(3, objeto.getEmail());
      ps.setString(4, objeto.getTelefone().toString());

      ps.executeUpdate();

      ps.close();
      con.close();

    }catch(Exception erro){
      String msg = "Metodo Incluir Contato - "+erro.getMessage();
      throw new Exception(msg);
    }
  }

  @Override
  public void excluir(int idContato) throws Exception {
    try{
      String sql = "DELETE FROM contatos WHERE id = ?";

      Connection con = ConexaoPostgre.getConexao();
      PreparedStatement ps = con.prepareStatement(sql);

      ps.setInt(1, idContato);

      ps.executeUpdate();

      ps.close();
      con.close();

    }catch(Exception erro){
      String msg = "Persistencia - Metodo Excluir - "+erro.getMessage();
      throw new Exception(msg);
    }
  }

  @Override
  public void alterar(Contato objeto) throws Exception {
    try {
      // validações básicas
      if (objeto.getNomeCompleto() == null || objeto.getNomeCompleto().trim().isEmpty()) {
          throw new Exception("Nome é obrigatório.");
      }
      if (!emailValido(objeto.getEmail())) {
          throw new Exception("E-mail inválido.");
      }
      if (objeto.getTelefone() == null) {
          throw new Exception("Telefone é obrigatório.");
      }

      // (opcional, mas ajuda) garantir que existe antes de alterar
      if (buscarID(objeto.getIdContato()) == null) {
          throw new Exception("Contato não encontrado para alterar.");
      }

      String sql = "UPDATE contatos SET nome = ?, email = ?, telefone = ? WHERE id = ?";

      Connection con = ConexaoPostgre.getConexao();
      PreparedStatement ps = con.prepareStatement(sql);

      ps.setString(1, objeto.getNomeCompleto());
      ps.setString(2, objeto.getEmail());
      ps.setString(3, objeto.getTelefone().toString());
      ps.setInt(4, objeto.getIdContato());

      ps.executeUpdate();

      ps.close();
      con.close();

    }catch(Exception erro){
      String msg = "Persistencia - Metodo Atualizar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }

  @Override
  public Contato consultar(String nomeCompleto) throws Exception {
    try{
      String sql = "SELECT id, nome, email, telefone FROM contatos WHERE LOWER(nome) = LOWER(?) LIMIT 1";

      Connection con = ConexaoPostgre.getConexao();
      PreparedStatement ps = con.prepareStatement(sql);

      ps.setString(1, nomeCompleto);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        int idContatoAux = rs.getInt("id");
        String nomeAux = rs.getString("nome");
        String email = rs.getString("email");
        String telefoneStr = rs.getString("telefone");

        Telefone telefone = Telefone.fromFormattedString(telefoneStr);

        Contato objContato = null;
        objContato = new Contato(idContatoAux, nomeAux, email, telefone);

        rs.close();
        ps.close();
        con.close();

        return objContato;
      }

      rs.close();
      ps.close();
      con.close();

      return null;

    }catch(Exception erro){
      String msg = "Persistencia - Metodo Consultar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }

  @Override
  public ArrayList<Contato> listagemDeContatos() throws Exception {
    try{
      ArrayList<Contato> listaDeContatos = new ArrayList<>();

      String sql = "SELECT id, nome, email, telefone FROM contatos ORDER BY id";

      Connection con = ConexaoPostgre.getConexao();
      PreparedStatement ps = con.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        int idContato = rs.getInt("id");
        String nomeCompleto = rs.getString("nome");
        String email = rs.getString("email");
        String telefoneStr = rs.getString("telefone");

        Telefone telefone = Telefone.fromFormattedString(telefoneStr);

        Contato objetoContato = new Contato(idContato, nomeCompleto, email, telefone);
        listaDeContatos.add(objetoContato);
      }

      rs.close();
      ps.close();
      con.close();

      return listaDeContatos;

    }catch(Exception erro){
      throw erro;
    }
  }

  @Override
  public Contato buscarID(int idContato) throws Exception {
    try{
      String sql = "SELECT id, nome, email, telefone FROM contatos WHERE id = ?";

      Connection con = ConexaoPostgre.getConexao();
      PreparedStatement ps = con.prepareStatement(sql);

      ps.setInt(1, idContato);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        int idContatoAux = rs.getInt("id");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String telefoneStr = rs.getString("telefone");

        Telefone telefone = Telefone.fromFormattedString(telefoneStr);

        Contato objContato = null;
        objContato = new Contato(idContatoAux, nome, email, telefone);

        rs.close();
        ps.close();
        con.close();

        return objContato;
      }

      rs.close();
      ps.close();
      con.close();

      return null;

    }catch(Exception erro){
      String msg = "Persistencia - Metodo Buscar - "+erro.getMessage();
      throw new Exception(msg);
    }
  }
}
