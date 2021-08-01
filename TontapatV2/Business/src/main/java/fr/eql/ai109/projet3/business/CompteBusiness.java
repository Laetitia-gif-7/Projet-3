package fr.eql.ai109.projet3.business;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

//import javax.inject.Inject;

import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.CompteIBusiness;
import fr.eql.ai109.projet3.idao.UtilisateurIDao;

@Remote(CompteIBusiness.class)
@Stateless
public class CompteBusiness implements CompteIBusiness {

	@EJB
	private UtilisateurIDao utilisateurIDao;
	
	@Override
	public Utilisateur connection(String login, String password) {
		return utilisateurIDao.authentifier(login, password);
	}
	
}
