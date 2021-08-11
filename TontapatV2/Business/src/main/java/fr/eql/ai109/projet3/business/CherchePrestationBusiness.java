package fr.eql.ai109.projet3.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.CompositionTroupeau;
import fr.eql.ai109.projet3.entity.CompositionTroupeauPrestation;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.dto.TerrainTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.ibusiness.CherchePrestationIBusiness;
import fr.eql.ai109.projet3.idao.CherchePrestationIDao;
import fr.eql.ai109.projet3.idao.PrestationIDao;
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
	@EJB
	PrestationIDao prestationIDao;
	
	@Override
	public List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(int idTerrain) {

		Terrain terrain = terrainIDao.getById(idTerrain);
		List<TroupeauTrouveApresRechercheDTO> tmpTroupeaux = cherchePrestationIDao.chercheTroupeauxCompatibles(terrain);
		List<TroupeauTrouveApresRechercheDTO> troupeaux = new ArrayList<TroupeauTrouveApresRechercheDTO>();
		for (int i=0; i<tmpTroupeaux.size(); i++) {
			if(terrainIDao.getById(idTerrain).getVilleCp().getDepartement()
					.equals(tmpTroupeaux.get(i).getTroupeau().getUtilisateur().getVilleCp().getDepartement())) {
				
				if (tmpTroupeaux.get(i).getPourcentagePropoMorpho()!=0 && tmpTroupeaux.get(i).getPourcentagePropoVege()!=0) {
					troupeaux.add(tmpTroupeaux.get(i));
				}
			}
		}
		
		for(TroupeauTrouveApresRechercheDTO dto : troupeaux)
			System.out.println("Animaux disponibles pour prestation : " 
					+ nbAnimauxDisponiblePourPrestation( dto.getTroupeau(), dto.getDateMin(), dto.getDateMax()));
		
		return troupeaux;
	}

	@Override
	public List<TerrainTrouveApresRechercheDTO> chercheTerrainsCompatibles(int idTroupeau) {
		Troupeau troupeau = troupeauIDao.getById(idTroupeau);
		List<TerrainTrouveApresRechercheDTO> tmpTerrains = cherchePrestationIDao.chercheTerrainsCompatibles(troupeau);
		List<TerrainTrouveApresRechercheDTO> terrains = new ArrayList<TerrainTrouveApresRechercheDTO>();
		for (int i=0; i<tmpTerrains.size(); i++) {
			if(troupeauIDao.getById(idTroupeau).getUtilisateur().getVilleCp().getDepartement()
					.equals(tmpTerrains.get(i).getTerrain().getVilleCp().getDepartement())) {
				
				if (tmpTerrains.get(i).getPourcentagePropoMorpho()!=0 && tmpTerrains.get(i).getPourcentagePropoVege()!=0) {
					terrains.add(tmpTerrains.get(i));
				}
			}
		}
		return terrains;
	}
	
	public int nbAnimauxDisponiblePourPrestation( Troupeau troupeau, Date dateDebut, Date dateFin) {
		
		int nbAnimauxDansTroupeau = troupeau.getNbTotalAnimaux();
		List<Prestation> prestationsEnCours = 
				prestationIDao.prestationsEnCoursPourTroupeauId(troupeau.getIdTroupeau(), dateDebut, dateFin);
		
		int nbAnimauxOccupes = 0;
		for(Prestation prestation : prestationsEnCours)
			for(CompositionTroupeauPrestation cp : prestation.getCompositionTroupeauPrestations() )
			nbAnimauxOccupes += cp.getNbAnimaux();

		int directTotal = prestationIDao.nbAnimauxEnPrestationPourTroupeauId(troupeau.getIdTroupeau(), dateDebut, dateFin);
		
		return nbAnimauxDansTroupeau - nbAnimauxOccupes;
	}
	
	

}
