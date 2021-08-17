package fr.eql.ai109.projet3.business.factories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;


import fr.eql.ai109.projet3.entity.helpers.prestation.Annule;
import fr.eql.ai109.projet3.entity.helpers.prestation.ConfirmeParPartenaire;
import fr.eql.ai109.projet3.entity.helpers.prestation.AttenteBerger;

import fr.eql.ai109.projet3.entity.helpers.prestation.ReserveParClient;
import fr.eql.ai109.projet3.entity.helpers.prestation.ReserveParEleveur;
import fr.eql.ai109.projet3.entity.helpers.prestation.SignatureContrat;
import fr.eql.ai109.projet3.entity.helpers.prestation.ValidationEtatDesLieux;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;

// Stateless
// full concurrent access
// @ConcurrencyManagement(ConcurrencyManagementType.BEAN) 
// @Startup
@Singleton
public class FactoryPrestationBU {

	@PostConstruct
    public void postConstruct() {
		System.out.println("post construct Factory");
	}
	
	@PreDestroy
    public void avantDeMourir() {
		System.out.println("preDestroy Factory");
	}
	
	// Utilisateur not used yet
	public PrestationBU createPrestationBU( Prestation prestation, Utilisateur utilisateur ) {
		
		PrestationBU proxy = new PrestationBU( prestation );
		
		int utilisateurInitiateurId = prestation.getInitiateurPrestation().getId();
		// 
		int clientId = prestation.getTerrain().getUtilisateur().getId();
		// One composition troupeau should always be available
		// assert here to retest
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
		
		// RESERVATION
		if( prestation.getConfirmation() == null ) { // en attente de confirmation
			
			if( utilisateurInitiateurId == clientId ) { // un client
				proxy.setState(  ReserveParClient.RESERVEPARCLIENT );
			}	else { // un eleveur
				proxy.setState( ReserveParEleveur.RESERVEPARELEVEUR );
			}
			return proxy;
		}
		
		// CONFIRME PAR PARTENAIRE
		if(  prestation.getPremiereVisiteAccepte() == null) {
			proxy.setState(ConfirmeParPartenaire.CONFIRMEPARPARTENAIRE);
		}
		
		
		// ACCEPTATION DATE d'état des lieux par l'éleveur
		if( prestation.getPremiereVisiteAccepte() == null) {
			
			if( utilisateurInitiateurId == clientId ) {
				proxy.setState( ConfirmeParPartenaire.CONFIRMEPARPARTENAIRE );
			// TODO continue the logic
			} else {
				proxy.setState(null);
			}
			return proxy;
		}
		// Attente berger
		if( prestation.isBesoinBerger() == true && prestation.getBerger() == null ) {
			proxy.setState(AttenteBerger.ATTENTEBERGER);
			return proxy;
		}
		
		if( prestation.getEtatLieuClient() == null || prestation.getEtatLieuEleveur() == null || 
			( prestation.isBesoinBerger() == true && prestation.getEtatLieuBerger()  == null)) {
			proxy.setState(ValidationEtatDesLieux.VALIDATIONETATDESLIEUX);
			return proxy;
		}
		
		if( prestation.getContratClient() == null || prestation.getContratEleveur() == null || 
			( prestation.isBesoinBerger() == true && prestation.getContratBerger()  == null)	)  {
			proxy.setState(SignatureContrat.SIGNATURECONTRAT);
			return proxy;
		}
		
		// TODO en Cours
		// return default should be an error
		return proxy;
	}
}
