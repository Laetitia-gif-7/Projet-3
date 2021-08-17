package fr.eql.ai109.projet3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Tache;
import fr.eql.ai109.projet3.idao.TacheIDao;

@Remote(TacheIDao.class)
@Stateless
public class TacheDao extends GenericDao<Tache> {

}
