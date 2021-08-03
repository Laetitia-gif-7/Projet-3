package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface TerrainIBusiness {

	List<Terrain> findTerrainsByUtilisateur(Utilisateur utilisateur);
	
}
