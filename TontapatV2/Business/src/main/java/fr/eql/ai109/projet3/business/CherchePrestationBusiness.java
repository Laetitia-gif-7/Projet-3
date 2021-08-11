package fr.eql.ai109.projet3.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.dto.TerrainTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.ibusiness.CherchePrestationIBusiness;
import fr.eql.ai109.projet3.idao.CherchePrestationIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;
import fr.eql.ai109.projet3.idao.TroupeauIDao;

@Remote(CherchePrestationIBusiness.class)
@Stateless
public class CherchePrestationBusiness implements CherchePrestationIBusiness {

	@EJB
	CherchePrestationIDao cherchePrestationIDao;
	
	@EJB
	TerrainIDao terrainIDao;
	@EJB
	TroupeauIDao troupeauIDao;
	
	@Override
	public List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(int idTerrain) {

		Terrain terrain = terrainIDao.getById(idTerrain);
		List<TroupeauTrouveApresRechercheDTO> tmpTroupeaux = cherchePrestationIDao.chercheTroupeauxCompatibles(terrain);
		List<TroupeauTrouveApresRechercheDTO> troupeaux = new ArrayList<TroupeauTrouveApresRechercheDTO>();
		for (int i=0; i<tmpTroupeaux.size(); i++) {
			if (tmpTroupeaux.get(i).getPourcentagePropoMorpho()!=0 && tmpTroupeaux.get(i).getPourcentagePropoVege()!=0) {
				troupeaux.add(tmpTroupeaux.get(i));
			}
		}
		return troupeaux;
	}

	@Override
	public List<TerrainTrouveApresRechercheDTO> chercheTerrainsCompatibles(int idTroupeau) {
		Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		List<TerrainTrouveApresRechercheDTO> tmpTerrains = cherchePrestationIDao.chercheTerrainsCompatibles(troupeau);
		List<TerrainTrouveApresRechercheDTO> terrains = new ArrayList<TerrainTrouveApresRechercheDTO>();
		for (int i=0; i<tmpTerrains.size(); i++) {
			if (tmpTerrains.get(i).getPourcentagePropoMorpho()!=0 && tmpTerrains.get(i).getPourcentagePropoVege()!=0) {
				terrains.add(tmpTerrains.get(i));
			}
		}
		return terrains;
	}
	
	

}
