/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Barbara
 */
public class Telefone {
    private String ddi;
    private String ddd;
    private String numero;

    public Telefone() {
    }

    public Telefone(String ddi, String ddd, String numero) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public static Telefone fromFormattedString(String texto) {
        if (texto == null) return null;
        texto = texto.trim();
        if (texto.isEmpty() || texto.replace("_", "").trim().isEmpty()) {
            return null;
        }
        texto = texto.replace(" ", "");

        // FORMATAÇÃO PADRÃO DE TELEFONE
        texto = texto.replace("+", "");

        int idxAbre = texto.indexOf('(');
        int idxFecha = texto.indexOf(')');

        if (idxAbre <= 0 || idxFecha < idxAbre) { //IF FORMATO ESTRANHO
            return new Telefone("", "", texto);
        }

        String ddi = texto.substring(0, idxAbre);
        String ddd = texto.substring(idxAbre + 1, idxFecha);
        String numero = texto.substring(idxFecha + 1);
        numero = numero.replace("-", "");

        return new Telefone(ddi, ddd, numero);
    }

    @Override
    public String toString() {
        if (ddi == null || ddd == null || numero == null) {
            return "";
        }

        String numLimpo = numero.replace("-", "");
        String numFormatado;

        switch (numLimpo.length()) {
            case 9:
                numFormatado = numLimpo.substring(0, 5) + "-" + numLimpo.substring(5);
                break;
            case 8:
                numFormatado = numLimpo.substring(0, 4) + "-" + numLimpo.substring(4);
                break;
            default:
                numFormatado = numLimpo;
                break;
        }

        return "+" + ddi + " (" + ddd + ") " + numFormatado;
    }
}
