package fr.eql.ai109.projet3.entity.prestationstate;

import java.io.Serializable;

import fr.eql.ai109.projet3.entity.PrestationExt;

/*
 * Etat 2A si le client a fait la réservation, l'eleveur a confirmé :
 * - le client peut valider la réservation => ValidePrestation
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut rien faire... ?
 */
public class ConfirmeParEleveur implements StatePrestation, Serializable  {

	private static final long serialVersionUID = 1L;
	
	public static final ConfirmeParEleveur CONFIRMEPARELEVEUR = new ConfirmeParEleveur();
	
	@Override
	public void valide(PrestationExt p) {
		
	}

	@Override
	public void annule(PrestationExt p) {
		// p.
	}

	@Override
	public void testPrint(PrestationExt p) {
		p.setTestString("CONFIRMEPARELEVEUR");
	}
	
	

}
