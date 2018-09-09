package metier;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.validator.constraints.br.CPF;

import metier.entities.Compte;

/**
 * Session Bean implementation class Banque
 */
@Stateless(name="BK")
@LocalBean
public class Banque implements BanqueRemote, BanqueLocal {

	@PersistenceContext(unitName="BanqueEJB")
	private EntityManager em;

	@Override
	public Compte addCompte(Compte cp) {
		em.persist(cp);
		return cp;
	}

	@Override
	public Compte getCompte(Long code) {
		Compte cp = em.find(Compte.class, code);
		if(cp == null) throw new RuntimeException("Compte introuvable !!");
		return cp;
	}

	@Override
	public List<Compte> listCompte() {
		Query req=em.createQuery("select c from Compte c");
		return req.getResultList();
	}

	@Override
	public void verser(Long code, double mt) {
		Compte cp = getCompte(code);
		cp.setSolde(cp.getSolde()+mt);
	}

	@Override
	public void retirer(Long code, double mt) {
		Compte cp = getCompte(code);
		if(mt>cp.getSolde()) throw new RuntimeException("Solde inssufisant !!");
		cp.setSolde(cp.getSolde()-mt);
	}

	@Override
	public void virement(Long cp1, Long cpt2, double mt) {
		retirer(cp1, mt);
		verser(cpt2, mt);
		
	}

}
