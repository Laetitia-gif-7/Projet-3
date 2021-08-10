package fr.eql.ai109.projet3.business;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.business.factories.FactoryEquipement;
import fr.eql.ai109.projet3.business.factories.FactoryQuantiteEquipement;
import fr.eql.ai109.projet3.entity.Equipement;
//import fr.eql.ai109.projet3.entity.PeriodeDisponibilite;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.constants.ConstantVariable;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;

import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.idao.EquipementIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(ReservationPrestationIBusiness.class)
@Stateless // if data are send by the web everytime. Statefull may store more infos in business
public class ReservationPrestationBusiness implements ReservationPrestationIBusiness {

	@EJB
	EquipementIDao equipementIDao;
	
	@EJB
	TerrainIDao terrainIDao;
	
	@EJB
	TroupeauIDao troupeauIDao; 
	
	@PostConstruct
	void init() {
		System.out.println("post construct ReservationPrestationBusiness");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("pre destroy ReservationPrestationBusiness");
	}
	
	// Override
	public ParametresReservationPrestation calculeDefautPrestation2(int idTerrain, int idTroupeau, Date dateDebut, Date dateFin) {
		
		ParametresReservationPrestation prp= new ParametresReservationPrestation();
		prp.setCout(1199.90f);
		prp.setNbAnimaux(50);
		prp.setQualiteTonte( 1.0f );
		prp.setBienEtreAnimal( 50.0f );
		
		List<QuantiteEquipement> equipements = new ArrayList<>();
		// cloture == id == 1
		Equipement equip = equipementIDao.getById(1);
		QuantiteEquipement quantEquip = new QuantiteEquipement();
		quantEquip.setEquipement(equip);
		quantEquip.setQuantite( 100 ); // set the quantity
		equipements.add(quantEquip);
		// abreuvoir == id = 2
		equip = equipementIDao.getById(2);
		quantEquip = new QuantiteEquipement();
		quantEquip.setEquipement(equip);
		quantEquip.setQuantite( 200 ); // set the quantity
		equipements.add(quantEquip);
		// abri == id = 3
		equip = equipementIDao.getById(3);
		quantEquip = new QuantiteEquipement();
		quantEquip.setEquipement(equip);
		quantEquip.setQuantite( 50 ); // set quantity
		equipements.add(quantEquip);
		
		prp.setEquipements(equipements);
		return prp;	
	}
	
	public ParametresReservationPrestation calculeDefautPrestation(int idTerrain, int idTroupeau, Date dateDebut, Date dateFin) {
		
		// can create at first and fill values along the algo
		ParametresReservationPrestation prp= new ParametresReservationPrestation();
		
		// periode de la prestation en jours
		LocalDate debut = convertToLocalDateViaInstant(dateDebut);
		LocalDate fin = convertToLocalDateViaInstant(dateFin);
		Period nbJour = Period.between(debut, fin);
				
		// besoin du terrain (surface, equipement disponible)
		//Terrain terrain = terrainIDao.getById(idTerrain);
		Terrain terrain = terrainIDao.getByIdWithEquipement(idTerrain);
		double superficie = terrain.getSuperficie().doubleValue();
		
		//Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		// need compositionTroupeau as well
		//Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		Troupeau troupeau = troupeauIDao.getTroupeauByIdWithComposition(idTroupeau);
		
		// to improve, take into account the disponibility
		int nbTotalAnimauxTroupeau = troupeau.getNbTotalAnimaux();
		prp.setNbTotalAnimauxTroupeau(nbTotalAnimauxTroupeau);
		double ugbMoyen = troupeau.getUGBMoyen();
		// prp.setUgbMoyen
		
		// compute le nombre d'animaux optimal, nbAnimaux recommandé
		int nbAnimaux = initialGuessNbAnimal(nbJour, terrain, nbTotalAnimauxTroupeau, ugbMoyen);
		prp.setNbAnimaux(nbAnimaux);
		
		// materiel dispo sur le terrain
		List<QuantiteEquipement> equipSurTerrain = terrain.getQuantiteEquipement(); 
				// terrainIDao.getEquipement(terrain.getIdTerrain());
		
		// materiel necessaire pour la prestation
		List<QuantiteEquipement> equipNecessaire = calculeEquipementNecessaire(nbAnimaux);
		
		// materiel à payer equipNecessaire - equipSurTerrain
		List<QuantiteEquipement> equipSupplementaire = soustraitEquipement(equipNecessaire, equipSurTerrain);
		
		// check availability of the missing materials by REST webservice
		
		// prix à payer              
		double coutTotal=0; // = calculePrixPrestation(prp); (for refactorisation)
		/*
		coutTotal += calculePrixAnimaux(troupeau, nbAnimaux, nbJour);
		coutTotal += calculePrixMateriel(equipSupplementaire);
		coutTotal += calculePrixTransport( nbAnimaux );
		prp.setCout(coutTotal);
		*/
		prp.setCout(1000);
		
		// util function,  need to be much better
		//int longueurCloture = getLongueurCloture(equipNecessaire);
		int longueurCloture = 100;
		
		prp.setBienEtreAnimal(
				calculeBienEtreAnimal(nbAnimaux, longueurCloture, terrain.getSuperficie().intValue(),
						terrain.isClos()));
		
		//prp.setQualiteTonte();
		// can be simple as nbAnimaux / nbAnimauxRecommande or recompute everything
		// here nbAniamux is ok, during init phase (nbAniamux == nbaniamux Recommande
		// no need to compute 1/1 !! calculeQualiteTonte( nbAnimaux, nbAnimauxRecommande )
		prp.setQualiteTonte( 1 );
		
		return prp;
	}
	
	private List<QuantiteEquipement> calculeEquipementNecessaire(int nbAnimaux) {
		
		List<QuantiteEquipement> listEquipement= new ArrayList<>();
		// cloture
		int surface = nbAnimaux * ConstantVariable.SEUIL_BIEN_ETRE_ANIMAL_CONFORTABLE;
		int longueurCloture = (int) (Math.sqrt(surface) * 4);
		QuantiteEquipement qe = FactoryQuantiteEquipement.Cloture.createQuantiteEquipement(longueurCloture);
		listEquipement.add(qe);
		
		// nbre d'abris : une surface pour tous les animaux / surface d'un abri
		int abri = (( nbAnimaux * ConstantVariable.M2_PAR_ANIMAL_ABRI ) / ConstantVariable.M2_PAR_ABRI ) + 1;
		qe = FactoryQuantiteEquipement.Abri.createQuantiteEquipement(abri);
		listEquipement.add(qe);
		
		// nbre d'abreuvoirs : the same
		int abreuvoir = nbAnimaux * ConstantVariable.L_PAR_ANIMAL_ABREUVOIR / ConstantVariable.L_PAR_ABREUVOIR;
		qe = FactoryQuantiteEquipement.Abreuvoir.createQuantiteEquipement(abreuvoir);
		listEquipement.add(qe);
		
		return listEquipement;
	}

	@Override
	public ParametresReservationPrestation actualisePrixPrestation(int idTerrain, int idTroupeau, Date dateDebut,
			Date dateFin, int nbAnimaux, int longueurCloture) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// first estimation of the number of animals for the prestation
	private int initialGuessNbAnimal(Period nbJour, Terrain terrain, int nbTotalAnimauxTroupeau, double ugbMoyen) {
		int nbAnimaux;
		
		nbAnimaux = (int) (( terrain.getSuperficie().floatValue() / ugbMoyen) * ( 365f / nbJour.getDays() ));
		System.out.println("nbAniamux " + nbAnimaux );
		
		if( nbTotalAnimauxTroupeau < nbAnimaux )
			nbAnimaux = nbTotalAnimauxTroupeau;
		
		return nbAnimaux;
	}
	
	////////////////// Calcul du prix
	// to keep for refactorisation
	public double calculePrixPrestation(Troupeau troupeau, int nbAnimaux, Period nbJours) {
		double coutTotal = 0;
		
		coutTotal += calculePrixAnimaux(troupeau, nbAnimaux, nbJours);
		//coutTotal += calculePrixMateriel(equipSupplementaire);
		//coutTotal += calculePrixTransport();
		return coutTotal;
	}

	private double calculePrixAnimaux( Troupeau troupeau, int nbAnimaux, Period nbJours) {
		return nbAnimaux * nbJours.getDays() * troupeau.getCoutParAnimal();
	}
	
	public double calculePrixMateriel( List<QuantiteEquipement> equipSupplementaire) {
		
		double cout = 0;
		
		for(QuantiteEquipement equip : equipSupplementaire ) {
			
			switch( equip.getEquipement().getLibelleEquipement() ) {
				case "clôture" :
					cout += equip.getQuantite() * ConstantVariable.PRIX_MATERIEL_CLOTURE;
					break;
				case "abri" :
					cout += equip.getQuantite() * ConstantVariable.PRIX_MATERIEL_ABRI;
					break;
				case "abreuvoir" :
					cout += equip.getQuantite() * ConstantVariable.PRIX_MATERIEL_ABREUVOIR;
					break;
			}
		}
		return cout;
	}
	
	public double calculePrixTransport( int nbAnimaux ) {
		return  nbAnimaux * ConstantVariable.PRIX_TRANSPORT_ANIMAL;
	}
	
	//////////// bien etre aniaml et qualite Tonte ////////////////
	public double calculeBienEtreAnimal( int nbAnimaux, int longueurCloture, int superficie, boolean isClos ) {
		double longueurCote;
		double surface; // longueurCote * longueurCote;
		double bea;
		
		if ( isClos == false) { // terrain non cloture => longueur de cloture commandé
			longueurCote = longueurCloture / 4.;
			surface = longueurCote * longueurCote;
			bea = surface / nbAnimaux / ConstantVariable.SEUIL_BIEN_ETRE_ANIMAL_CONFORTABLE;
		} else
			bea = superficie / nbAnimaux / ConstantVariable.SEUIL_BIEN_ETRE_ANIMAL_CONFORTABLE;
		
		return bea; 
	}
	
	/* https://www.baeldung.com/java-streams-find-list-items
		soustraction = equipNecessaire.stream()
		.filter( neccess -> equipSurTerrain.stream()
		.anyMatch( ) */
	/* tricky without Map */
	private List<QuantiteEquipement> soustraitEquipement(List<QuantiteEquipement> equipNecessaire, List<QuantiteEquipement> equipSurTerrain) {
		
		List<QuantiteEquipement> soustraction = new ArrayList<>();
		
		int qteNecess;
		Equipement equipement; 
		QuantiteEquipement qteEquipement;
		
		//for( String str : Arrays.asList("clôture","abri", "abreuvoir")) {
		for( String str : FactoryEquipement.getListOfLibelle() ) {
			qteNecess  = 0;
			// new Equipement
			equipement = new Equipement();
			equipement.setLibelleEquipement(str);
			qteEquipement = new QuantiteEquipement();
			/*
			for(QuantiteEquipement necess : equipNecessaire )
				if( necess.getEquipement().getLibelleEquipement().equals(str) )
					qteNecess = necess.getQuantite();
			
			for(QuantiteEquipement present : equipSurTerrain )
				if( present.getEquipement().getLibelleEquipement().equals(str) )
					qteNecess -= present.getQuantite();
			
			// assign the missing materiel
			if( qteNecess > 0 ) 
				qteEquipement.setQuantite(qteNecess);
			else 
				qteEquipement.setQuantite(0);
			*/
			
			qteEquipement.setQuantite(20);
			//qteEquipement.setEquipement(equipement);
			soustraction.add(qteEquipement );
		}
		
		return soustraction;
	}
	
	// extract longueur cloture for the list, bad implemenentation / structure => Map !!
	public int getLongueurCloture(List<QuantiteEquipement> equipNecessaire) {
		for( QuantiteEquipement qe : equipNecessaire )
			if( qe.getEquipement().getLibelleEquipement() == "clôture" )
				return qe.getQuantite();
		return 0;
	}
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
