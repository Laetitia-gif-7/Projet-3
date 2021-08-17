package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class ParametresTache implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date debutPlannifiee = new Date();
	private Date finPlanifiee = new Date();
	private Date debutRealisation = new Date();
	private Date finRealisation = new Date();
	private String rapport;
	
	public Date getDebutPlannifiee() {
		return debutPlannifiee;
	}
	public void setDebutPlannifiee(Date debutPlannifiee) {
		this.debutPlannifiee = debutPlannifiee;
	}
	public Date getFinPlanifiee() {
		return finPlanifiee;
	}
	public void setFinPlanifiee(Date finPlanifiee) {
		this.finPlanifiee = finPlanifiee;
	}
	public Date getDebutRealisation() {
		return debutRealisation;
	}
	public void setDebutRealisation(Date debutRealisation) {
		this.debutRealisation = debutRealisation;
	}
	public Date getFinRealisation() {
		return finRealisation;
	}
	public void setFinRealisation(Date finRealisation) {
		this.finRealisation = finRealisation;
	}
	public String getRapport() {
		return rapport;
	}
	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

}
