package br.com.projetoorientadoobjeto.main;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import br.com.projetoorientadoobjeto.dao.PessoaDao;
import br.com.projetoorientadoobjeto.modelo.Pessoa;

public class ApiServer {

	private static final PessoaDao pessoaDao = new PessoaDao();

	public static void main(String[] args) throws IOException {
		int porta = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
		HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);

		server.createContext("/api/pessoas", (HttpExchange exchange) -> {
			// Libera CORS para o Front-end React
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

			String metodo = exchange.getRequestMethod();

			if ("OPTIONS".equalsIgnoreCase(metodo)) {
				exchange.sendResponseHeaders(204, -1);
				return;
			}

			try {
				if ("GET".equalsIgnoreCase(metodo)) {
					List<Object> pessoas = pessoaDao.listar();
					String json = converterListaParaJson(pessoas);
					enviarResposta(exchange, 200, json);

				} else if ("POST".equalsIgnoreCase(metodo)) {
					String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

					Pessoa p = new Pessoa();
					p.setCpf(extrairTextoJson(body, "cpf"));
					p.setNome(extrairTextoJson(body, "nome"));
					p.setdtNascimento(extrairTextoJson(body, "dtNascimento"));
					p.setEmail(extrairTextoJson(body, "email"));
					p.setWpp(extrairTextoJson(body, "wpp"));
					p.setGenero(extrairIntJson(body, "genero"));
					p.settipoCadastro(extrairIntJson(body, "tipoCadastro"));
					p.setFoto(extrairTextoJson(body, "foto"));

					pessoaDao.salvar(p);
					enviarResposta(exchange, 201, "{\"status\":\"ok\",\"mensagem\":\"Salvo no Supabase\"}");

				} else if ("PUT".equalsIgnoreCase(metodo)) {
					String query = exchange.getRequestURI().getQuery();
					if (query != null && query.startsWith("id=")) {
						int id = Integer.parseInt(query.replace("id=", ""));
						String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

						Pessoa p = new Pessoa();
						p.setCpf(extrairTextoJson(body, "cpf"));
						p.setNome(extrairTextoJson(body, "nome"));
						p.setdtNascimento(extrairTextoJson(body, "dtNascimento"));
						p.setEmail(extrairTextoJson(body, "email"));
						p.setWpp(extrairTextoJson(body, "wpp"));
						p.setGenero(extrairIntJson(body, "genero"));
						p.settipoCadastro(extrairIntJson(body, "tipoCadastro"));
						p.setFoto(extrairTextoJson(body, "foto"));

						pessoaDao.editar(p, id);
					}
					enviarResposta(exchange, 200, "{\"status\":\"ok\",\"mensagem\":\"Atualizado no Supabase\"}");

				} else if ("DELETE".equalsIgnoreCase(metodo)) {
					String query = exchange.getRequestURI().getQuery();
					if (query != null && query.startsWith("id=")) {
						int id = Integer.parseInt(query.replace("id=", ""));
						pessoaDao.excluir(id);
					}
					enviarResposta(exchange, 200, "{\"status\":\"ok\",\"mensagem\":\"Excluido\"}");
				}
			} catch (Exception e) {
				e.printStackTrace();
				enviarResposta(exchange, 500, "{\"erro\":\"" + escaparJson(e.getMessage()) + "\"}");
			}
		});

		server.setExecutor(null);
		server.start();
		System.out.println("Servidor API Java rodando na porta " + porta);
	}

	private static void enviarResposta(HttpExchange exchange, int status, String json) throws IOException {
		byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
		exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
		exchange.sendResponseHeaders(status, bytes.length);
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(bytes);
		}
	}

	private static String converterListaParaJson(List<Object> lista) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < lista.size(); i++) {
			Pessoa p = (Pessoa) lista.get(i);
			sb.append("{").append("\"id\":").append(p.getPessoa()).append(",").append("\"cpf\":\"")
					.append(escaparJson(p.getCpf())).append("\",").append("\"nome\":\"")
					.append(escaparJson(p.getNome())).append("\",").append("\"dtNascimento\":\"")
					.append(escaparJson(p.getDtNascimento())).append("\",").append("\"email\":\"")
					.append(escaparJson(p.getEmail())).append("\",").append("\"wpp\":\"")
					.append(escaparJson(p.getWpp())).append("\",").append("\"genero\":").append(p.getGenero())
					.append(",").append("\"tipoCadastro\":").append(p.getTipoCadastro()).append(",")
					.append("\"foto\":\"").append(escaparJson(p.getFoto())).append("\"").append("}");
			if (i < lista.size() - 1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	private static String extrairTextoJson(String json, String chave) {
		Pattern pattern = Pattern.compile("\"" + chave + "\"\\s*:\\s*\"(.*?)\"(?=,|\\s*\\})", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(json);
		return matcher.find() ? matcher.group(1) : "";
	}

	private static int extrairIntJson(String json, String chave) {
		Pattern pattern = Pattern.compile("\"" + chave + "\"\\s*:\\s*(\\d+)");
		Matcher matcher = pattern.matcher(json);
		return matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
	}

	private static String escaparJson(String texto) {
		if (texto == null)
			return "";
		return texto.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "").replace("\r", "");
	}
}