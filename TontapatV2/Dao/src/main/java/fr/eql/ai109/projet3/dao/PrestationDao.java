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

		TypedQuery<Prestation> query = entityManager.createQuery(
			"SELECT DISTINCT p "
					+ "FROM Prestation p "
					+ "JOIN FETCH p.compositionTroupeauPrestations ctp "
					+ "WHERE p.terrain.utilisateur =:utilisateurParam "
					+ "	  OR ctp.troupeau.utilisateur =:utilisateurParam2 ",Prestation.class);
			/*
			"SELECT DISTINCT p "
					+ "FROM Prestation p "
					+ "JOIN p.compositionTroupeauPrestations ctp "
					+ "JOIN FETCH p.quantiteEquipementPrestations.equipement qep "
					+ "WHERE p.terrain.utilisateur =:utilisateurParam "
					+ "	  OR ctp.troupeau.utilisateur =:utilisateurParam2 ",Prestation.class);	
			*/
		query.setParameter("utilisateurParam", utilisateur);
		query.setParameter("utilisateurParam2", utilisateur);
		prestas = query.getResultList();
		// do not manage to load all data. to test an other SQL request all Equipements of  each prestations ??
		for( Prestation presta : prestas)
			entityManager.refresh(presta.getQuantiteEquipementPrestations().get(0));
		
		return prestas;
	}

}
