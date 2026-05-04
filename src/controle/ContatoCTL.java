/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;
import java.util.ArrayList;
import modelos.*;
import persistencia.ContatoDAO;

public class ContatoCTL implements IcrudContato{
  ContatoDAO persistenciaContatoDAO = null;

  public ContatoCTL() {
    persistenciaContatoDAO = new ContatoDAO();
  }
  
  private void verificarDadosContato(Contato objeto) throws Exception{
    String resposta = "";

    if(objeto.getIdContato() <= 0) 
      resposta += "\nValor do identificador não pode ser <= 0";

    // NOME (evita NullPointer)
    String nome = objeto.getNomeCompleto();
    if(nome == null || nome.trim().isEmpty())
      resposta += "\nNome esta vazio";

    // EMAIL (evita NullPointer)
    String email = objeto.getEmail();
    if(email == null || email.trim().isEmpty()) {
      resposta += "\nEmail esta vazio";
    } else {
      email = email.trim();
      // validação simples (do jeito que você quer, sem regex)
      if (!email.contains("@") || !email.contains(".")) {
        resposta += "\nEmail inválido";
      }
    }

    // TELEFONE (obrigatório)
    if (objeto.getTelefone() == null) {
      resposta += "\nTelefone esta vazio";
    }

    if(!resposta.isEmpty()) throw new Exception(resposta);
  }

  @Override
  public void incluir(Contato objeto) throws Exception {
    try {
      verificarDadosContato(objeto);

      // impede ID duplicado
      if(buscarID(objeto.getIdContato())!=null)
        throw new Exception("Identificador Já Existe");

      persistenciaContatoDAO.incluir(objeto);
    } catch (Exception erro) {
      throw new Exception("Controle - Incluir " + erro.getMessage());
    }
  }

  @Override
  public void excluir(int idContato) throws Exception {
    try {
      persistenciaContatoDAO.excluir(idContato);
    } catch (Exception erro) {
      throw new Exception("Controle - Excluir " + erro.getMessage());
    }
  }

  @Override
  public void alterar(Contato objeto) throws Exception {
    try {
      verificarDadosContato(objeto);

      if (buscarID(objeto.getIdContato()) == null) {
        throw new Exception("Contato não encontrado para alterar.");
      }

      persistenciaContatoDAO.alterar(objeto);
    } catch (Exception erro) {
      throw new Exception("Controle - Alterar " + erro.getMessage());
    }
  }

  @Override
  public Contato consultar(String nomeCompleto) throws Exception {
    try {
      if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
        throw new Exception("Nome esta vazio");
      }
      return persistenciaContatoDAO.consultar(nomeCompleto.trim());
    } catch (Exception erro) {
      throw new Exception("Controle - Consultar " + erro.getMessage());
    }
  }

  @Override
  public Contato buscarID(int idContato) throws Exception {
    return persistenciaContatoDAO.buscarID(idContato);  
  }

  @Override
  public ArrayList<Contato> listagemDeContatos() throws Exception {
    return persistenciaContatoDAO.listagemDeContatos();
  }
}
