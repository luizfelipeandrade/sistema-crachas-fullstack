
package br.com.projetoorientadoobjeto.controller;
import java.util.HashMap;
import java.util.Map;

public class PessoaController {
    

    public PessoaController(){
}


public Map<String,String> validarCadastro(
        String nome, String dtNasc, String email, String wpp, Integer genero, Integer tipoCadastro){
    Map<String, String> mapErros = new HashMap<String, String>();
    String msgCampoNaoPreechido = "Por favor, preecha o campo.";
    String msgCampoSelecao = "Por favor, preencha uma opção válida.";
    
    if(nome == null || nome.trim().isEmpty()) {
        mapErros.put("CH_NOME", msgCampoNaoPreechido);
    }

    if(dtNasc == null || dtNasc.trim().isEmpty()) {
        mapErros.put("CH_DT_NASC", msgCampoNaoPreechido);
    }

    if(email == null || email.trim().isEmpty()) {
        mapErros.put("CH_EMAIL", msgCampoNaoPreechido);
    }
    
    if(wpp == null || wpp.trim().isEmpty()) {
        mapErros.put("CH_WPP", msgCampoNaoPreechido);
    }
    
    if(genero == 0){
        mapErros.put("CH_GENERO", msgCampoNaoPreechido);
    }
    
    if(tipoCadastro == 0){
        mapErros.put("CH_TIPO_CADASTRO", msgCampoNaoPreechido);
    }
    
return mapErros;

}

}