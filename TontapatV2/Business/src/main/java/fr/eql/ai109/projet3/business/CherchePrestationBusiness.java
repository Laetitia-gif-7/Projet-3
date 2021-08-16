package fr.eql.ai109.projet3.business;

import java.util.ArrayList;
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
		List<TroupeauTrouveApresRechercheDTO> tmpTroupeaux = cherchePrestationIDao.chercheTroupeauxCompatibles(terrain);
		List<TroupeauTrouveApresRechercheDTO> troupeaux = new ArrayList<TroupeauTrouveApresRechercheDTO>();
		for (int i=0; i<tmpTroupeaux.size(); i++) {
			if (tmpTroupeaux.get(i).getPourcentagePropoMorpho()!=0 && tmpTroupeaux.get(i).getPourcentagePropoVege()!=0) {
				troupeaux.add(tmpTroupeaux.get(i));
			}
		}
		return troupeaux;
	}
	
	

}
