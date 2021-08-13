package fr.eql.ai109.projet3.business.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;

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
		p.setStateString("RESERVEPARELEVEUR");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("reserveParEleveur.xhtml");
	}

	/*
	@Override
	public void valideAvecDate(PrestationBU p, LocalDateTime date) {
		// TODO Auto-generated method stub
	}*/
}
