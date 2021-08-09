package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;

public interface CherchePrestationIBusiness {

	List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(int idTerrain);
}
