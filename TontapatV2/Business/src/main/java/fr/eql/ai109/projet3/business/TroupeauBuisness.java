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
		
		return troupeauIDao.getTroupeauxByUser(utilisateur);
	}

}
