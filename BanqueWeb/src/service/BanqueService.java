package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.BanqueLocal;
import metier.entities.Compte;

@WebService
public class BanqueService {

	@EJB
	private BanqueLocal metier;

	@WebMethod
	public Compte addCompte(
			@WebParam(name="solde") double solde) {
		Compte cp = new Compte();
		cp.setSolde(solde);
		cp.setDateCreation(new Date());
		return metier.addCompte(cp);
	}

	@WebMethod
	public Compte getCompte(@WebParam(name="code") Long code) {
		return metier.getCompte(code);
	}

	@WebMethod
	public List<Compte> listCompte() {
		return metier.listCompte();
	}

	@WebMethod
	public void verser(
			@WebParam(name="code")Long code, 
			@WebParam(name="montant") double mt) {
		metier.verser(code, mt);
	}

	@WebMethod
	public void retirer(
			@WebParam(name="code")Long code, 
			@WebParam(name="montant") double mt) {
		metier.retirer(code, mt);
	}

	@WebMethod
	public void virement(
			@WebParam(name="compte1")Long cp1, 
			@WebParam(name="compte1")Long cpt2, 
			@WebParam(name="montant")double mt) {
		metier.virement(cp1, cpt2, mt);
	}
	
	
	
}
