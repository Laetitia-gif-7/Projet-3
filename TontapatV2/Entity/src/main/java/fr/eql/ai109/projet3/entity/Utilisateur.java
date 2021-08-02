package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.LAZY)
	private List<Terrain> terrains;
	
	// Try with use of default constructor
	
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
	
	/*
	public List<Terrain> getTerrains() {
		return terrains;
	}
	public void setTerrains(List<Terrain> terrains) {
		this.terrains = terrains;
	}
	*/
	
	
	
	
	
	
	 
	
	

}
