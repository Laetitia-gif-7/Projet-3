package fr.eql.ai109.projet3.idao;

import java.util.List;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;

public interface CherchePrestationIDao {
	
	List<Troupeau> chercheTroupeauxCompatibles(Terrain terrain);
}
