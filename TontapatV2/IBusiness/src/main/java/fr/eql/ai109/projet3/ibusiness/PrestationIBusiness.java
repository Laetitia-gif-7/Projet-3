package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface PrestationIBusiness {
	
	List<PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur);
	// prestationbu contains everything
	PrestationBU valide(PrestationBU pbu); // prestation Id
	PrestationBU annule(int idPrestation);
	// void valide( Date...)
}
