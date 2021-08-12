package fr.eql.ai109.projet3.business;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.constants.ConstantVariable;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;

import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.idao.PrestationIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(ReservationPrestationIBusiness.class)
@Stateless // if data are send by the web everytime. Statefull may store more infos in business
public class ReservationPrestationBusiness implements ReservationPrestationIBusiness {

	@EJB
	TerrainIDao terrainIDao;
	@EJB
	TroupeauIDao troupeauIDao;
	@EJB
	PrestationIDao prestationIDao;
	
	@PostConstruct
	void init() {
		System.out.println("post construct ReservationPrestationBusiness");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("pre destroy ReservationPrestationBusiness");
	}
	
	@Override
	public ParametresReservationPrestation calculeDefautPrestation(int idTerrain, int idTroupeau, Date dateDebut, Date dateFin) {
		// initial call, all must be filled
		ParametresReservationPrestation prp= new ParametresReservationPrestation();
		prp.setDateDebut(dateDebut);
		prp.setDateFin(dateFin);
		
		// periode de la prestation en jours
		LocalDate debut = convertToLocalDateViaInstant(dateDebut);
		LocalDate fin = convertToLocalDateViaInstant(dateFin);
		long nbJour = ChronoUnit.DAYS.between(debut, fin); 
				
		// besoin du terrain (surface, equipement disponible)
		Terrain terrain = terrainIDao.getByIdWithEquipement(idTerrain);
		//double superficie = terrain.getSuperficie().doubleValue();
		// need compositionTroupeau
		Troupeau troupeau = troupeauIDao.getTroupeauByIdWithComposition(idTroupeau);

		// take into account the disponibility of animals
		int nbTotalAnimauxTroupeau = troupeau.getNbTotalAnimaux();
		prp.setNbTotalAnimauxTroupeau(nbTotalAnimauxTroupeau);
		// take into account the disponibility of animals
		int nbAnimauxOccupes = prestationIDao.nbAnimauxEnPrestationPourTroupeauId(idTroupeau, dateDebut, dateFin);
		int nbAnimauxDispo = nbTotalAnimauxTroupeau - nbAnimauxOccupes;
		prp.setNbAnimauxTroupeauDispo(nbAnimauxDispo);
		
		double ugbMoyen = troupeau.getUGBMoyen();
		prp.setUgbMoyen(ugbMoyen);
		
		// retourne le nombre d'animaux recommandé (size, period, ugb) : 
		// nbAnimaux recommandés  + nbAnimaux vraiment disponible sur ce troupeau
		List<Integer> listNbAnimaux = initialGuessNbAnimaux(nbJour, terrain, nbAnimauxDispo, ugbMoyen);
		int nbAnimauxRecommande = listNbAnimaux.get(0);
		int nbAnimaux = listNbAnimaux.get(1);
		prp.setNbAnimauxRecommande(nbAnimauxRecommande);
		prp.setNbAnimaux(nbAnimaux);
		
		// materiel dispo sur le terrain
		List<QuantiteEquipement> equipSurTerrain = terrain.getQuantiteEquipement(); 
		prp.setEquipementSurTerrain(equipSurTerrain);
		
		// materiel necessaire pour la prestation
		List<QuantiteEquipement> equipNecessaire = calculeEquipementNecessaire(nbAnimaux, true);
		
		// materiel à payer equipNecessaire - equipSurTerrain
		List<QuantiteEquipement> equipSupplementaire = soustraitEquipement(equipNecessaire, equipSurTerrain);
		
		prp.setEquipementSupplementaire(equipSupplementaire);
		// cloture supplémentaire inprp
		int longueurTmp = prp.getLongueurClotureSupplementaire();
		prp.setLongueurCloture(longueurTmp);
		// check availability of the missing materials by REST webservice
		
		// util function,  need to be much better, 
		// wrong total cloture doit etre calculée !!
		// int longueurCloture = getLongueurCloture(equipNecessaire);
		
		// prix à payer              
		double coutTotal=0; // = calculePrixPrestation(prp); (for refactorisation)
		coutTotal += calculePrixAnimaux( troupeau, nbAnimaux, nbJour ); // troupeau, getCoutParAnimal
		coutTotal += calculePrixMateriel( equipSupplementaire );
		coutTotal += calculePrixTransport( nbAnimaux );
		prp.setCout(coutTotal);
		
		// doit etre la somme cloture supplementaire + cloture du terrain
		int longueurCloture = getLongueurCloture(equipNecessaire);
		System.out.println("longueur cloture necessaire : " + longueurCloture);
		prp.setBienEtreAnimal(
				calculeBienEtreAnimal(nbAnimaux, longueurCloture, terrain.getSuperficie().intValue(),
						terrain.isClos()));
		
		prp.setQualiteTonte( (double)nbAnimaux / nbAnimauxRecommande );
		return prp;
	}
	
	@Override
	public ParametresReservationPrestation actualisePrixPrestation(int idTerrain, int idTroupeau, ParametresReservationPrestation prp) {
		// periode de la prestation en jours
		LocalDate debut = convertToLocalDateViaInstant(prp.getDateDebut());
		LocalDate fin = convertToLocalDateViaInstant(prp.getDateFin());
		long nbJour = ChronoUnit.DAYS.between(debut, fin);
		
		// Could easily avoid the database request
		// besoin du terrain (surface, isClos) //  equipement disponible saved the first time
		Terrain terrain = terrainIDao.getByIdWithEquipement(idTerrain);
		// need troupeau, only for getcoutParAnimal
		Troupeau troupeau = troupeauIDao.getTroupeauByIdWithComposition(idTroupeau);
		
		// reactualise le nombre d'animaux disponibles avec les nouvelles dates
		int nbAnimauxOccupes = prestationIDao.nbAnimauxEnPrestationPourTroupeauId(idTroupeau, prp.getDateDebut(), prp.getDateFin());
		prp.setNbAnimauxTroupeauDispo( prp.getNbTotalAnimauxTroupeau()-nbAnimauxOccupes);
		
		// change si date change, ne prend pas en compte le nombre d'animaaux, seulement recommandé
		// nbAnimaux fixé par utilisateur dans ce cas
		int nbAnimauxRecommande = initialGuessNbAnimaux(
				nbJour, terrain, prp.getNbAnimauxTroupeauDispo(), prp.getUgbMoyen()).get(0); // superficie
		prp.setNbAnimauxRecommande(nbAnimauxRecommande);
		
		// Equipements
		List<QuantiteEquipement> equipSurTerrain = prp.getEquipementSurTerrain();
		
		// materiel necessaire pour la prestation, can be factorized ?
		// false do not compute cloture in the function, get from the input in prp
		List<QuantiteEquipement> equipNecessaire = calculeEquipementNecessaire( prp.getNbAnimaux(), false );
		// only copy the input from the user
		equipNecessaire.add( FactoryQuantiteEquipement.Cloture.createQuantiteEquipement(prp.getLongueurCloture()) );
		
		// materiel à payer equipNecessaire - equipSurTerrain
		List<QuantiteEquipement> equipSupplementaire = soustraitEquipement(equipNecessaire, equipSurTerrain);
		prp.setEquipementSupplementaire(equipSupplementaire);
		
		int longueurTmp = prp.getLongueurClotureSupplementaire();
		prp.setLongueurCloture(longueurTmp);
		// check availability of the missing materials by REST webservice
		
		// prix à payer              
		double coutTotal=0; // = calculePrixPrestation(prp); (for refactorisation)
		coutTotal += calculePrixAnimaux(troupeau, prp.getNbAnimaux(), nbJour); // troupeau, getCoutParAnimal
		coutTotal += calculePrixMateriel(equipSupplementaire);
		coutTotal += calculePrixTransport( prp.getNbAnimaux() );
		prp.setCout(coutTotal);
		
		// doit etre la somme cloture supplementaire + cloture du terrain
		int longueurCloture = getLongueurCloture(equipNecessaire);
		System.out.println("longueur cloture necessaire : " + longueurCloture);
		prp.setBienEtreAnimal(
				calculeBienEtreAnimal(prp.getNbAnimaux(), prp.getLongueurCloture(), terrain.getSuperficie().intValue(),
						terrain.isClos()));
		
		prp.setQualiteTonte( (double)prp.getNbAnimaux() / nbAnimauxRecommande );
		return prp;
	}
	
	//////// Private methods
	private List<QuantiteEquipement> calculeEquipementNecessaire(int nbAnimaux, boolean calculeLongueurCloture) {
		
		List<QuantiteEquipement> listEquipement= new ArrayList<>();
		QuantiteEquipement qe;
		
		if( calculeLongueurCloture ) {
			// cloture
			int surface = nbAnimaux * ConstantVariable.SEUIL_BIEN_ETRE_ANIMAL_CONFORTABLE;
			// 4 * cote d'un carré
			int longueurCloture = (int) (Math.sqrt(surface) * 4);
			qe = FactoryQuantiteEquipement.Cloture.createQuantiteEquipement(longueurCloture);
			listEquipement.add(qe);
		}
		
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

	// first estimation of the number of animals for the prestation
	private List<Integer> initialGuessNbAnimaux(long nbJour, Terrain terrain, int nbAnimauxTroupeauDispo, double ugbMoyen) {
		int nbAnimaux;
		int nbAnimauxRecommande;
		
		nbAnimauxRecommande = (int) (
				// ugb definition over 1 year
				( terrain.getSuperficie().doubleValue() / (ugbMoyen * ConstantVariable.HA_EN_METRE2) )
				// adjust to the period
				* ( 365f / nbJour ) );
		
		// must be available in the troupeau
		if( nbAnimauxTroupeauDispo < nbAnimauxRecommande )
			nbAnimaux = nbAnimauxTroupeauDispo;
		else
			nbAnimaux = nbAnimauxRecommande;
		
		return Arrays.asList(nbAnimauxRecommande, nbAnimaux);
	}
	
	////////////////// Calcul du prix
	// to keep for refactorisation
	private double calculePrixPrestation(Troupeau troupeau, int nbAnimaux, long nbJours) {
		double coutTotal = 0;
		
		coutTotal += calculePrixAnimaux(troupeau, nbAnimaux, nbJours);
		//coutTotal += calculePrixMateriel(equipSupplementaire);
		//coutTotal += calculePrixTransport();
		return coutTotal;
	}

	private double calculePrixAnimaux( Troupeau troupeau, int nbAnimaux, long nbJours) {
		return nbAnimaux * nbJours * troupeau.getCoutParAnimal();
	}
	
	private double calculePrixMateriel( List<QuantiteEquipement> equipSupplementaire) {
		
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
			
			qteEquipement.setEquipement(equipement);
			soustraction.add(qteEquipement );
		}
		return soustraction;
	}
	
	// extract longueur cloture for the list, bad implemenentation / structure => Map !!
	private int getLongueurCloture(List<QuantiteEquipement> equipNecessaire) {
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
	
	/* to delete, send a fixed prp
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
	}*/
}
