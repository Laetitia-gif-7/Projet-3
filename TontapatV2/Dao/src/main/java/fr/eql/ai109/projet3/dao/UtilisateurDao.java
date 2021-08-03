package fr.eql.ai109.projet3.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.idao.UtilisateurIDao;

@Remote(UtilisateurIDao.class)
@Stateless
public class UtilisateurDao extends GenericDao<Utilisateur> implements UtilisateurIDao {

	
	
	public Utilisateur authentifier(String login, String password) {
		Utilisateur user = null;
		List<Utilisateur> users = null;
			
		Query query = entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.email=:loginParam "
				+ "AND u.motDePasse=:passwordParam");
		query.setParameter("loginParam", login);
		query.setParameter("passwordParam", password);
		users = query.getResultList();
			
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}
	
}
