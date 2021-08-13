package fr.eql.ai109.projet3.idao;

import java.util.Date;
import java.util.List;

import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;

public interface PrestationIDao extends GenericIDao<Prestation> {
	
	List<Prestation> getPrestationsByUser(Utilisateur utilisateur);
	// Recuperes les troupeaux impliqués dans des prestations à certaines dates, pour connaitre les animaux disponibles
	List<Prestation> prestationsEnCoursPourTroupeauId(int idTroupeau, Date dateDebut, Date dateFin);
	// Meme, mais la somme est faite directement dans la requête
	int nbAnimauxEnPrestationPourTroupeauId(int idTroupeau, Date dateDebut, Date dateFin);
}
