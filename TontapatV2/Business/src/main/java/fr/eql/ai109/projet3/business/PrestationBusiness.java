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
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.business.helpers.prestation.*;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.idao.PrestationIDao;

// @Local(PrestationIBusiness.class)

@Remote(PrestationIBusiness.class)
@Stateless // Statefull maybe here
public class PrestationBusiness implements PrestationIBusiness {
	
	// as attribute, 
	List<PrestationBU> prestationsBU = new ArrayList<>();
	
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
	public List<PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur) {
		List<Prestation> prestations = prestationIdao.getPrestationsByUser(utilisateur);
		List<PrestationBU> prestationsBu = new ArrayList<>();
		
		//PrestationBU temp;
		for (Prestation prestation : prestations) {
			//prestation.getTerrain(); // ok terrain loaded .....
			System.out.println("test: " + prestation.toString());
			/*
			temp = new PrestationExt( prestation );
			temp.testPrint();
			prestationsExt.add( temp ); // new PrestationExt( prestation ) );
			*/
			prestationsBu.add( factoryPrestaBu.createPrestationBU(prestation, utilisateur ) );
		}
		//System.out.println("test: " + prestations.toString());
		return prestationsBu;
	}

	@Override
	public PrestationBU valide(PrestationBU prestaBu) {
		System.out.println("etat de presta : " + prestaBu.getStateString());
		prestaBu.valide();
		// date have been included and state changed
		return prestaBu;
	}

	@Override
	public PrestationBU annule(int id) {
		// TODO Auto-generated method stub
		return new PrestationBU();
	}
}
		
	

