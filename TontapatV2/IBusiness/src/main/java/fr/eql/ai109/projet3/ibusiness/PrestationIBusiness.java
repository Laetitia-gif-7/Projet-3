package fr.eql.ai109.projet3.ibusiness;

import java.util.Date;
import java.util.List;

import fr.eql.ai109.projet3.entity.PrestationExt;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface PrestationIBusiness {
	
	List<PrestationExt> findPrestationsByUtilisateur(Utilisateur utilisateur);
	void valide(int id); // prestation Id or PrestationExt
	void cancel(int id);
}
