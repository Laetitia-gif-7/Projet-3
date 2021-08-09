package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.idao.CherchePrestationIDao;

@Remote(CherchePrestationIDao.class)
@Stateless
public class CherchePrestations implements CherchePrestationIDao {

	@PersistenceContext(unitName = "PUTontapatV2")
	protected EntityManager entityManager;
	
	public List<Troupeau> chercheTroupeauxCompatibles2(Terrain terrain) {
		List<Troupeau> correspondances = new ArrayList<>();
		
		Query query = entityManager.createNativeQuery(
				"SELECT DISTINCT tr.id_troupeau "
				+ "FROM terrain t "
				+ "JOIN periode_disponibilite as pd ON t.id_terrain = pd.id_terrain "
				+ "JOIN troupeau tr "
				+ "JOIN  periode_disponibilite as pd2 ON tr.id_troupeau = pd2.id_troupeau "
				+ "WHERE ( not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode ) ) "
				+ " AND pd.id_terrain = :idField "
				);
		
		query.setParameter("idField", terrain.getIdTerrain());
		correspondances = query.getResultList();
		
		return correspondances;
	}
	
	public List<Troupeau> chercheTroupeauxCompatibles(Terrain terrain) {
		List<Troupeau> correspondances = new ArrayList<>();
		
		//TypedQuery<Troupeau>
		Query query = entityManager.createQuery(
				"SELECT tr "
				+ "FROM PeriodeDisponibilite pd, PeriodeDisponibilite pd2 "
				+ "JOIN pd2.troupeau tr "
				+ "WHERE " // pd2.troupeau is not null "
				+ "pd.terrain = :paramTerrain "
				+ "AND ( not ( pd.finPeriode < pd2.debutPeriode OR pd.debutPeriode > pd2.finPeriode ) ) "
		);
				
//				+ "WHERE pd.finPeriode < :paramDate " working
//				+ "JOIN Troupeau tr "
//				+ "JOIN PeriodeDisponibilite pd2 ON tr.periodeDisponibilites = pd2 "
//				+ "WHERE not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode ) "
//				+ " AND t = :paramTerrain "
				//-- Range periode must overlap <=> not not overlapping , use of auto jointure
				//JOIN  periodedisponibilite as pd2 ON tr.troupeau_id = pd2.troupeau_id
				//--  ON not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode )
				//,Troupeau.class);
		
		query.setParameter("paramTerrain", terrain);
		// query.setParameter("paramDate", new Date(2025,01,01));
		correspondances = query.getResultList();
		
		return correspondances;
	}
	
	
	public List<Troupeau> chercheTroupeauxCompatibles3(Terrain terrain) {
		List<Troupeau> correspondances = new ArrayList<>();
		
		TypedQuery<Troupeau> query = entityManager.createQuery(
				  "SELECT pd.troupeau "
				+ "FROM PeriodeDisponibilite pd "
				+ "JOIN PeriodeDisponibilite pd2 "
//				+ "JOIN Troupeau tr "
//				+ "JOIN tr.periodeDisponibilites pd2 "
				+ "WHERE ( not ( pd.finPeriode < pd2.debutPeriode OR pd.debutPeriode > pd2.finPeriode ) ) "
//				+ "AND t = :paramTerrain "
				
//				+ "WHERE pd.finPeriode < :paramDate " working
//				+ "JOIN Troupeau tr "
//				+ "JOIN PeriodeDisponibilite pd2 ON tr.periodeDisponibilites = pd2 "
//				+ "WHERE not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode ) "
//				+ " AND t = :paramTerrain "
				//-- Range periode must overlap <=> not not overlapping , use of auto jointure
				//JOIN  periodedisponibilite as pd2 ON tr.troupeau_id = pd2.troupeau_id
				//--  ON not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode )
				,Troupeau.class);
		
		//query.setParameter("paramTerrain", terrain);
		// query.setParameter("paramDate", new Date(2025,01,01));
		correspondances = query.getResultList();
		
		return correspondances;
	}
}
