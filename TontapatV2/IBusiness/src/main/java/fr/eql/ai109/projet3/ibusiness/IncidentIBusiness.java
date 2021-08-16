package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.IncidentRef;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface IncidentIBusiness {
	
	List<IncidentRef> findAllIncidentRef();

	public void DeclarationIncident(Prestation prestation, Utilisateur utilisateurConnecte, IncidentRef incidentRef);

}
