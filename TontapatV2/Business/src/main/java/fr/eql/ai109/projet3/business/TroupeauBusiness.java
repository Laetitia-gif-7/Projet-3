package fr.eql.ai109.projet3.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBusiness;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(TroupeauIBusiness.class)
@Stateless
public class TroupeauBusiness implements TroupeauIBusiness{
	
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
	public Troupeau findTroupeauById(int idTroupeau) {
		
		return troupeauIDao.getById(idTroupeau);
	}
	
	

}
