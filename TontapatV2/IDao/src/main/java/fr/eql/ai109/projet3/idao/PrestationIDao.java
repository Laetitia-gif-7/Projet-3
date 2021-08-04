package fr.eql.ai109.projet3.idao;

import java.util.List;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;


public interface PrestationIDao extends GenericIDao<Prestation> {
	List<Prestation> getPrestationsByUser(Utilisateur utilisateur);
}
