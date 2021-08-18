package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

/*
 * Etat initial si le client a fait la réservation :
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut confirmer la réservation => ConfirméParEleveur
 */

public class EnCours implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final EnCours ENCOURS = new EnCours();
	
	@Override
	public void valide(PrestationBU p) {
		//  set the confirmation date in prestation entity => save db with update here or in business 
		// entity will be updated in PrestationBusiness, not need dao access here (and can move later to Entity)
		Prestation prestation = p.getPrestation();
		prestation.setConfirmation(LocalDateTime.now());
		p.setState(EnCours.ENCOURS);
	}

	@Override
	public void annule(PrestationBU p) {
		// p.
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("ENCOURS");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("enCours.xhtml");
	}
	/*
	@Override
	public void valideAvecDate(PrestationBU p, LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void valide(PrestationBU p, Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valideAvecDate(PrestationBU p, Utilisateur utilisateur, LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}
}
