package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
import fr.eql.ai109.projet3.entity.Utilisateur;
/*
 * Etat final, soit annulation prématurée, soit fin normale d'une prestation
 */
public class SignatureContrat implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final SignatureContrat SIGNATURECONTRAT = new SignatureContrat();
	
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
		p.setStateString("ENATTENTE");
	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("signatureContrat.xhtml");
	}
	/*
	@Override
	public void valideAvecDate(PrestationBU p, LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void valide(PrestationBU p, Utilisateur utilisateur) {
		Prestation presta = p.getPrestation();
		if( p.getClient() == utilisateur )
			presta.setContratClient(LocalDateTime.now());
		
		if( p.getEleveur() == utilisateur )
			presta.setContratEleveur(LocalDateTime.now());
		
		//if( p.getBerger() == utilisateur )
		//	presta.setContratBerger(LocalDateTime.now());
	}
}
