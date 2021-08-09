package fr.eql.ai109.projet3.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;

import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.idao.EquipementIDao;
//import fr.eql.ai109.projet3.idao.QuantiteEquipementIDao;

@Remote(ReservationPrestationIBusiness.class)
@Stateless // if data are send by the web everytime. Statefull may store more infos in business
public class ReservationPrestationBusiness implements ReservationPrestationIBusiness {

	// @EJB to delete ? 
	// QuantiteEquipementIDao quantiteEquipementIDao;
	
	@EJB
	EquipementIDao equipementIDao;  
	
	@Override
	public ParametresReservationPrestation calculeDefautPrestation(int idTerrain, int idTroupeau, Date dateDebut, Date dateFin) {
		
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

	@Override
	public ParametresReservationPrestation actualisePrixPrestation(int idTerrain, int idTroupeau, Date dateDebut,
			Date dateFin, int nbAnimaux, int longueurCloture) {
		// TODO Auto-generated method stub
		return null;
	}
}
