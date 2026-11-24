package br.com.projetoorientadoobjeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projetoorientadoobjeto.modelo.Pessoa;

public class PessoaDao implements DAO {

	@Override
	public void salvar(Object objeto) throws SQLException {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO pessoa(cpf, nome, \"dtNascimento\", email, "
				+ "wpp, genero, \"tipoCadastro\", foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Pessoa pessoa = (Pessoa) objeto;

		try {
			conexao = ConexaoMySQL.abriConexao();
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, pessoa.getCpf() != null ? pessoa.getCpf() : "");
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getDtNascimento());
			stmt.setString(4, pessoa.getEmail());
			stmt.setString(5, pessoa.getWpp());
			stmt.setInt(6, pessoa.getGenero());
			stmt.setInt(7, pessoa.getTipoCadastro());
			stmt.setString(8, pessoa.getFoto());

			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getMessage(), sqle);
		} finally {
			ConexaoMySQL.fecharConexao(conexao);
		}
	}

	@Override
	public List<Object> listar() {
		List<Object> lista = new ArrayList<>();
		String sql = "SELECT \"idPessoa\", cpf, nome, \"dtNascimento\", email, wpp, genero, \"tipoCadastro\", foto "
				+ "FROM pessoa ORDER BY \"idPessoa\" DESC";

		try (Connection conexao = ConexaoMySQL.abriConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Pessoa p = new Pessoa();
				p.setidPessoa(rs.getInt("idPessoa"));
				p.setCpf(rs.getString("cpf"));
				p.setNome(rs.getString("nome"));
				p.setdtNascimento(rs.getString("dtNascimento"));
				p.setEmail(rs.getString("email"));
				p.setWpp(rs.getString("wpp"));
				p.setGenero(rs.getInt("genero"));
				p.settipoCadastro(rs.getInt("tipoCadastro"));
				p.setFoto(rs.getString("foto"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void excluir(Integer id) {
		String sql = "DELETE FROM pessoa WHERE \"idPessoa\" = ?";
		try (Connection conexao = ConexaoMySQL.abriConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editar(Object objeto, Integer id) {
		String sql = "UPDATE pessoa SET cpf = ?, nome = ?, \"dtNascimento\" = ?, email = ?, "
				+ "wpp = ?, genero = ?, \"tipoCadastro\" = ?, foto = ? WHERE \"idPessoa\" = ?";
		Pessoa pessoa = (Pessoa) objeto;

		try (Connection conexao = ConexaoMySQL.abriConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, pessoa.getCpf() != null ? pessoa.getCpf() : "");
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getDtNascimento());
			stmt.setString(4, pessoa.getEmail());
			stmt.setString(5, pessoa.getWpp());
			stmt.setInt(6, pessoa.getGenero());
			stmt.setInt(7, pessoa.getTipoCadastro());
			stmt.setString(8, pessoa.getFoto());
			stmt.setInt(9, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object buscarPorId(Integer id) {
		return null;
	}
}