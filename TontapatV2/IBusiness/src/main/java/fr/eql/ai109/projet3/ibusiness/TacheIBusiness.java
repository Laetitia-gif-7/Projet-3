package fr.eql.ai109.projet3.ibusiness;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Tache;
import fr.eql.ai109.projet3.entity.TacheRef;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface TacheIBusiness {
	
	List<TacheRef> findAllTacheRefs();

	public Tache AjouterTache(Prestation prestation, Utilisateur utilisateurConnecte, TacheRef tacheRef, Date debutPlanifiee, Date finPlanifiee);
	public Tache ValiderTache(int idTache, Utilisateur utilisateurConnecte, Date debutRealisation, Date finRealisation, String rapport);
	
}
