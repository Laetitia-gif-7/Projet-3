package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		/*
		Query query = entityManager.createQuery("SELECT p FROM Prestation p "
				// + "JOIN Terrain t ON t.idTerrain = p.terrain.idTerrain "
				// + "JOIN Utilisateur u ON t.utilisateur = u "
				//+ "WHERE t.utilisateur=:utilisateurParam");
				+ "WHERE p.terrain.utilisateur = :utilisateurParam");
		*/
		/*
		TypedQuery<Prestation> query = entityManager.createQuery(
				  "SELECT p "
				+ "FROM Prestation p "
				+ "JOIN FETCH p.compositionTroupeauPrestations "
				+ "WHERE p.terrain.utilisateur =:utilisateurParam ", Prestation.class);
//				+ "OR ctp.troupeau.utilisateur =:utilisateurParam ", Prestation.class);
		*/
		Query query = entityManager.createQuery(
				  "SELECT DISTINCT p "
				+ "FROM Prestation p "
				+ "JOIN FETCH p.compositionTroupeauPrestations ctp "
				+ "WHERE p.terrain.utilisateur =:utilisateurParam "
				+ "	  OR ctp.troupeau.utilisateur =:utilisateurParam2 ");
		query.setParameter("utilisateurParam", utilisateur);
		query.setParameter("utilisateurParam2", utilisateur);
		prestas = query.getResultList();
		return prestas;
	}
	
}
