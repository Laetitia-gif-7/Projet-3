package fr.eql.ai109.projet3.ibusiness;

import fr.eql.ai109.projet3.entity.Utilisateur;

public interface CompteIBusiness {
	
	Utilisateur connection(String login, String password);
}
