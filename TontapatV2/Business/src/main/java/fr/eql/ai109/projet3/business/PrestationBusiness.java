package fr.eql.ai109.projet3.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.CompositionTroupeau;
import fr.eql.ai109.projet3.entity.CompositionTroupeauPrestation;
import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.QuantiteEquipementPrestation;
import fr.eql.ai109.projet3.entity.QuantiteEquipementPrestationPK;
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
	public void createPrestationClient(Utilisateur utilisateur, 
			ParametresReservationPrestation prp, int idTerrain, int idTroupeau) {
		Prestation prestation = new Prestation();
				
		prestation.setInitiateurPrestation(utilisateur);
		prestation.setReservation( LocalDateTime.now() );
		prestation.setDebutPrestation(utils.convertToLocalDateTimeViaInstant(prp.getDateDebut()));
		prestation.setFinPrestation(utils.convertToLocalDateTimeViaInstant(prp.getDateFin()));
		prestation.setCoutPrestation((float)prp.getCout());
		prestation.setTerrain( terrainIDao.getById(idTerrain) );
		UUID uuid = UUID.randomUUID();
		System.out.println("uuid : " + uuid.toString());
		prestation.setNumeroPrestation(uuid.toString());
		prestation.setIdDerniereProposition(utilisateur);
		prestation.setPremiereVisitePropose(utils.convertToLocalDateTimeViaInstant(prp.getPremiereVisite()));
		
		
		
		Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		prestation = prestationIDao.add(prestation);
		
		List<QuantiteEquipementPrestation> equipementPrestationSupplementaires = new ArrayList<>();
		for(QuantiteEquipement quantiteEquip : prp.getEquipementSupplementaire() ) {
			QuantiteEquipementPrestation quantiteSupplementaire = new QuantiteEquipementPrestation(); 
			quantiteSupplementaire.setPrestation(prestation);
			quantiteSupplementaire.setCout( 0 );
			quantiteSupplementaire.setQuantite(  quantiteEquip.getQuantite());	
			
			quantiteSupplementaire.setEquipement( quantiteEquip.getEquipement() );
			QuantiteEquipementPrestationPK qepPk = new QuantiteEquipementPrestationPK();
			qepPk.setIdEquipement( quantiteEquip.getEquipement().getIdEquipement());
			qepPk.setIdPrestation( prestation.getIdPrestation() );
			quantiteSupplementaire.setId( qepPk );
			
			equipementPrestationSupplementaires.add(quantiteSupplementaire);
		}
		
		prestation.setQuantiteEquipementPrestations( equipementPrestationSupplementaires );
		prestationIDao.enregistreEquipementSupplementaires(equipementPrestationSupplementaires);
		
		// need function to set correct number to a set of compotroupeaux
		CompositionTroupeauPrestation compoTroupeauPresta = new CompositionTroupeauPrestation();
		compoTroupeauPresta.setPrestation( prestation );
		compoTroupeauPresta.setNbAnimaux(prp.getNbAnimaux());
		compoTroupeauPresta.setTroupeau( troupeau );
		compositionTroupeauPrestationIDao.add(compoTroupeauPresta);
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
		UUID uuid = UUID.randomUUID();
		prestation.setNumeroPrestation(uuid.toString());
		
		Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		CompositionTroupeauPrestation compoTroupeauPresta = new CompositionTroupeauPrestation();
		
		prestation = prestationIDao.add(prestation);
		compoTroupeauPresta.setPrestation( prestation );
		compoTroupeauPresta.setNbAnimaux(0);
		compoTroupeauPresta.setTroupeau( troupeau );
		compositionTroupeauPrestationIDao.add(compoTroupeauPresta);
		
	}
	// calcule le nombre d'animaux et de races
	private void createCompositionTroupeau(CompositionTroupeauPrestation ctp, int nbAniamux) {
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
		
	

