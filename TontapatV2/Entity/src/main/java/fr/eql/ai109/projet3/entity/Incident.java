package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="incident")
public class Incident implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_incident", unique=true, nullable=false)
	private int idIncident;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_declaration")
	private Date dateDeclaration;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_fin_incident")
	private Date dateFinIncident;
	
	@Column(length=254)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_incident_ref", nullable=false)
	private IncidentRef incidentRef;
	
	@ManyToOne
	@JoinColumn(name="id_prestation")
	private Prestation prestation;
	

	@ManyToOne
	@JoinColumn(name="id_declarateur")
	private Utilisateur declarateur;
	
	@ManyToOne
	@JoinColumn(name="id_fin_declarateur")
	private Utilisateur finDeclarateur;

	
	public int getIdIncident() {
		return idIncident;
	}

	public void setIdIncident(int idIncident) {
		this.idIncident = idIncident;
	}

	public Date getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public Date getDateFinIncident() {
		return dateFinIncident;
	}

	public void setDateFinIncident(Date dateFinIncident) {
		this.dateFinIncident = dateFinIncident;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IncidentRef getIncidentRef() {
		return incidentRef;
	}

	public void setIncidentRef(IncidentRef incidentRef) {
		this.incidentRef = incidentRef;
	}

	public Prestation getPrestation() {
		return prestation;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

	public Utilisateur getDeclarateur() {
		return declarateur;
	}

	public void setDeclarateur(Utilisateur declarateur) {
		this.declarateur = declarateur;
	}

	public Utilisateur getFinDeclarateur() {
		return finDeclarateur;
	}

	public void setFinDeclarateur(Utilisateur finDeclarateur) {
		this.finDeclarateur = finDeclarateur;
	}

}
