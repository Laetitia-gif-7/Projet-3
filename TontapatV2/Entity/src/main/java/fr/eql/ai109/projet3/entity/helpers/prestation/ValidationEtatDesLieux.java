package fr.eql.ai109.projet3.entity.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

public class ValidationEtatDesLieux implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final ValidationEtatDesLieux VALIDATIONETATDESLIEUX = new ValidationEtatDesLieux();
	
	@Override
	public void valide(PrestationBU p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void annule(PrestationBU p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setStateName(PrestationBU p) {
		p.setStateString("ENATTENTE");

	}

	@Override
	public void setTemplateString(PrestationBU p) {
		p.setTemplateXhtml("validationEtatDesLieux.xhtml");
	}

	@Override
	public void valide(PrestationBU p, Utilisateur utilisateur) {
		Prestation presta = p.getPrestation();
		if( p.getClient().getId() == utilisateur.getId() )
			presta.setEtatLieuClient(LocalDateTime.now());
		
		if( p.getEleveur().getId() == utilisateur.getId() )
			presta.setEtatLieuEleveur(LocalDateTime.now());
		
		if( presta.isBesoinBerger() )
			if ( p.getBerger().getId() == utilisateur.getId() )
				presta.setEtatLieuBerger(LocalDateTime.now());
	}

	@Override
	public void valideAvecDate(PrestationBU p, Utilisateur utilisateur, LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}

}
