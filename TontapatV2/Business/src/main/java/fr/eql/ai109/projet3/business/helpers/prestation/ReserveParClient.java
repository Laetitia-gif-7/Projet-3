package fr.eql.ai109.projet3.business.helpers.prestation;

import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.StatePrestation;

import fr.eql.ai109.projet3.idao.PrestationIDao;
/*
 * Etat initial si le client a fait la réservation :
 * - le client peut annuler la réservation => PrestationAnnule
 * - l'éleveur peut confirmer la réservation => ConfirméParEleveur
 */

@Stateless
public class ReserveParClient implements StatePrestation {

	private static final long serialVersionUID = 1L;
	
	public static final ReserveParClient RESERVEPARCLIENT = new ReserveParClient();
	
	@EJB
	PrestationIDao prestationIdao;
	
	@Override
	public void valide(PrestationBU p) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PUTontapatV2");
		EntityManager em = emf.createEntityManager();
		
		
		// must set the date confirmation in prestation entity => save db with update here 
		// change state of the prestation here
		Prestation prestation = p.getPrestation();
		//Prestation prestation = p.getPrestation();
		//Prestation presta = prestationIdao.getById( p.getPrestation().getIdPrestation() );
		//prestationIdao.valideDateConfirmation( p.getPrestation().getIdPrestation(), "")
		//prestationIdao.update(presta);
		
		//Prestation prestation = prestationIdao.addDate( p.getPrestation().getIdPrestation(), "DATE_CONFIRMATION" );
		prestation.setConfirmation(LocalDateTime.now());
		//prestationIdao.update( prestation );
		em.merge(prestation);
		//prestationIdao.myUpdate( prestation );
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
