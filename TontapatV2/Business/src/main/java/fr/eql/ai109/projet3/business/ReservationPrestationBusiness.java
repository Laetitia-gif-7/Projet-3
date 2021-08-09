package fr.eql.ai109.projet3.business;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.business.factories.FactoryQuantiteEquipement;
import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.constants.ConstantVariable;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;

import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.idao.EquipementIDao;
//import fr.eql.ai109.projet3.idao.QuantiteEquipementIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(ReservationPrestationIBusiness.class)
@Stateless // if data are send by the web everytime. Statefull may store more infos in business
public class ReservationPrestationBusiness implements ReservationPrestationIBusiness {

	// @EJB to delete ? 
	// QuantiteEquipementIDao quantiteEquipementIDao;
	
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
		
		ParametresReservationPrestation prp= new ParametresReservationPrestation();
		
		// periode de la prestation en jours
		LocalDate debut = convertToLocalDateViaInstant(dateDebut);
		LocalDate fin = convertToLocalDateViaInstant(dateFin);
		Period nbJour = Period.between(debut, fin);
				
		// besoin du terrain (surface, equipement disponible)
		Terrain terrain = terrainIDao.getById(idTerrain);
		double superficie = terrain.getSuperficie().doubleValue();
		
		Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		
		// to improve, take into account the disponibility
		int nbTotalAnimauxTroupeau = troupeau.getNbTotalAnimaux();
		prp.setNbTotalAnimauxTroupeau(nbTotalAnimauxTroupeau);
		double ugbMoyen = troupeau.getUGBMoyen();
		
		// compute le nombre d'animaux optimal
		int nbAnimaux = initialGuessNbAnimal(nbJour, terrain, nbTotalAnimauxTroupeau, ugbMoyen);
		prp.setNbAnimaux(nbAnimaux);
		
		// materiel dispo sur le terrain
		List<QuantiteEquipement> equipSurTerrain = terrainIDao.getEquipement(terrain.getIdTerrain());
		
		// materiel necessaire pour la prestation
		List<QuantiteEquipement> equipNecessaire = calculeEquipementNecessaire(nbAnimaux);
		
		// materiel à payer equipNecessaire - equipSurTerrain
		List<QuantiteEquipement> equipSupplementaire = soustraitEquipement(equipNecessaire, equipSurTerrain);
		
		// check availability of hte missing materials
		
		// prix à payer
		
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
		int abri = ( nbAnimaux * ConstantVariable.M2_PAR_ANIMAL_ABRI ) / ConstantVariable.M2_PAR_ABRI;
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
	
	/* https://www.baeldung.com/java-streams-find-list-items
		soustraction = equipNecessaire.stream()
		.filter( neccess -> equipSurTerrain.stream()
		.anyMatch( )
	*/
	List<QuantiteEquipement> soustraitEquipement(List<QuantiteEquipement> equipNecessaire, List<QuantiteEquipement> equipSurTerrain) {
		List<QuantiteEquipement> soustraction = new ArrayList<>();
		
		int qteNecess;
		Equipement equipement; 
		QuantiteEquipement qteEquipement;
		
		for( String str : Arrays.asList("clôture","abri", "abreuvoir")) {
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
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	
	
}
