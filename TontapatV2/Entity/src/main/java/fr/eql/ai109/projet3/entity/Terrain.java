package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="terrain")
public class Terrain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_terrain", unique=true, nullable=false)
	private int idTerrain;
	
	@Column(name="nom_terrain", length=254)
	private String nomTerrain;
	
	@Column(length=254)
	private String description;
	
	@Column(precision=10)
	private BigDecimal superficie;
	// private float surface;
	
	@Column(length=254)
	private String rue;
	
	@Column(name="no_rue", length=254)
	private String noRue;
	
	@Column(length=254)
	private String photo;
	
	@Column(length=254)
	private String parcelle;
	
	@Column(precision=10, scale=8)
	private BigDecimal latitude;

	@Column(precision=10, scale=8)
	private BigDecimal longitude;
	
	private byte clos;
	
	@Column(name="date_creation")
	private Date dateCreation;
	
	@Column(name="date_retrait")
	private Date dateRetrait;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_utilisateur", nullable=false)
	private Utilisateur utilisateur;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="insee_id")
	private VilleCpRef villeCp;
	
	@OneToMany( mappedBy="terrain",fetch = FetchType.EAGER)
	Set<Prestation> prestations;
	
	
	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name="id_motif_retrait_terrain")
	private MotifRetraitTerrain motifRetraitTerrain;
	
	
	/*  Préférence d'espece pour le terrain, optionel !
	@ManyToOne
	@JoinColumn(name="espece_id")
	private EspeceRef especePreference;
	*/
	
	public Set<Prestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(Set<Prestation> prestations) {
		this.prestations = prestations;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public int getIdTerrain() {
		return idTerrain;
	}
	
	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}
	public String getNomTerrain() {
		return nomTerrain;
	}
	public void setNomTerrain(String nomTerrain) {
		this.nomTerrain = nomTerrain;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getSuperficie() {
		return superficie;
	}
	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getNoRue() {
		return noRue;
	}
	public void setNoRue(String noRue) {
		this.noRue = noRue;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getParcelle() {
		return parcelle;
	}
	public void setParcelle(String parcelle) {
		this.parcelle = parcelle;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public byte getClos() {
		return clos;
	}
	public void setClos(byte clos) {
		this.clos = clos;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateRetrait() {
		return dateRetrait;
	}
	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
	
	public VilleCpRef getVilleCp() {
		return villeCp;
	}
	public void setVilleCp(VilleCpRef villeCp) {
		this.villeCp = villeCp;
	}

}
