package fr.eql.ai109.projet3.business;

import java.time.LocalDateTime;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Incident;
import fr.eql.ai109.projet3.entity.IncidentRef;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.IncidentIBusiness;
import fr.eql.ai109.projet3.idao.IncidentIDao;
import fr.eql.ai109.projet3.idao.IncidentRefIDao;

@Remote(IncidentIBusiness.class)
@Stateless
public class IncidentBusiness implements IncidentIBusiness{
	
	@EJB
	IncidentRefIDao incidentRefIDao; 
	
	@EJB
	IncidentIDao incidentIDao;

	@Override
	public List<IncidentRef> findAllIncidentRef() {
		
		return incidentRefIDao.getAll();
	}

	@Override
	public void DeclarationIncident(Prestation prestation, Utilisateur utilisateurConnecte, IncidentRef incidentRef) {
		Incident incident = new Incident();	
		incident.setDeclarateur(utilisateurConnecte);
		incident.setPrestation(prestation);
		incident.setDateDeclaration(LocalDateTime.now());
		incident.setIncidentRef(incidentRef);
		incidentIDao.add(incident);
	}

}
