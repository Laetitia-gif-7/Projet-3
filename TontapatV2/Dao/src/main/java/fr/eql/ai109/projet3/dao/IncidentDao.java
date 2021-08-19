package fr.eql.ai109.projet3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Incident;

import fr.eql.ai109.projet3.idao.IncidentIDao;

@Remote(IncidentIDao.class)
@Stateless
public class IncidentDao extends GenericDao<Incident> implements IncidentIDao {

}
