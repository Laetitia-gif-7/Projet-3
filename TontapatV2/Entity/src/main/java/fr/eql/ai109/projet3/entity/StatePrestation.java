package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface StatePrestation extends Serializable {
	
	void valide(PrestationBU p);
	void valide(PrestationBU p, Utilisateur utilisateur);
	void valideAvecDate(PrestationBU p, Utilisateur utilisateur, LocalDateTime date);
	void annule(PrestationBU p);
	void setStateName(PrestationBU p);
	void setTemplateString(PrestationBU p);
}
