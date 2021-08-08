package fr.eql.ai109.projet3.business;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;

@Remote(ReservationPrestationIBusiness.class)
@Stateless // if data are send by the web everytime. Statefull may store more infos in business
public class ReservationPrestationBusiness {

	
	// EJB dao
	
}
