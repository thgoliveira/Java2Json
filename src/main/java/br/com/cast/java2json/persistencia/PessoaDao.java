package br.com.cast.java2json.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.cast.java2json.entidade.Pessoa;

public class PessoaDao {
	
	private EntityManager em;

	public PessoaDao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("puJava2Json");
		em = emf.createEntityManager();
	}
	
	public void inserir(Pessoa pessoa) {
		
		try {
			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	public List<Pessoa> buscarPessoas(){
		StringBuilder jpql = new StringBuilder();
		jpql.append("select p ").append("from ").append(Pessoa.class.getName()).append(" p ")
				.append("join fetch p.endereco ");

		Query query = em.createQuery(jpql.toString());
		return query.getResultList();
	}

	public void alterar(Pessoa pessoa) {
		try {
			em.getTransaction().begin();
			em.merge(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void excluir(Pessoa pessoa) {
		try {
			em.getTransaction().begin();
			em.remove(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	public Pessoa buscarPorCpf(String cpf) {
		return em.find(Pessoa.class, cpf);
	}
	
}
