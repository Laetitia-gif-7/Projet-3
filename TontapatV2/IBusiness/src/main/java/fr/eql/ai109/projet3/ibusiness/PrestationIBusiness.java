package fr.eql.ai109.projet3.ibusiness;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;

public interface PrestationIBusiness {
	
	Map<Integer,PrestationBU> findPrestationsByUtilisateur(Utilisateur utilisateur);
	// to create a prestation
	void createPrestationEleveur(Utilisateur utilisateur,int idTerrain,int idTroupeau,Date dateDebut,Date dateFin);
	void createPrestationClient(Utilisateur utilisateurConnecte, ParametresReservationPrestation prp,
			int idTerrain, int idTroupeau );
	void ReservePrestationBerger(int idPrestation, Utilisateur berger);
	
	void valideEtatDesLieux(int idPrestation, Utilisateur utilisateur);
	void valideContrat(int idPrestation, Utilisateur utilisateur);
	
	// prestationbu contains prestation + additional data. Can send back to the view for updating it
	PrestationBU valide(PrestationBU pbu);
	PrestationBU valide(PrestationBU pbu, Utilisateur utilisateur);
	PrestationBU valide(PrestationBU prestationBU, Date date, Utilisateur utilisateurConnecte);
	//PrestationBU valideAvecDate(PrestationBU prestationBU, LocalDateTime date);
	
	PrestationBU annule(int idPrestation);
	// void valide( Date...)
	
}
