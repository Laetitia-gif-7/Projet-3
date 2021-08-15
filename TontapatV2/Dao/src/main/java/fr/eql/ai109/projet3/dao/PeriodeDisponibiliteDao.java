package fr.eql.ai109.projet3.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.PeriodeDisponibilite;
import fr.eql.ai109.projet3.idao.PeriodeDisponibiliteIDao;

@Remote(PeriodeDisponibiliteIDao.class)
@Stateless
public class PeriodeDisponibiliteDao extends GenericDao<PeriodeDisponibilite> implements PeriodeDisponibiliteIDao {

	@Override
	public void splitPeriode(int dispoIdToDelete, List<PeriodeDisponibilite> listDispos) {
		PeriodeDisponibilite dispoToDelete = getById(dispoIdToDelete);
		entityManager.remove(dispoToDelete);
		// save new ones
		for( PeriodeDisponibilite dispo : listDispos ) {			
			add(dispo);
		}
	}
	
}
