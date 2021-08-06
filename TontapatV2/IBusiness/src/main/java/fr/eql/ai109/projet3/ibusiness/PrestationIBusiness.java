package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface PrestationIBusiness {
	
	List<PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur);
	PrestationBU valide(int idPrestation); // prestation Id or PrestationExt
	PrestationBU annule(int idPrestation);
	// void valide( Date...)
}
