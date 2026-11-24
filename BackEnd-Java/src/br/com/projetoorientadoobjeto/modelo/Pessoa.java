package br.com.projetoorientadoobjeto.modelo;


public class Pessoa {
    private Integer idPessoa;
    private String Nome;
    private String Cpf;
    private String Email;
    private String Wpp;
    private String DtNascimento;
    private String Foto;
    private Integer TipoCadastro;
    private Integer Genero;
    
    public Pessoa(){
    }
        
    public Integer getPessoa(){
        return idPessoa;
    }
    
    public void setidPessoa(Integer idPessoa){
        this.idPessoa = idPessoa;
    }
    
    public String getNome(){
        return Nome;
    }
    
    public void setNome(String Nome){
       this.Nome = Nome;
    }
    
    public String getCpf(){
    return Cpf;
}

    public void setCpf(String Cpf){
        this.Cpf = Cpf;
    }
    
    
    public String getEmail(){
    return Email;
}

    public void setEmail(String Email){
        this.Email = Email;
    }
    
    
    public String getWpp(){
    return Wpp;
}

    public void setWpp(String Wpp){
        this.Wpp = Wpp;
    }
    
    
    public String getDtNascimento(){
    return DtNascimento;
}

    public void setdtNascimento(String DtNascimento){
        this.DtNascimento = DtNascimento;
    }
    
    
    public String getFoto(){
    return Foto;
}

    public void setFoto(String Foto){
        this.Foto = Foto;
    }
    
    
    public Integer getTipoCadastro(){
    return TipoCadastro;
}

    public void settipoCadastro(Integer TipoCadastro){
        this.TipoCadastro = TipoCadastro;
    }
    
     public Integer getGenero(){
    return Genero;
}

    public void setGenero(Integer Genero){
        this.Genero = Genero;
    }
    
    
}
