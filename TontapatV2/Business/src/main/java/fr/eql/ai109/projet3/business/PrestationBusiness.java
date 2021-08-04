package fr.eql.ai109.projet3.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
//import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationExt;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.idao.PrestationIDao;

// @Local(PrestationIBusiness.class)

@Remote(PrestationIBusiness.class)
@Stateless // Statefull maybe here
public class PrestationBusiness implements PrestationIBusiness {
	
	// as attribute, 
	List<PrestationExt> prestationsExt = new ArrayList<>();
	
	@EJB
	PrestationIDao prestationIdao;
	
	@PostConstruct
	void init() {
		System.out.println("init PrestationIBusiness");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy PrestationIBusiness");
	}
	
	@Override
	public List<PrestationExt> findPrestationsByUtilisateur(Utilisateur utilisateur) {
		List<Prestation> prestations = prestationIdao.getPrestationsByUser(utilisateur);
		List<PrestationExt> prestationsExt = new ArrayList<>();
		
		PrestationExt temp;
		for (Prestation prestation : prestations) {
			//prestation.getTerrain(); // ok terrain loaded .....
			System.out.println("test: " + prestation.toString());
			
			temp = new PrestationExt( prestation );
			temp.testPrint();
			prestationsExt.add( temp ); // new PrestationExt( prestation ) );
		}
		//System.out.println("test: " + prestations.toString());
		return prestationsExt;
	}

	@Override
	public void valide(int id) {
		return;
	}

	@Override
	public void cancel(int id) {
		// TODO Auto-generated method stub	
	}

}
