package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.TerrainTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;

public interface CherchePrestationIBusiness {

	List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(int idTerrain);
	List<TerrainTrouveApresRechercheDTO> chercheTerrainsCompatibles(int idTroupeau);
	List<Prestation> cherchePrestationMemeDepartement(Utilisateur utilisateurBerger);
}
