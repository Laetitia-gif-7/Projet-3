package fr.eql.ai109.projet3.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
	
	@PostConstruct
	void init() {
		System.out.println("init PrestationDao");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy PrestationDao");
	}
	
	@Override
	public List<Prestation> getPrestationsByUser(Utilisateur utilisateur) {
		List<Prestation> prestas = new ArrayList<Prestation>();

		TypedQuery<Prestation> query = entityManager.createQuery(
			"SELECT DISTINCT p "
					+ "FROM Prestation p "
					+ "JOIN FETCH p.compositionTroupeauPrestations ctp "
					+ "WHERE p.terrain.utilisateur =:utilisateurParam "
					+ "	  OR ctp.troupeau.utilisateur =:utilisateurParam2 ",Prestation.class);
			/* not working as expected, keep to retest
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
		for( Prestation presta : prestas) {
			for( int i=0; i<presta.getQuantiteEquipementPrestations().size(); i++) {
				entityManager.refresh(presta.getQuantiteEquipementPrestations().get(i));
			}
			for( int i=0; i<presta.getEvaluations().size(); i++) {
				entityManager.refresh(presta.getEvaluations().get(i));
			}
			for( int i=0; i<presta.getIncidents().size(); i++) {
				entityManager.refresh(presta.getIncidents().get(i));
			}
		}
		return prestas;
	}

}


