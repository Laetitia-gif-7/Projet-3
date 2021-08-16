package fr.eql.ai109.projet3.idao;

import java.util.Date;
import java.util.List;

import fr.eql.ai109.projet3.entity.PeriodeDisponibilite;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface TerrainIDao extends GenericIDao<Terrain> {
	List<Terrain> getTerrainsByUser(Utilisateur utilisateur);
	Terrain getTerrainByIdTerrainAndUser(int idTerrain, Utilisateur utilisateur);
	List<QuantiteEquipement> getEquipement(int idTerrain);
	Terrain getByIdWithEquipement(int idTerrain);
	PeriodeDisponibilite getPeriodeDisponibilite(int idTerrain, Date dateDebut, Date dateFin);
}
