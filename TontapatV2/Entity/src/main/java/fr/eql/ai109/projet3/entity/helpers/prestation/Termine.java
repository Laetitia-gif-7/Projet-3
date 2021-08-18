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

public class Termine implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final Termine TERMINE = new Termine();
	
	@Override
	public void valide(PrestationBU p) {}

	@Override
	public void annule(PrestationBU p) {}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("TERMINE");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("termine.xhtml");
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
