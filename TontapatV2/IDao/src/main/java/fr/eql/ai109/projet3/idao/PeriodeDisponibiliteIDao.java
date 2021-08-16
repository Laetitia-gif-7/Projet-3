package fr.eql.ai109.projet3.idao;

import java.util.List;

import fr.eql.ai109.projet3.entity.PeriodeDisponibilite;

public interface PeriodeDisponibiliteIDao extends GenericIDao<PeriodeDisponibilite> {

	void splitPeriode(int dispoIdToDelete, List<PeriodeDisponibilite> listDispos);
}
