package fr.eql.ai109.projet3.idao;

import fr.eql.ai109.projet3.entity.Utilisateur;

public interface UtilisateurIDao extends GenericIDao<Utilisateur> {

	Utilisateur authentifier(String login, String password);
}
