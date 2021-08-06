package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.eql.ai109.projet3.entity.ProportionVegetation;
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


}
