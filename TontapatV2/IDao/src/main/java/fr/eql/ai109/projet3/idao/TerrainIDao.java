package fr.eql.ai109.projet3.idao;

import java.util.List;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;



public interface TerrainIDao extends GenericIDao<Terrain> {
	List<Terrain> getTerrainsByUser(Utilisateur utilisateur);
}