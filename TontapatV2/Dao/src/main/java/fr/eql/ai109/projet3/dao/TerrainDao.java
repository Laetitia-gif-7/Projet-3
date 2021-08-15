package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.entity.PeriodeDisponibilite;
import fr.eql.ai109.projet3.entity.ProportionVegetation;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.idao.TerrainIDao;

@Remote(TerrainIDao.class)
@Stateless
public class TerrainDao extends GenericDao<Terrain> implements TerrainIDao {

	@Override
	public List<Terrain> getTerrainsByUser(Utilisateur utilisateur) {
		List<Terrain> terrains = new ArrayList<Terrain>();
		Query query = entityManager.createQuery("SELECT t FROM Terrain t WHERE t.utilisateur=:utilisateurParam");
		query.setParameter("utilisateurParam", utilisateur);
		terrains = query.getResultList();
		for (int i=0; i<terrains.size(); i++) {
			for (int j=0; j<terrains.get(i).getQuantiteEquipement().size(); j++) {
				entityManager.refresh(terrains.get(i).getQuantiteEquipement().get(j));
			}
			for (int j=0; j<terrains.get(i).getProportionVegetations().size(); j++) {
				entityManager.refresh(terrains.get(i).getProportionVegetations().get(j));
				ProportionVegetation pv = terrains.get(i).getProportionVegetations().get(j);
				
				for (int k=0; k < pv.getVegetationRef().getRaceRefs().size() ; k++) {
					entityManager.refresh(pv.getVegetationRef().getRaceRefs().get(k));	
				}
			}
			for (int j=0; j<terrains.get(i).getProportionMorphologies().size(); j++) {
				entityManager.refresh(terrains.get(i).getProportionMorphologies().get(j));
			}
			for (int j=0; j<terrains.get(i).getPeriodeDisponibilites().size(); j++) {
				entityManager.refresh(terrains.get(i).getPeriodeDisponibilites().get(j));
			}
		}
		return terrains;
	}

	@Override
	public Terrain getTerrainByIdTerrainAndUser(int idTerrain, Utilisateur utilisateur) {
		Terrain terrain = new Terrain();
		Query query = entityManager.createQuery("SELECT t FROM Terrain t "
												+ "WHERE t.utilisateur=:utilisateurParam "
												+ "AND t.idTerrain=:idTerrainParam");
		query.setParameter("utilisateurParam", utilisateur);
		query.setParameter("idTerrainParam", idTerrain);
		terrain = (Terrain) query.getSingleResult();
		for (int i=0; i<terrain.getPeriodeDisponibilites().size(); i++) {
			entityManager.refresh(terrain.getPeriodeDisponibilites().get(i));
		}
		return terrain;
	}

	// used ???to check
	@Override
	public List<QuantiteEquipement> getEquipement(int idTerrain) {
		
		TypedQuery<QuantiteEquipement> query = 
				entityManager.createQuery(
						  "SELECT qe "
						+ "FROM Terrain t "
						+ "JOIN t.quantiteEquipement qe "
						+ "WHERE t.idTerrain := paramTerrainId ",
						QuantiteEquipement.class).setParameter("paramTerrainId",idTerrain);
		List<QuantiteEquipement> qes = query.getResultList();
		return qes;
	}
	
	@Override
	public Terrain getByIdWithEquipement(int idTerrain) {
		Terrain terrain;
		
		TypedQuery<Terrain> query = entityManager.createQuery(
				  "SELECT t "
				+ "FROM Terrain t "
				+ "JOIN t.quantiteEquipement qe "
				+ "JOIN qe.equipement qee "
				+ "WHERE t.idTerrain = :paramIdTerrain ",
				Terrain.class)
				.setParameter("paramIdTerrain", idTerrain);
		
		terrain = query.getSingleResult();
		// strange ?? error in normal mode, all equipments in debug mode
		Equipement eq;
		for(QuantiteEquipement qe : terrain.getQuantiteEquipement()) {
			eq = qe.getEquipement();
			qe.getEquipement().getUniteRef();
		}
		
		return terrain;
	}
	
	@Override
	public PeriodeDisponibilite getPeriodeDisponibilite(int idTerrain, Date dateDebut, Date dateFin) {
		PeriodeDisponibilite pd; // = null;
		TypedQuery<PeriodeDisponibilite> query = entityManager.createQuery(
				  "SELECT pd "
				+ "FROM Terrain t "
				+ "JOIN t.periodeDisponibilites pd "
				+ "WHERE t.id = :paramIdTerrain "
				+ "   AND ( pd.debutPeriode <= :paramDateDebut ) AND (pd.finPeriode >= :paramDateFin) "
				, PeriodeDisponibilite.class);
		
		query.setParameter("paramIdTerrain", idTerrain);
		query.setParameter("paramDateDebut", dateDebut);
		query.setParameter("paramDateFin", dateFin);
		pd = query.getSingleResult();
		return pd;
	}
	
}

//	cmd.CommandText = @"SELECT DISTINCT tr.troupeau_id, compte.nom, compte.prenom, espece.libelle, morpho.*, pm.*, vege.*, pv.*,
//            CASE WHEN pd.debut_periode > pd2.debut_periode THEN pd.debut_periode
//                ELSE pd2.debut_periode
//            END AS 'MinDateService', 
//            CASE WHEN pd.fin_periode > pd2.fin_periode THEN pd2.fin_periode
//            ELSE pd.fin_periode
//            END AS 'MaxDateService'
//FROM terrain t
//JOIN proportion_morpho pm ON t.terrain_id = pm.terrain_id
//JOIN morphologie morpho ON pm.morphologie_id = morpho.morphologie_id
//JOIN assoc_morpho_race amr ON morpho.morphologie_id = amr.morphologie_id
//JOIN race race1 ON  amr.race_id = race1.race_id
//-- Vegetation
//JOIN proportion_vegetation pv ON t.terrain_id = pv.terrain_id
//JOIN vegetation vege ON pv.vegetation_id = vege.vegetation_id
//JOIN assoc_race_vegetation arv ON vege.vegetation_id = arv.vegetation_id
//JOIN race race2 ON arv.race_id = race2.race_id
//JOIN espece ON race2.espece_id = espece.espece_id 
//--
//JOIN comp_troupeau ON race1.race_id = comp_troupeau.race_id
//JOIN troupeau tr ON comp_troupeau.troupeau_id = tr.troupeau_id
//
//JOIN ref_ville_cp ville ON ville.insee_id = t.insee_id
//JOIN compte ON tr.compte_id = compte.compte_id
//JOIN ref_ville_cp ville2 ON compte.insee_id = ville2.insee_id
//
//JOIN periodedisponibilite as pd ON t.terrain_id = pd.terrain_id
//-- Range periode must overlap <=> not not overlapping , use of auto jointure
//JOIN  periodedisponibilite as pd2 ON tr.troupeau_id = pd2.troupeau_id
//--  ON not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode )
//WHERE pd2.troupeau_id is not null
//and ( not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode ) )
//and race1.race_id = race2.race_id
//and ville.departement = ville2.departement
//and pd.terrain_id = @id_field
//ORDER BY tr.troupeau_id, MinDateService";

