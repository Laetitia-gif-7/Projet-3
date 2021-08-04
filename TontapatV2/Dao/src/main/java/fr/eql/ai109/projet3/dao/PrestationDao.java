package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.idao.PrestationIDao;

@Remote(PrestationIDao.class)
@Stateless
public class PrestationDao extends GenericDao<Prestation> implements PrestationIDao {

	@Override
	public List<Prestation> getPrestationsByUser(Utilisateur utilisateur) {
		List<Prestation> prestas = new ArrayList<Prestation>();
		// p.terrain
		Query query = entityManager.createQuery("SELECT p FROM Prestation p "
				// + "JOIN Terrain t ON t.idTerrain = p.terrain.idTerrain "
				// + "JOIN Utilisateur u ON t.utilisateur = u "
				//+ "WHERE t.utilisateur=:utilisateurParam");
				+ "WHERE p.terrain.utilisateur = :utilisateurParam");
		query.setParameter("utilisateurParam", utilisateur);
		prestas = query.getResultList();
		return prestas;
	}
	
}
