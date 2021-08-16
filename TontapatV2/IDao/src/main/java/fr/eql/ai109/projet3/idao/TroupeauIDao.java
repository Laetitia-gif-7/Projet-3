package fr.eql.ai109.projet3.idao;

import java.util.List;

import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface TroupeauIDao extends GenericIDao<Troupeau> {
	
	List<Troupeau> getTroupeauxByUser(Utilisateur utilisateur);
	Troupeau getTroupeauByIdWithComposition(int idTroupeau);
}
