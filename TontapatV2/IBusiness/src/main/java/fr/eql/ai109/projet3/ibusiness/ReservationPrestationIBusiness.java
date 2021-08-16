package fr.eql.ai109.projet3.ibusiness;

import java.util.Date;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;

public interface ReservationPrestationIBusiness {

	ParametresReservationPrestation calculeDefautPrestation(int idTerrain, int idTroupeau, Date dateDebut, Date dateFin);	
	ParametresReservationPrestation actualisePrixPrestation (int idTerrain, int idTroupeau, ParametresReservationPrestation prp);
}
