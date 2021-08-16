package fr.eql.ai109.projet3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.idao.EquipementIDao;

@Remote(EquipementIDao.class)
@Stateless
public class EquipementDao extends GenericDao<Equipement> {

}
