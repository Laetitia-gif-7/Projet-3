package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.io.Serializable;
import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;

/*
 * Etat 2A si le client a fait la réservation, l'eleveur a confirmé :
 * - le client peut valider la réservation => ValidePrestation
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut rien faire de plus ... ?
 */
public class ConfirmeParEleveur implements StatePrestation, Serializable  {

	private static final long serialVersionUID = 1L;
	
	public static final ConfirmeParEleveur CONFIRMEPARELEVEUR = new ConfirmeParEleveur();
	
	@Override
	public void valide(PrestationBU p) {
		Prestation prestation = p.getPrestation();
		prestation.setPremiereVisiteAccepte(LocalDateTime.now());
		p.setState(DateEtatDesLieuxValide.DATEETATSDESLIEUXVALIDE);   
	}

	@Override
	public void annule(PrestationBU p) {
		
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("CONFIRMEPARELEVEUR");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("confirmeParEleveur.xhtml");
	}

	/*
	@Override
	public void valideAvecDate(PrestationBU p, LocalDateTime date) {
		Prestation prestation = p.getPrestation();
		prestation.setAcceptationEleveur(LocalDateTime.now());
		p.setState(ConfirmeParEleveur.CONFIRMEPARELEVEUR);
		p.getPrestation().setAcceptationEleveur(date);
		
		p.setState(ConfirmeParEleveur.CONFIRMEPARELEVEUR);
		
	}*/
}
