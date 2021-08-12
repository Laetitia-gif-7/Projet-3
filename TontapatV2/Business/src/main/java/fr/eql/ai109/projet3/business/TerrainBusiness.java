package fr.eql.ai109.projet3.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;
import fr.eql.ai109.projet3.idao.TerrainIDao;

@Remote(TerrainIBusiness.class)
@Stateless
public class TerrainBusiness implements TerrainIBusiness {

	@EJB
	TerrainIDao terrainIDao;
	
	@Override
	public List<Terrain> findTerrainsByUtilisateur(Utilisateur utilisateur) {
		return terrainIDao.getTerrainsByUser(utilisateur);
	}

	@Override
	public Terrain findTerrainByIdTerrainAndUtilisateur(int idTerrain, Utilisateur utilisateur) {
		return terrainIDao.getTerrainByIdTerrainAndUser(idTerrain, utilisateur);
	}

	
	
	

}
