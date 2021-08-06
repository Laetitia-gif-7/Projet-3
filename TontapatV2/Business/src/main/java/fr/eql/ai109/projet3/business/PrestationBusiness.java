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
import fr.eql.ai109.projet3.entity.prestationstate.Annule;
import fr.eql.ai109.projet3.entity.prestationstate.ConfirmeParEleveur;
import fr.eql.ai109.projet3.entity.prestationstate.ReserveParClient;
import fr.eql.ai109.projet3.entity.prestationstate.ReserveParEleveur;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.idao.PrestationIDao;

// @Local(PrestationIBusiness.class)

@Remote(PrestationIBusiness.class)
@Stateless // Statefull maybe here
public class PrestationBusiness implements PrestationIBusiness {
	
	// as attribute, 
	List<PrestationBU> prestationsBU = new ArrayList<>();
	
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
	public List<PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur) {
		List<Prestation> prestations = prestationIdao.getPrestationsByUser(utilisateur);
		List<PrestationBU> prestationsBu = new ArrayList<>();
		
		PrestationBU temp;
		for (Prestation prestation : prestations) {
			//prestation.getTerrain(); // ok terrain loaded .....
			System.out.println("test: " + prestation.toString());
			/*
			temp = new PrestationExt( prestation );
			temp.testPrint();
			prestationsExt.add( temp ); // new PrestationExt( prestation ) );
			*/
			prestationsBu.add( this.factoryMethod(prestation, utilisateur ));
		}
		//System.out.println("test: " + prestations.toString());
		return prestationsBu;
	}

	@Override
	public void valide(int id) {
		// findPrestationId_> PrestationExt.valide()
		return;
	}

	@Override
	public void cancel(int id) {
		// TODO Auto-generated method stub	
	}
	
	// TODO Factory Class, ApplicatioScope              // Utilisateur not used
	PrestationBU factoryMethod( Prestation prestation, Utilisateur utilisateur ) {
		
		PrestationBU proxy = new PrestationBU( prestation );
		
		int utilisateurInitiateurId = prestation.getInitiateurPrestation().getId();
		// 
		int clientId = prestation.getTerrain().getUtilisateur().getId();
		//int eleveurId = prestation.getTroupeau().getUtilisateur().getId();
		int eleveurId = prestation.getCompositionTroupeauPrestations().get(0).getTroupeau().getUtilisateur().getId();
		
		// insert into the object
		proxy.setClient(prestation.getTerrain().getUtilisateur());
		proxy.setEleveur(prestation.getCompositionTroupeauPrestations().get(0).getTroupeau().getUtilisateur());
		
		//if( prestation.getReservation() == null )
		//	throw new Exception("Error in prestation Reservation is null");
		
		// ANNULATION
		if( prestation.getAnnullationPrestation() != null ) {
			proxy.setState( Annule.ANNULE );
			return proxy;
		}
		
		if( prestation.getConfirmation() == null ) { // en attente de confirmation
			
			if( utilisateurInitiateurId == clientId ) { // un client
				proxy.setState(  ReserveParClient.RESERVEPARCLIENT );
			}	else { // un eleveur
				proxy.setState( ReserveParEleveur.RESERVEPARELEVEUR );
			}
			return proxy;
		}
		//proxy.setStateString();
		return proxy;
	}
}
		
	

