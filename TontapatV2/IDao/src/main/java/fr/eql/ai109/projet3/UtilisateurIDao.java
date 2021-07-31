package fr.eql.ai109.projet3;

import fr.eql.ai109.projet3.entity.Utilisateur;

public interface UtilisateurIDao {

	Utilisateur authentifier(String login, String password);
}
