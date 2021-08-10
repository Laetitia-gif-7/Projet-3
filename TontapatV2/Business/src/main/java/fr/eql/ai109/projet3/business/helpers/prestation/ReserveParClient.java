package fr.eql.ai109.projet3.business.helpers.prestation;

import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;

/*
 * Etat initial si le client a fait la réservation :
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut confirmer la réservation => ConfirméParEleveur
 */

public class ReserveParClient implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final ReserveParClient RESERVEPARCLIENT = new ReserveParClient();
	
	//@EJB
	//PrestationIDao prestationIdao;
	
	@Override
	public void valide(PrestationBU p) {
		/* keep tests for reminder
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PUTontapatV2");
		EntityManager em = emf.createEntityManager();
		prestationIdao.update( prestation );
		em.merge(prestation);
		prestationIdao.myUpdate( prestation );
		*/
		
		//  set the confirmation date in prestation entity => save db with update here or in business 
		// entity will be updated in PrestationBusiness, not need dao access here (and can move later to Entity)
		Prestation prestation = p.getPrestation();
		prestation.setConfirmation(LocalDateTime.now());
		p.setState(ConfirmeParEleveur.CONFIRMEPARELEVEUR);
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
