/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
//Revisado
public class Contato {
  //Atributos
  private int idContato = 0;
  private String nomeCompleto = "";
  private String email = "";
  private Telefone telefone;
  //Metodos
  //MEtodos Construtores
  public Contato() {
  }
  public Contato(int idContato, String nomeCompleto, String email, Telefone telefone) {
      this.idContato = idContato;
      this.nomeCompleto = nomeCompleto;
      this.email = email;
      this.telefone = telefone;
  }
  // Metodos set´s e get´s

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
  
  public int getIdContato() {
    return idContato;
  }
  public void setIdContato(int idContato) {
    this.idContato = idContato;
  }
  public String getNomeCompleto() {
      return nomeCompleto;
  }
  public void setNomeCompleto(String nomeCompleto) {
      this.nomeCompleto = nomeCompleto;
  }
  public String getEmail() {
      return email;
  }
  public void setEmail(String email) {
      this.email = email;
  }  
}
