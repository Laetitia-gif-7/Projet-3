package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.eql.ai109.projet3.dao.utils.utils;
import fr.eql.ai109.projet3.entity.CompositionTroupeauPrestation;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.QuantiteEquipementPrestation;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.idao.PrestationIDao;

@Remote(PrestationIDao.class)
@Stateless
public class PrestationDao extends GenericDao<Prestation> implements PrestationIDao {
	
	@PostConstruct
	void init() {
		System.out.println("init PrestationDao");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy PrestationDao");
	}
	
	@Override
	public List<Prestation> getPrestationsByUser(Utilisateur utilisateur) {
		List<Prestation> prestas = new ArrayList<Prestation>();

		TypedQuery<Prestation> query = entityManager.createQuery(
			"SELECT DISTINCT p "
					+ "FROM Prestation p "
					+ "JOIN FETCH p.compositionTroupeauPrestations ctp "
					+ "WHERE p.terrain.utilisateur =:utilisateurParam "
					+ "	  OR ctp.troupeau.utilisateur =:utilisateurParam2 "
					+ "   OR p.berger =:utilisateurParam3 ",Prestation.class);
			/* not working as expected, keep to retest
			"SELECT DISTINCT p "
					+ "FROM Prestation p "
					+ "JOIN p.compositionTroupeauPrestations ctp "
					+ "JOIN FETCH p.quantiteEquipementPrestations.equipement qep "
					+ "WHERE p.terrain.utilisateur =:utilisateurParam "
					+ "	  OR ctp.troupeau.utilisateur =:utilisateurParam2 ",Prestation.class);	
			*/
		query.setParameter("utilisateurParam", utilisateur);
		query.setParameter("utilisateurParam2", utilisateur);
		query.setParameter("utilisateurParam3", utilisateur);
		prestas = query.getResultList();
		// do not manage to load all data. to test an other SQL request all Equipements of  each prestations ??
		for( Prestation presta : prestas) {
			for( int i=0; i<presta.getQuantiteEquipementPrestations().size(); i++) {
				entityManager.refresh(presta.getQuantiteEquipementPrestations().get(i));
			}
			for( int i=0; i<presta.getEvaluations().size(); i++) {
				entityManager.refresh(presta.getEvaluations().get(i));
			}
			for( int i=0; i<presta.getIncidents().size(); i++) {
				entityManager.refresh(presta.getIncidents().get(i));
			}
		}
		return prestas;
	}
	
	public List<Prestation> prestationsEnCoursPourTroupeauId(int idTroupeau, Date dateDebut, Date dateFin) {
		
		TypedQuery<Prestation> query = entityManager.createQuery(
				"SELECT DISTINCT prest "
				+ "FROM Troupeau tr "
				+ "JOIN tr.compositionTroupeauPrestations ctp "
				+ "JOIN ctp.prestation prest "
				+ "WHERE not( (prest.finPrestation < :paramDateDebut) or (prest.debutPrestation > :paramDateFin ) ) "
				+ "AND tr.idTroupeau = :paramTroupeauId ", Prestation.class);
		
		query.setParameter("paramTroupeauId", idTroupeau);
		query.setParameter("paramDateDebut", utils.convertToLocalDateTimeViaInstant(dateDebut) );
		query.setParameter("paramDateFin", utils.convertToLocalDateTimeViaInstant(dateFin) );
		
		List<Prestation> listPrestations = query.getResultList();
		
		// solve lazy initialisation problems in getting access to ctp
		for(Prestation presta : listPrestations)
			for(CompositionTroupeauPrestation ctp : presta.getCompositionTroupeauPrestations())
				entityManager.refresh(ctp);
		
		return listPrestations;
	}

	@Override
	public int nbAnimauxEnPrestationPourTroupeauId(int idTroupeau, Date dateDebut, Date dateFin) {
		
		TypedQuery<Long> query = entityManager.createQuery(
				"SELECT sum(ctp.nbAnimaux) "
				+ "FROM Troupeau tr "
				+ "JOIN tr.compositionTroupeauPrestations ctp "
				+ "JOIN ctp.prestation prest "
				+ "WHERE not( (prest.finPrestation < :paramDateDebut) or (prest.debutPrestation > :paramDateFin ) ) "
				+ "AND tr.idTroupeau = :paramTroupeauId ", Long.class);
		
		query.setParameter("paramTroupeauId", idTroupeau);
		query.setParameter("paramDateDebut", utils.convertToLocalDateTimeViaInstant(dateDebut) );
		query.setParameter("paramDateFin", utils.convertToLocalDateTimeViaInstant(dateFin) );
		long total = query.getSingleResult();
		return (int)total;
	}
	
	@Override
	public void enregistreEquipementSupplementaires(List<QuantiteEquipementPrestation>  listEquipements) {
		for(QuantiteEquipementPrestation equip : listEquipements)
			entityManager.persist(equip);
	}
	
	
	@Override
	public void enregistreCompoTroupeauPresta(List<CompositionTroupeauPrestation>  listCompoTroupo) {
		for(CompositionTroupeauPrestation ctp : listCompoTroupo)
			entityManager.persist(ctp);
	}
	
	public List<Prestation> allPrestationWhithCtp(){
		List<Prestation> prestations = getAll();
		List<CompositionTroupeauPrestation> cpt;
		Troupeau troupeau;
		Terrain terrain;
		Utilisateur eleveur;
		Utilisateur client;
		for (Prestation prestation : prestations) {
			terrain = prestation.getTerrain();
			client = terrain.getUtilisateur();
			cpt = prestation.getCompositionTroupeauPrestations();
			troupeau = cpt.get(0).getTroupeau();
			eleveur = troupeau.getUtilisateur();
		}
		return prestations;
	}
	
	public Prestation prestationWhithCtp(int idPrestation) {
		Prestation prestation = getById(idPrestation);
		Terrain terrain = prestation.getTerrain();
		Utilisateur client = terrain.getUtilisateur();
		//terrain.setUtilisateur(client);
		List<CompositionTroupeauPrestation> cpt = prestation.getCompositionTroupeauPrestations();
		Troupeau troupeau = cpt.get(0).getTroupeau();
		Utilisateur eleveur = troupeau.getUtilisateur();
		//troupeau.setUtilisateur(eleveur);
		//cpt.get(0).setTroupeau(troupeau);
		//prestation.setTerrain(terrain);
		//prestation.setCompositionTroupeauPrestations(cpt);
		return prestation;
	}
}


