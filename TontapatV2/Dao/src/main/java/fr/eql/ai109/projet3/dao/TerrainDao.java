package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;

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
			entityManager.refresh(terrains.get(i).getQuantiteEquipement().get(i));
			entityManager.refresh(terrains.get(i).getProportionVegetations().get(i));
			entityManager.refresh(terrains.get(i).getProportionMorphologies().get(i));
		}
		return terrains;
	}
	
}
