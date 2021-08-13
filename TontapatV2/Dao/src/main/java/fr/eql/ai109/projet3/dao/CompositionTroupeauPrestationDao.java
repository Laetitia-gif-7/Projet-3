package fr.eql.ai109.projet3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.CompositionTroupeauPrestation;
import fr.eql.ai109.projet3.idao.CompositionTroupeauPrestationIDao;

@Remote(CompositionTroupeauPrestationIDao.class)
@Stateless
public class CompositionTroupeauPrestationDao extends GenericDao<CompositionTroupeauPrestation> implements CompositionTroupeauPrestationIDao {

}
