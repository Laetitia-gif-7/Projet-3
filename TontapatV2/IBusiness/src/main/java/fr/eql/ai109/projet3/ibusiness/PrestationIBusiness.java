package fr.eql.ai109.projet3.ibusiness;

import java.util.Map;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface PrestationIBusiness {
	
	Map<Integer,PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur);
	
	// prestationbu contains prestation + additional data. Can send back to the view for updating it
	PrestationBU valide(PrestationBU pbu);
	PrestationBU annule(int idPrestation);
	// void valide( Date...)
}
