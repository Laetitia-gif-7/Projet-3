package fr.eql.ai109.projet3.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.CompositionTroupeau;
import fr.eql.ai109.projet3.entity.CompositionTroupeauPrestation;
import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.entity.PeriodeDisponibilite;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.QuantiteEquipementPrestation;
import fr.eql.ai109.projet3.entity.QuantiteEquipementPrestationPK;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.constants.ConstantVariable;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;
// import fr.eql.ai109.projet3.business.helpers.prestation.*; not need anymore after Factory
import fr.eql.ai109.projet3.business.factories.FactoryPrestationBU;
import fr.eql.ai109.projet3.business.utils.utils;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.idao.CompositionTroupeauPrestationIDao;
import fr.eql.ai109.projet3.idao.PeriodeDisponibiliteIDao;
import fr.eql.ai109.projet3.idao.PrestationIDao;
import fr.eql.ai109.projet3.idao.RaceRefIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(PrestationIBusiness.class)
@Stateless // Statefull maybe here ??  
public class PrestationBusiness implements PrestationIBusiness {
	
	Map<Integer, PrestationBU> prestationsBU = new HashMap<>();
	
	@EJB
    private FactoryPrestationBU factoryPrestaBu;
	
	@EJB
	PrestationIDao prestationIDao;
	
	@EJB
	TerrainIDao terrainIDao;
	
	@EJB
	TroupeauIDao troupeauIDao;

	@EJB
	CompositionTroupeauPrestationIDao compositionTroupeauPrestationIDao;
	
	@EJB
	RaceRefIDao raceRefIDao;
	
	@EJB
	PeriodeDisponibiliteIDao periodeDisponibiliteIDao;
	
	
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
		Terrain terrain = terrainIDao.getById(idTerrain);
		prestation.setTerrain( terrain );
		UUID uuid = UUID.randomUUID();
		System.out.println("uuid : " + uuid.toString());
		prestation.setNumeroPrestation(uuid.toString());
		prestation.setIdDerniereProposition(utilisateur);
		prestation.setPremiereVisitePropose(utils.convertToLocalDateTimeViaInstant(prp.getPremiereVisite()));
		
		prestation = prestationIDao.add(prestation);
		
		List<QuantiteEquipementPrestation> equipementPrestationSupplementaires = new ArrayList<>();
		for(QuantiteEquipement quantiteEquip : prp.getEquipementSupplementaire() ) {
			QuantiteEquipementPrestation quantiteSupplementaire = new QuantiteEquipementPrestation(); 
			quantiteSupplementaire.setPrestation(prestation);
			quantiteSupplementaire.setCout( 0 );
			quantiteSupplementaire.setQuantite(  quantiteEquip.getQuantite());	
			
			quantiteSupplementaire.setEquipement( quantiteEquip.getEquipement() );
			// work with explicit creation of PK
			QuantiteEquipementPrestationPK qepPk = new QuantiteEquipementPrestationPK();
			qepPk.setIdEquipement( quantiteEquip.getEquipement().getIdEquipement());
			qepPk.setIdPrestation( prestation.getIdPrestation() );
			quantiteSupplementaire.setId( qepPk );
			
			equipementPrestationSupplementaires.add(quantiteSupplementaire);
		}
		
		prestation.setQuantiteEquipementPrestations( equipementPrestationSupplementaires );
		prestationIDao.enregistreEquipementSupplementaires(equipementPrestationSupplementaires);
		
		// Assign correct number of animals in compoTroupeauPrestation
// TODO
// May delete the previous one if set by the eleveur 
		Troupeau troupeau = troupeauIDao.getTroupeauByIdWithComposition(idTroupeau);
		List<CompositionTroupeauPrestation>  compoTroupeauPrestations = 
				createCompositionTroupeauPrestation( prp.getNbAnimaux(), troupeau, prp.getDateDebut(), prp.getDateFin());
		for( CompositionTroupeauPrestation ctp : compoTroupeauPrestations) {
			ctp.setPrestation(prestation);
			ctp.setTroupeau(troupeau);
			compositionTroupeauPrestationIDao.add(ctp);
		}
		/*
		// Doit spliter la période de disponibilité du terrain
		PeriodeDisponibilite periodeDispoTerrain = terrainIDao.getPeriodeDisponibilite(idTerrain, prp.getDateDebut(), prp.getDateFin());
		List<PeriodeDisponibilite> listDispos = splitPeriodeDisponibilite( periodeDispoTerrain, prp.getDateDebut(), prp.getDateFin() );
		// set the new ones before sending to DAO
		for( PeriodeDisponibilite dispo : listDispos ) {
			dispo.setCreationDispo( new Date() );
			dispo.setTerrain( terrain );
		}
		// delete previous periode cannot here ?? cannot remove a detached object ??
		periodeDisponibiliteIDao.splitPeriode( periodeDispoTerrain.getDispoId(), listDispos );
		*/
	}

	@Override
	public void createPrestationEleveur(Utilisateur utilisateur, int idTerrain, int idTroupeau, Date dateDebut, Date dateFin) {
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
		
		// just create a link between prestation et le troupeau, 
		// the composition will be done (automatically) by the client
		prestation = prestationIDao.add(prestation);
		compoTroupeauPresta.setPrestation( prestation );
		compoTroupeauPresta.setNbAnimaux(0);
		compoTroupeauPresta.setTroupeau( troupeau );
		compositionTroupeauPrestationIDao.add(compoTroupeauPresta);
	}
	
	public void ReservePrestationBerger(int idPrestation, Utilisateur berger) {
		Prestation prestation = prestationIDao.getById(idPrestation);
		prestation.setBerger(berger);
		prestation.setConfirmationBerger(LocalDateTime.now());
		prestationIDao.update(prestation);
		
	}
	
	@Override
	public Map<Integer,PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur) {
		List<Prestation> prestations = prestationIDao.getPrestationsByUser(utilisateur);
		//  Id prestation , prestationBu
		Map<Integer,PrestationBU> prestationsBu = new HashMap<>();
		
		for (Prestation prestation : prestations) {
			System.out.println("test: " + prestation.toString());
			prestationsBu.put(
					prestation.getIdPrestation(),
					factoryPrestaBu.createPrestationBU(prestation, utilisateur ) );
		}
		return prestationsBu;
	}
	
	// calcule le nombre d'animaux et de races de la prestation
	// take into account the number of animals disponibles
	private  List<CompositionTroupeauPrestation> createCompositionTroupeauPrestation(int nbAnimaux, Troupeau troupeau, Date debut, Date fin) {
		List<CompositionTroupeauPrestation> compotroupoPrestas = new ArrayList<>();
		
		// get the animaux already in prestation
		List<CompositionTroupeauPrestation> compotroupoPrestasOccupes = new ArrayList<>();

		//  race_id  nb_animaux
		Map<Integer, Integer> mapRaceDispo= nbAnimauxParRaceDisponibles(troupeau, debut, fin);
		
		// try to take out as much as possible from each race, order random
		for( Map.Entry<Integer, Integer> entry : mapRaceDispo.entrySet() ) {
			
			CompositionTroupeauPrestation ctp = new CompositionTroupeauPrestation();
			// cette race ne suffit pas a fournir nbAnimaux
			if( entry.getValue() <= nbAnimaux ) {
				ctp.setNbAnimaux(entry.getValue());
				// Problem !! Need raceRef and its id : like quantite !!
				ctp.setRaceRef( raceRefIDao.getById( entry.getKey() ) );
				// RaceRef( getRaceRefFromId(entry.getkey()) );
				nbAnimaux -= entry.getValue();
			// cette race contient plus ou egal a nbAnimaux
			}  else {
				ctp.setNbAnimaux(nbAnimaux);
				ctp.setRaceRef( raceRefIDao.getById( entry.getKey() ) );
				nbAnimaux = 0;
			}
			
			compotroupoPrestas.add(ctp);
			if (nbAnimaux == 0)
				return compotroupoPrestas;
		}
		// jamais execute
		return compotroupoPrestas;
	}

	private Map<Integer, Integer> nbAnimauxParRaceDisponibles(Troupeau troupeau, Date debut, Date fin) {
		
		List<Prestation> prestas = prestationIDao.prestationsEnCoursPourTroupeauId( troupeau.getIdTroupeau() , debut, fin);
		// Intermediate map, certainly easier
		// id_race  , nb_animaux
		Map<Integer, Integer> mapRaceOccupes = new HashMap<>();
		for(Prestation presta : prestas) {
			
			for(CompositionTroupeauPrestation ctp : presta.getCompositionTroupeauPrestations()) {
				int ctp_id_race = ctp.getRaceRef().getIdRace();
				int nb_race = ctp.getNbAnimaux();
				
				// update the map, creating key if necessary
				if( mapRaceOccupes.containsKey(ctp_id_race) ) 
					mapRaceOccupes.put(ctp_id_race, mapRaceOccupes.get(ctp_id_race)+ nb_race );
				else
					mapRaceOccupes.put(ctp_id_race, nb_race);
			}
		}
		
		Map<Integer, Integer> mapRaceInitial = new HashMap<>();
		for( CompositionTroupeau ct : troupeau.getCompositionTroupeau() )
			mapRaceInitial.put(ct.getRaceRef().getIdRace(), ct.getNbAnimaux());
		
		// Aniamuxdisponibles
		Map<Integer,Integer> mapRaceDispo = new HashMap<>();
		// initial always more races
		for( Map.Entry<Integer,Integer> entry : mapRaceInitial.entrySet() ) {
			int nbdispo = entry.getValue() - mapRaceOccupes.get( entry.getKey() );
			if( nbdispo > 0 )
				mapRaceDispo.put(entry.getKey(), nbdispo );
		}
		
		return mapRaceDispo;
	}
	
	private List<PeriodeDisponibilite> splitPeriodeDisponibilite( PeriodeDisponibilite period, Date dateDebut, Date dateFin ) {
		List<PeriodeDisponibilite> listPeriods = new ArrayList<>();
		PeriodeDisponibilite periodSplited;
		
		long diffInMillies; // = Math.abs(secondDate.getTime() - firstDate.getTime());
	    long diff; //= TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
		if (period.getDebutPeriode().getTime() < dateDebut.getTime() ) {
			diffInMillies = Math.abs(dateDebut.getTime() - period.getDebutPeriode().getTime());
			diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if ( diff > ConstantVariable.MIN_JOUR_PERIODE_DISPO ) {
				periodSplited = new PeriodeDisponibilite();
				periodSplited.setDebutPeriode( period.getDebutPeriode());
				periodSplited.setFinPeriode( dateDebut );
				listPeriods.add(periodSplited);
			}
		}
				
		if (period.getFinPeriode().getTime() > dateFin.getTime() ) {
			diffInMillies = Math.abs(  period.getFinPeriode().getTime() - dateFin.getTime() );
			diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if ( diff > ConstantVariable.MIN_JOUR_PERIODE_DISPO ) {
				periodSplited = new PeriodeDisponibilite();
				periodSplited.setDebutPeriode( dateFin );
				periodSplited.setFinPeriode( period.getFinPeriode());
				listPeriods.add(periodSplited);
			}
		}
		return listPeriods;
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
	
	@Override
	public PrestationBU valide(PrestationBU prestaBu, Utilisateur utilisateur) {
		prestaBu.valide(utilisateur);
		prestationIDao.update(prestaBu.getPrestation());
		return prestaBu;
	}
	
	@Override
	public PrestationBU valide(PrestationBU prestaBu, Date date, Utilisateur utilisateur) {
		prestaBu.valide(utilisateur);
		prestationIDao.update(prestaBu.getPrestation());
		return null;
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

	@Override
	public void valideEtatDesLieux(int idPrestation, Utilisateur utilisateur) {
		Prestation prestation = prestationIDao.prestationWhithCtp(idPrestation);
		if(utilisateur.getId() == prestation.getBerger().getId()) {
			prestation.setEtatLieuBerger(LocalDateTime.now());
		}
		if(utilisateur.getId() == prestation.getTerrain().getUtilisateur().getId()) {
			prestation.setEtatLieuClient(LocalDateTime.now());
		}
		if(utilisateur.getId() == prestation.getCompositionTroupeauPrestations().get(0).getTroupeau().getUtilisateur().getId()) {
			prestation.setEtatLieuEleveur(LocalDateTime.now());
		}
		prestationIDao.update(prestation);
		
	}

	@Override
	public void valideContrat(int idPrestation, Utilisateur utilisateur) {
		Prestation prestation = prestationIDao.prestationWhithCtp(idPrestation);
		if(utilisateur.getId() == prestation.getBerger().getId()) {
			prestation.setContratBerger(LocalDateTime.now());
		}
		if(utilisateur.getId() == prestation.getTerrain().getUtilisateur().getId()) {
			prestation.setContratClient(LocalDateTime.now());
		}
		if(utilisateur.getId() == prestation.getCompositionTroupeauPrestations().get(0).getTroupeau().getUtilisateur().getId()) {
			prestation.setContratEleveur(LocalDateTime.now());
		}
		prestationIDao.update(prestation);
		
	}

}
		
	

