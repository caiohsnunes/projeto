package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Pessoa;

public class PessoaDAO {
	private static PessoaDAO instance;
	protected EntityManager entityManager;

	public static PessoaDAO getInstance() {
		if (instance == null) {
			instance = new PessoaDAO();
		}

		return instance;
	}

	private PessoaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Pessoa getById(final int id) {
		return entityManager.find(Pessoa.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findAll() {
		return entityManager.createQuery("Select c from Pessoa c").getResultList();
	}

	public void persist(Pessoa p) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(p);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Pessoa p) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(p);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Pessoa p) {
		try {
			entityManager.getTransaction().begin();
			p = entityManager.find(Pessoa.class, p.getCodigo());
			entityManager.remove(p);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Pessoa p = getById(id);
			remove(p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
