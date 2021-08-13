package fr.eql.ai109.projet3.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.CompositionTroupeau;
import fr.eql.ai109.projet3.entity.CompositionTroupeauPrestation;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;
// import fr.eql.ai109.projet3.business.helpers.prestation.*; not need anymore after Factory
import fr.eql.ai109.projet3.business.factories.FactoryPrestrestationBU;
import fr.eql.ai109.projet3.business.utils.utils;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.idao.CompositionTroupeauPrestationIDao;
import fr.eql.ai109.projet3.idao.PrestationIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(PrestationIBusiness.class)
@Stateless // Statefull maybe here ??  
public class PrestationBusiness implements PrestationIBusiness {
	
	Map<Integer, PrestationBU> prestationsBU = new HashMap<>();
	
	@EJB
    private FactoryPrestrestationBU factoryPrestaBu;
	
	@EJB
	PrestationIDao prestationIDao;
	
	@EJB
	TerrainIDao terrainIDao;
	
	@EJB
	TroupeauIDao troupeauIDao;

	@EJB
	CompositionTroupeauPrestationIDao compositionTroupeauPrestationIDao;
	
	@PostConstruct
	void init() {
		System.out.println("init PrestationBusiness");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy PrestationIBusiness");
	}
	
	@Override
	public void createPrestationClient(Utilisateur utilisateurConnecte, ParametresReservationPrestation prp) {
		//
	}

	@Override
	public void createPrestationEleveur(Utilisateur utilisateur, int idTerrain, int idTroupeau, Date dateDebut,
			Date dateFin) {
		Prestation prestation = new Prestation();
		// soit ici, en objet, soit dans la dao directement en  sql / jsql
		prestation.setInitiateurPrestation(utilisateur);
		prestation.setReservation( LocalDateTime.now() );
		prestation.setDebutPrestation(utils.convertToLocalDateTimeViaInstant(dateDebut));
		prestation.setFinPrestation(utils.convertToLocalDateTimeViaInstant(dateFin));
		prestation.setTerrain( terrainIDao.getById(idTerrain) );
		// UUID uuid = UUID.randomUUID();
		Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		CompositionTroupeauPrestation compoTroupeauPresta = new CompositionTroupeauPrestation();
		
		//compoTroupeauPresta.setPrestation( prestation );
		//compoTroupeauPresta.setNbAnimaux(0);
		//compoTroupeauPresta.setTroupeau( troupeau );
		/*
		compositionTroupeauPrestationIDao.add(compoTroupeauPresta);
		List<CompositionTroupeauPrestation> listCompos = new ArrayList<>();
		listCompos.add(compoTroupeauPresta);
		prestation.setCompositionTroupeauPrestations( listCompos );
		*/
		
		
		prestation = prestationIDao.add(prestation);
		compoTroupeauPresta.setPrestation( prestation );
		compoTroupeauPresta.setNbAnimaux(0);
		compoTroupeauPresta.setTroupeau( troupeau );
		compositionTroupeauPrestationIDao.add(compoTroupeauPresta);
		
	}
	
	@Override
	public Map<Integer,PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur) {
		List<Prestation> prestations = prestationIDao.getPrestationsByUser(utilisateur);
		Map<Integer,PrestationBU> prestationsBu = new HashMap<>();
		
		for (Prestation prestation : prestations) {
			System.out.println("test: " + prestation.toString());
			prestationsBu.put(
					prestation.getIdPrestation(),
					factoryPrestaBu.createPrestationBU(prestation, utilisateur ) );
		}
		return prestationsBu;
	}

	@Override
	public PrestationBU valide(PrestationBU prestaBu) {
		System.out.println("etat de presta : " + prestaBu.getStateString());
		prestaBu.valide();
		// only way to save in db, it is from here
		prestationIDao.update(prestaBu.getPrestation());
		
		// date have been included and state changed
		return prestaBu;
	}
	/*
	@Override
	public PrestationBU valideAvecDate(PrestationBU prestaBu, LocalDateTime date) {
		prestaBu.valideAvecDate(date);
		prestationIDao.update(prestaBu.getPrestation());
		return prestaBu;
	}*/
	
	
	@Override
	public PrestationBU annule(int id) {
		// TODO To implement
		return new PrestationBU();
	}

}
		
	

