package fr.eql.ai109.projet3.business;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import fr.eql.ai109.projet3.business.helpers.prestation.Annule;
import fr.eql.ai109.projet3.business.helpers.prestation.ConfirmeParEleveur;
import fr.eql.ai109.projet3.business.helpers.prestation.ReserveParClient;
import fr.eql.ai109.projet3.business.helpers.prestation.ReserveParEleveur;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;

// full concurrent access
@ConcurrencyManagement(ConcurrencyManagementType.BEAN) 
@Startup
@Singleton
public class FactoryPrestrestationBU {

	@PostConstruct
    public void postConstruct() {
		System.out.println("post construct Factory");
	}
	
	@PreDestroy
    public void avantDeMourir() {
		System.out.println("preDestroy Factory");
	}
	
	// TODO Factory Class, ApplicatioScope              // Utilisateur not used yet
	public PrestationBU createPrestationBU( Prestation prestation, Utilisateur utilisateur ) {
		
		PrestationBU proxy = new PrestationBU( prestation );
		
		int utilisateurInitiateurId = prestation.getInitiateurPrestation().getId();
		// 
		int clientId = prestation.getTerrain().getUtilisateur().getId();
		//int eleveurId = prestation.getTroupeau().getUtilisateur().getId();
		int eleveurId = prestation.getCompositionTroupeauPrestations().get(0).getTroupeau().getUtilisateur().getId();
		
		// insert into the object for the view
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
		
		// very bad name ?? Use it like a validation from the initiateur
		if( prestation.getAcceptationEleveur() == null) {
			
			if( utilisateurInitiateurId == clientId ) {
				proxy.setState( ConfirmeParEleveur.CONFIRMEPARELEVEUR );
			// TODO continue the logic
			} else {
				proxy.setState(null);
			}
			return proxy;
		}
		
		return proxy;
	}
}
