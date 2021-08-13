package fr.eql.ai109.projet3.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.IncidentRef;
import fr.eql.ai109.projet3.ibusiness.IncidentIBusiness;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;
import fr.eql.ai109.projet3.idao.IncidentRefIDao;

@Remote(IncidentIBusiness.class)
@Stateless
public class IncidentBusiness implements IncidentIBusiness{
	
	@EJB
	IncidentRefIDao incidentIdao; 

	@Override
	public List<IncidentRef> findAllIncidentRef() {
		
		return incidentIdao.getAll();
	}

}
