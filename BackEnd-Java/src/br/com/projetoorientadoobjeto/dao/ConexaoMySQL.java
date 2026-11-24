package br.com.projetoorientadoobjeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

	private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL")
			: "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres?prepareThreshold=0";

	private static final String USUARIO = System.getenv("DB_USER") != null ? System.getenv("DB_USER")
			: "postgres.wiibwmdjqrwlnyfboyqq";

	private static final String SENHA = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD")
			: "SUA_SENHA_AQUI";

	public static Connection abriConexao() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(URL, USUARIO, SENHA);

		} catch (ClassNotFoundException cnfe) {
			throw new SQLException(
					"Driver do PostgreSQL não encontrado (verifique o .jar no Build Path): " + cnfe.getMessage(), cnfe);
		} catch (SQLException sqle) {
			throw new SQLException("Erro ao abrir conexão com o Supabase: " + sqle.getMessage(), sqle);
		} catch (Exception e) {
			throw new SQLException("Erro genérico ao conectar ao banco PostgreSQL", e);
		}
	}

	public static void fecharConexao(Connection conexao) throws SQLException {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao fechar conexão.", e);
		}
	}
}