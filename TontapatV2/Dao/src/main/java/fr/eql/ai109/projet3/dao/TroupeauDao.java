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
		Query query = entityManager.createQuery("SELECT t "
												+ "FROM Troupeau t "
												//+ "INNER JOIN FETCH CompositionTroupeau ct on t = ct.troupeau "
												+ "WHERE t.utilisateur=:utilisateurParam");
			
		query.setParameter("utilisateurParam", utilisateur);
		troupeaux= query.getResultList( );
		//methode pour récupérer le premier objet de getResultList()
//		Iterator it = results.iterator( );
//		Object[] result = (Object[])it.next();
//		troupeaux.add((Troupeau)result[0]);
		//troupeaux = query.getResultList();
		
		//Methode d'axel : fonctionne avec une seule race 
		//troupeaux.get(0).getCompositionTroupeau().size();

		//Merge : même résultat qu'avec le refresh
		//entityManager.merge(troupeaux.get(0).getCompositionTroupeau().get(0));
		for(Troupeau troupeau : troupeaux) {
			entityManager.refresh(troupeau.getCompositionTroupeau().get(0));
		}
		
		return troupeaux;
	
	}
	
}
	

