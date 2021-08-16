package fr.eql.ai109.projet3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.RaceRef;
import fr.eql.ai109.projet3.idao.RaceRefIDao;

@Remote(RaceRefIDao.class)
@Stateless
public class RaceRefDao extends GenericDao<RaceRef> implements RaceRefIDao {

}
