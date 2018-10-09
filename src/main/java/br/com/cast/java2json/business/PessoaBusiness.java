package br.com.cast.java2json.business;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.java2json.dto.EnderecoDto;
import br.com.cast.java2json.dto.PessoaDto;
import br.com.cast.java2json.entidade.Endereco;
import br.com.cast.java2json.entidade.Pessoa;
import br.com.cast.java2json.persistencia.PessoaDao;

public class PessoaBusiness {
	
	PessoaDao pdao;

	public PessoaBusiness() {
		this.pdao = new PessoaDao();
	}
	
	public void salvar(PessoaDto pessoaDto) throws Exception {
		
		Pessoa pessoa = converterParaEntidade(pessoaDto);
		
		if(pessoa.getCpf() != null) {
			pdao.alterar(pessoa);
		} else {
			pdao.inserir(pessoa);
		}
		
	}


	public List<PessoaDto> buscarTodos() {
		
		List<Pessoa> pessoas = pdao.buscarPessoas();
		
		List<PessoaDto> pessoasDto = new ArrayList<>();
		
		for (Pessoa pessoa : pessoas) {
			PessoaDto pessoaDto = converterParaDto(pessoa);
			pessoasDto.add(pessoaDto);
		}
		
		return pessoasDto;
	}

	
	public void excluir(String cpf) {
		Pessoa pessoa = pdao.buscarPorCpf(cpf);
		pdao.excluir(pessoa);
	}
	
	
	private Pessoa converterParaEntidade(PessoaDto pessoaDto) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setEmail(pessoaDto.getEmail());
		
		Endereco end = new Endereco();
		end.setCep(pessoaDto.getEnderecoDto().getCep());
		end.setBairro(pessoaDto.getEnderecoDto().getBairro());
		end.setCidade(pessoaDto.getEnderecoDto().getCidade());
		end.setComplemento(pessoaDto.getEnderecoDto().getComplemento());
		end.setLogradouro(pessoaDto.getEnderecoDto().getLogradouro());
		end.setNumero(pessoaDto.getEnderecoDto().getNumero());
		end.setUf(pessoaDto.getEnderecoDto().getUf());
		
		pessoa.setEndereco(end);
		return pessoa;
	}
	
	private PessoaDto converterParaDto(Pessoa pessoa) {
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto.setCpf(pessoa.getCpf());
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setEmail(pessoa.getEmail());
		
		EnderecoDto endDto = new EnderecoDto();
		endDto.setCep(pessoa.getEndereco().getCep());
		endDto.setLogradouro(pessoa.getEndereco().getLogradouro());
		endDto.setNumero(pessoa.getEndereco().getNumero());
		endDto.setComplemento(pessoa.getEndereco().getComplemento());
		endDto.setBairro(pessoa.getEndereco().getBairro());
		endDto.setCidade(pessoa.getEndereco().getCidade());
		endDto.setUf(pessoa.getEndereco().getUf());
		
		pessoaDto.setEnderecoDto(endDto);
		return pessoaDto;
	}


}
