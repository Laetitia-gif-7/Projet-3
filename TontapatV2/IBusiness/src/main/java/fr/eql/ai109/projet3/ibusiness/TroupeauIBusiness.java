package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface TroupeauIBusiness {

	List<Troupeau> findTroupeauxByUtilisateur(Utilisateur utilisateur);
	Troupeau findTroupeauById(int idTroupeau);

}
