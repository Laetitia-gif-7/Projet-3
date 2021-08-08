package fr.eql.ai109.projet3.ibusiness;

import java.util.List;

import fr.eql.ai109.projet3.entity.Troupeau;

public interface CherchePrestationIBusiness {

	List<Troupeau> chercheTroupeauxCompatibles(int idTerrain);
}
