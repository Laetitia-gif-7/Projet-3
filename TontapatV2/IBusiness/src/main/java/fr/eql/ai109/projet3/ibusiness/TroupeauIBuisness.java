package fr.eql.ai109.projet3.ibusiness;

import java.util.List;


import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface TroupeauIBuisness {

	List<Troupeau> findTroupeauxByUtilisateur(Utilisateur utilisateur);
}
