package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

/*
 * Etat initial si l'éleveur fait la réservation :
 * - le client peut annuler la réservation => PrestationAnnule
 * - le client peut confirmer la réservation => ConfirmParclient
 */
public class ReserveParEleveur implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final ReserveParEleveur RESERVEPARELEVEUR = new ReserveParEleveur();
	
	@Override
	public void valide(PrestationBU p) {
	}

	@Override
	public void annule(PrestationBU p) {
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("ENATTENTE");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("reserveParEleveur.xhtml");
	}

	@Override
	public void valide(PrestationBU p, Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valideAvecDate(PrestationBU p, Utilisateur utilisateur, LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}

	/*
	@Override
	public void valideAvecDate(PrestationBU p, LocalDateTime date) {
		// TODO Auto-generated method stub
	}*/
}
