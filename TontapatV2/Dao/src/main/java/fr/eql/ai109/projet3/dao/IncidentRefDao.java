package fr.eql.ai109.projet3.dao;


import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.IncidentRef;
import fr.eql.ai109.projet3.idao.IncidentRefIDao;


@Remote(IncidentRefIDao.class)
@Stateless
public class IncidentRefDao extends GenericDao<IncidentRef> {

	
}