package fr.eql.ai109.projet3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.TacheRef;
import fr.eql.ai109.projet3.idao.TacheRefIDao;

@Remote(TacheRefIDao.class)
@Stateless
public class TacheRefDao extends GenericDao<TacheRef> {

}
