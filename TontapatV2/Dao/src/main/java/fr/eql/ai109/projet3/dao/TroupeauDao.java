package fr.eql.ai109.projet3.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;


import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(TroupeauIDao.class)
@Stateless
public class TroupeauDao extends GenericDao<Troupeau> implements TroupeauIDao {

	@Override
	public List<Troupeau> getTroupeauxByUser(Utilisateur utilisateur) {
		List<Troupeau> troupeaux = new ArrayList<Troupeau>();
		Query query = entityManager.createQuery("SELECT t, ct.nbAnimaux "
												+ "FROM Troupeau t "
												+ "INNER JOIN CompositionTroupeau ct on t.idTroupeau=ct.id.idTroupeau "
												+ "WHERE t.utilisateur=:utilisateurParam");
			
		query.setParameter("utilisateurParam", utilisateur);
		List results = query.getResultList( );
		Iterator it = results.iterator( );
		Object[] result = (Object[])it.next();
		troupeaux.add((Troupeau)result[0]);
		//troupeaux = query.getResultList();
		
			
		
		return troupeaux;
	
	}
	
}
	

