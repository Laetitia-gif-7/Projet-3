package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
/*
 * Etat final, soit annulation prématurée, soit fin normale d'une prestation
 */
public class DateEtatDesLieuxValide implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final DateEtatDesLieuxValide DATEETATSDESLIEUXVALIDE = new DateEtatDesLieuxValide();
	
	@Override
	public void valide(PrestationBU p) {
		// must set the date confirmation in prestation entity => save db with update here ?
		// change state of the prestation, here or in PrestationExt ?
	}

	@Override
	public void annule(PrestationBU p) {
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("ANNULER");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("annule.xhtml");
		
	}
	/*
	@Override
	public void valideAvecDate(PrestationBU p, LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}*/
}
