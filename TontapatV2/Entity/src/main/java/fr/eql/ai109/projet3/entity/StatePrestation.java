package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface StatePrestation extends Serializable {
	
	void valide(PrestationBU p);
	void annule(PrestationBU p);
	void setStateName(PrestationBU p);
	void setTemplateString(PrestationBU p);
	//void valideAvecDate(PrestationBU p, LocalDateTime date);
}
