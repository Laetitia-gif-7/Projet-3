package fr.eql.ai109.projet3.entity.prestationstate;

import fr.eql.ai109.projet3.entity.PrestationBU;
/*
 * Etat initial si le client a fait la réservation :
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut confirmer la réservation => ConfirméParEleveur
 */
public class ReserveParClient implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final ReserveParClient RESERVEPARCLIENT = new ReserveParClient();
	
	@Override
	public void valide(PrestationBU p) {
		// must set the date confirmation in prestation entity => save db with update here ?
		// change state of the prestation, here or in PrestationExt ?
	}

	@Override
	public void annule(PrestationBU p) {
		// p.
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("RESERVEPARCLIENT");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("reserveParClient.xhtml");
	}
	
	

}
