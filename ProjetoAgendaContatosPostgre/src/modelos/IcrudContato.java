/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelos;
import java.util.ArrayList;
//Revisado
public interface IcrudContato{
    void incluir(Contato objeto) throws Exception;
    void excluir(int idContato) throws Exception;
    void alterar(Contato objeto) throws Exception;
    Contato consultar(String nomeCompleto) throws Exception;
    Contato buscarID(int idContato) throws Exception;
    ArrayList<Contato> listagemDeContatos() throws Exception;
}
