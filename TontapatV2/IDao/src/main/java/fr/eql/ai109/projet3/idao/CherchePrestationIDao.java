package fr.eql.ai109.projet3.idao;

import java.util.List;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.dto.TerrainTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;

public interface CherchePrestationIDao {
	
	List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(Terrain terrain);
	List<TerrainTrouveApresRechercheDTO> chercheTerrainsCompatibles(Troupeau troupeau);
}
