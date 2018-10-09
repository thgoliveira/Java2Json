package br.com.cast.java2json.servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.cast.java2json.business.PessoaBusiness;
import br.com.cast.java2json.dto.PessoaDto;
import br.com.cast.java2json.util.RespostaJSON;

/**
 * Servlet implementation class CadastrarPessoaServlet
 */
@WebServlet("/pessoa")
public class CadastrarPessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = request.getReader().lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		PessoaDto pessoaDto = gson.fromJson(json, PessoaDto.class);
		
		RespostaJSON resp = new RespostaJSON();
		
		try {
			PessoaBusiness pb = new PessoaBusiness();
			pb.salvar(pessoaDto);
			resp.addInfo("Registro inserido com sucesso!");
		} catch (Exception e) {
			resp.addErro(e.getMessage());
		}
		
		toJson(response, gson.toJson(resp));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PessoaBusiness pb = new PessoaBusiness();
		List<PessoaDto> listarPessoasDto = pb.buscarTodos();
		
		Gson gson = new Gson();
		String json = gson.toJson(listarPessoasDto);
		
		toJson(resp, json);
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = request.getReader().lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		PessoaDto pessoaDto = gson.fromJson(json, PessoaDto.class);
		
		RespostaJSON resp = new RespostaJSON();
		
		try {
			PessoaBusiness pb = new PessoaBusiness();
			pb.salvar(pessoaDto);
			resp.addInfo("Registro alterado com sucesso!");
		} catch (Exception e) {
			resp.addErro(e.getMessage());
		}
		
		toJson(response, gson.toJson(resp));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String json = req.getReader().lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		PessoaDto pessoaDto = gson.fromJson(json, PessoaDto.class);
		
		RespostaJSON resposta = new RespostaJSON();
		String cpf = req.getParameter("cpf");
		
		try {
			PessoaBusiness pb = new PessoaBusiness();
			pb.excluir(cpf);
			resposta.addInfo("Registro removido com sucesso!");
		} catch (Exception e) {
			resposta.addErro(e.getMessage());
		}
		
		toJson(resp, gson.toJson(resposta));
		
	}
	
	private void toJson(HttpServletResponse resp, String json) throws IOException{
		resp.setContentType("application/json");
		resp.getWriter().append(json);
	}
		
}
