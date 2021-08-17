package fr.eql.ai109.projet3.business;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;


import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Tache;
import fr.eql.ai109.projet3.entity.TacheRef;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.TacheIBusiness;
import fr.eql.ai109.projet3.idao.TacheIDao;
import fr.eql.ai109.projet3.idao.TacheRefIDao;


@Remote(TacheIBusiness.class)
@Stateless
public class TacheBusiness implements TacheIBusiness {

	@EJB
	TacheRefIDao tacheRefIDao; 
	
	@EJB
	TacheIDao tacheIDao;
	
	@Override
	public List<TacheRef> findAllTacheRefs() {
		
		return tacheRefIDao.getAll();
	}

	@Override
	public Tache AjouterTache(Prestation prestation, Utilisateur utilisateurConnecte, TacheRef tacheRef, Date debutPlanifiee, Date finPlanifiee) {

		Tache tache = new Tache();	
		//tache.set(utilisateurConnecte);
		tache.setTacheRef(tacheRef);
		tache.setPrestation(prestation);
		tache.setCreationTache(LocalDateTime.now());
		tache.setDebutPlannifiee(debutPlanifiee);
		tache.setFinPlanifiee(finPlanifiee);
		tache = tacheIDao.add(tache);
		
		return tache;
		
	}

	@Override
	public Tache ValiderTache(int idTache, Utilisateur utilisateurConnecte, Date debutRealisation, Date finRealisation, String rapport) {
		
		Tache tache = tacheIDao.getById(idTache);
		tache.setDebutRealisation(debutRealisation);
		tache.setFinRealisation(finRealisation);
		tache.setRapport(rapport);
		tache = tacheIDao.update(tache);
		return tache;
	}


}
