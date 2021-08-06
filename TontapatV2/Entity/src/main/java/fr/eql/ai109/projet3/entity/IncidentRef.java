package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="incident_ref")
public class IncidentRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_incident_ref", unique=true, nullable=false)
	private int idIncidentRef;
	
	@Column(name="libelle_incident", length=254)
	private String libelleIncident;
	
	@OneToMany(mappedBy="incidentRef")
	private List<Incident> incidents;

	public int getIdIncidentRef() {
		return idIncidentRef;
	}

	public void setIdIncidentRef(int idIncidentRef) {
		this.idIncidentRef = idIncidentRef;
	}

	public String getLibelleIncident() {
		return libelleIncident;
	}

	public void setLibelleIncident(String libelleIncident) {
		this.libelleIncident = libelleIncident;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

}
