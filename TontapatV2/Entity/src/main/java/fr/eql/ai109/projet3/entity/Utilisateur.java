package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_utilisateur")
	private Integer id;
	private String nom;
	private String prenom;
	@Column(name="date_naissance")
	private Date dateNaissance;
	private String email;
	@Column(name="mot_de_passe")
	private String motDePasse;
	@Column(name="no_rue")
	private String noRue;
	private String rue;
	private String telephone;
	private String siret;
	
	private Double latitude;
	private Double longitude;
	/*
	@Column(name="date_desinscription")
	private String dateDesinscription;
	
	@ManyToOne
	@JoinColumn(name = "id_motif_desinscription", referencedColumnName = "id_motif_desinscription")
	private String motifDesinscription;
	
	@OneToOne
	@JoinColumn(name= "id_metier", referencedColumnName = "id_metier")
	private String metier;
	*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="insee_id", nullable=false)
	private VilleCpRef villeCp;
	
	 //TODO: check if necessary
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.LAZY)
	private Set<Terrain> terrains;

	@OneToMany(mappedBy="utilisateur", fetch = FetchType.LAZY)
	private Set<Troupeau> troupeaux;
	
	@OneToMany(mappedBy="idDerniereProposition", fetch = FetchType.LAZY)
	private List<Prestation> prestationDernieresPropositions;
	
	@OneToMany(mappedBy="initiateurPrestation", fetch = FetchType.LAZY)
	private List<Prestation> prestationInitiees;
	
	// Relation Tables:  User<-> Prestation  ONLY for bergers, name maybe confusing
	@OneToMany(mappedBy="berger", fetch = FetchType.LAZY)
	private List<Prestation> bergers;
	
	@OneToMany(mappedBy="declarateur")
	private List<Incident> incidentsEnCours;
	
	@OneToMany(mappedBy="finDeclarateur")
	private List<Incident> incidentsClotures;
	
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.LAZY)
	private List<Evaluation> evaluation;
	

	public List<Evaluation> getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(List<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}
	public List<Prestation> getPrestationInitiees() {
		return prestationInitiees;
	}
	public void setPrestationInitiees(List<Prestation> prestationInitiees) {
		this.prestationInitiees = prestationInitiees;
	}
	public List<Prestation> getBergers() {
		return bergers;
	}
	public void setBergers(List<Prestation> bergers) {
		this.bergers = bergers;
	}
	public List<Incident> getIncidentsEnCours() {
		return incidentsEnCours;
	}
	public void setIncidentsEnCours(List<Incident> incidentsEnCours) {
		this.incidentsEnCours = incidentsEnCours;
	}
	public List<Incident> getIncidentsClotures() {
		return incidentsClotures;
	}
	public void setIncidentsClotures(List<Incident> incidentsClotures) {
		this.incidentsClotures = incidentsClotures;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getNoRue() {
		return noRue;
	}
	public void setNoRue(String noRue) {
		this.noRue = noRue;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public VilleCpRef getVilleCp() {
		return villeCp;
	}
	
	public void setVilleCp(VilleCpRef villeCp) {
		this.villeCp = villeCp;
	}
	
	public Set<Terrain> getTerrains() {
		return terrains;
	}
	public void setTerrains(Set<Terrain> terrains) {
		this.terrains = terrains;
	}
	public Set<Troupeau> getTroupeaux() {
		return troupeaux;
	}
	public void setTroupeaux(Set<Troupeau> troupeaux) {
		this.troupeaux = troupeaux;
	}
	public List<Prestation> getPrestationDernieresPropositions() {
		return prestationDernieresPropositions;
	}
	public void setPrestationDernieresPropositions(List<Prestation> prestationDernieresPropositions) {
		this.prestationDernieresPropositions = prestationDernieresPropositions;
	}
}
