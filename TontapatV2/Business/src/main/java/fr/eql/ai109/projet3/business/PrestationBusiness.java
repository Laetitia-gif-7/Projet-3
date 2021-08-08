package fr.eql.ai109.projet3.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;
// import fr.eql.ai109.projet3.business.helpers.prestation.*; not need anymore after Factory

import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.idao.PrestationIDao;

@Remote(PrestationIBusiness.class)
@Stateless // Statefull maybe here ??  
public class PrestationBusiness implements PrestationIBusiness {
	
	Map<Integer, PrestationBU> prestationsBU = new HashMap<>();
	
	@EJB
    private FactoryPrestrestationBU factoryPrestaBu;
	
	@EJB
	PrestationIDao prestationIdao;
	
	@PostConstruct
	void init() {
		System.out.println("init PrestationBusiness");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy PrestationIBusiness");
	}
	
	@Override
	public Map<Integer,PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur) {
		List<Prestation> prestations = prestationIdao.getPrestationsByUser(utilisateur);
		Map<Integer,PrestationBU> prestationsBu = new HashMap<>();
		
		for (Prestation prestation : prestations) {
			System.out.println("test: " + prestation.toString());
			prestationsBu.put(
					prestation.getIdPrestation(),
					factoryPrestaBu.createPrestationBU(prestation, utilisateur ) );
		}
		return prestationsBu;
	}

	@Override
	public PrestationBU valide(PrestationBU prestaBu) {
		System.out.println("etat de presta : " + prestaBu.getStateString());
		prestaBu.valide();
		// only way to save in db, it is from here
		prestationIdao.update(prestaBu.getPrestation());
		
		// date have been included and state changed
		return prestaBu;
	}

	@Override
	public PrestationBU annule(int id) {
		// TODO To implement
		return new PrestationBU();
	}
}
		
	

