package fr.eql.ai109.projet3.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.ibusiness.CherchePrestationIBusiness;
import fr.eql.ai109.projet3.idao.CherchePrestationIDao;
import fr.eql.ai109.projet3.idao.TerrainIDao;

@Remote(CherchePrestationIBusiness.class)
@Stateless
public class CherchePrestationBusiness implements CherchePrestationIBusiness {

	@EJB
	CherchePrestationIDao cherchePrestationIDao;
	
	@EJB
	TerrainIDao terrainIDao;
	
	@Override
	public List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(int idTerrain) {

		Terrain terrain = terrainIDao.getById(idTerrain);
		List<TroupeauTrouveApresRechercheDTO> troupeaux = cherchePrestationIDao.chercheTroupeauxCompatibles(terrain);
		return troupeaux;
	}
	
	

}
