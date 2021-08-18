package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.io.Serializable;
import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

/*
 * Etat 2A si le client a fait la réservation, l'eleveur a confirmé :
 * - le client peut valider la réservation => ValidePrestation
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut rien faire de plus ... ?
 */
public class ConfirmeParPartenaire implements StatePrestation, Serializable  {

	private static final long serialVersionUID = 1L;
	
	public static final ConfirmeParPartenaire CONFIRMEPARPARTENAIRE = new ConfirmeParPartenaire();
	
	@Override
	public void valide(PrestationBU p) {
		//Prestation prestation = p.getPrestation();
		//prestation.setPremiereVisiteAccepte(LocalDateTime.now());
		//p.setState(DateEtatDesLieuxValide.DATEETATSDESLIEUXVALIDE);   
	}
	
	@Override
	public void valide(PrestationBU p, Utilisateur utilisateur) {
		Prestation presta = p.getPrestation();
		presta.setIdDerniereProposition(utilisateur);
		presta.setPremiereVisiteAccepte(LocalDateTime.now());
	}

	@Override
	public void valideAvecDate(PrestationBU p, Utilisateur utilisateur, LocalDateTime date) {
		Prestation presta = p.getPrestation();
		presta.setIdDerniereProposition(utilisateur);
		presta.setPremiereVisitePropose(date);
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
		p.setTemplateXhtml("confirmeParPartenaire.xhtml");
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
