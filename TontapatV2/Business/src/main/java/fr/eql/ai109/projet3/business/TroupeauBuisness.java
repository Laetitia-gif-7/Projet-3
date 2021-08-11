package fr.eql.ai109.projet3.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBuisness;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(TroupeauIBuisness.class)
@Stateless
public class TroupeauBuisness implements TroupeauIBuisness{
	
	@EJB
	TroupeauIDao troupeauIDao;

	@Override
	public List<Troupeau> findTroupeauxByUtilisateur(Utilisateur utilisateur) {
		
		//return 
		List <Troupeau> troupeaux = troupeauIDao.getTroupeauxByUser(utilisateur);
//		for (Troupeau troupeau : troupeaux) {
//	        //prestation.getTerrain(); // ok terrain loaded .....
//	        System.out.println(troupeau.getCompositionTroupeau().get(0).getNbAnimaux());
//	         
	  //  }
		 return troupeaux;
	}

	@Override
	public Troupeau findTroupeauByIdTroupeauAndUtilisateur(int idTroupeau, Utilisateur utilisateur) {
		
		return troupeauIDao.getById(idTroupeau);
	}
	
	

}
