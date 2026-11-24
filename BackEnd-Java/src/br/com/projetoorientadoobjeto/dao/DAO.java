package br.com.projetoorientadoobjeto.dao;

import java.sql.SQLException;
import java.util.List;



public interface DAO {
    public void salvar(Object objeto) throws SQLException;
    public void editar (Object objeto, Integer id);
    public void excluir (Integer id);
    
    public List<Object> listar();
    
    public Object buscarPorId(Integer id);
}
