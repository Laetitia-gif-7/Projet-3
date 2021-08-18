package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

public class AttenteBerger implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final AttenteBerger ATTENTEBERGER = new AttenteBerger();
	
	@Override
	public void valide(PrestationBU p) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void valide(PrestationBU p, Utilisateur utilisateur) {}

	@Override
	public void valideAvecDate(PrestationBU p, Utilisateur utilisateur, LocalDateTime date) {}

	@Override
	public void annule(PrestationBU p) {
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("ENATTENTE");

	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("attenteBerger.xhtml");
	}

	

}
