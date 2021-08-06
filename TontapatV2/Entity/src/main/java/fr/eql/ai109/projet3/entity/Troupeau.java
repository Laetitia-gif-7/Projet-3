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
@Table(name="troupeau")
public class Troupeau implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_troupeau", unique=true, nullable=false)
	private int idTroupeau;
	
	@Column(name="nom_troupeau", length=254)
	private String nomTroupeau;
	
	@Column(length=254)
	private String description;
	
	@Column(length=254)
	private String photo;
	
	@Column(name="date_creation")
	private Date dateCreation;
	
	@Column(name="date_retrait")
	private Date dateRetrait;
	
	@Column(name="frequence_visite")
	private int frequenceVisite;
	
	@Column(name="cout_par_animal", nullable=true)
	private float coutParAnimal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_utilisateur", nullable=false)
	private Utilisateur utilisateur;
	
	@OneToMany(mappedBy="troupeau")
	private List<CompositionTroupeau> compositionTroupeau;
	
	//bi-directional many-to-one association to Compositiontroupeauprestation
	@OneToMany(mappedBy="troupeau")
	private List<CompositionTroupeauPrestation> compositionTroupeauPrestations;
	
	@ManyToOne(fetch  = FetchType.EAGER)
	@JoinColumn(name="id_motif_retrait")
	private MotifRetraitTroupeau motifRetraitTroupeau;
	
	@OneToMany(mappedBy="troupeau")
	private List<PeriodeDisponibilite> periodeDisponibilites;
	
	
	public List<CompositionTroupeauPrestation> getCompositionTroupeauPrestations() {
		return compositionTroupeauPrestations;
	}

	public void setCompositionTroupeauPrestations(List<CompositionTroupeauPrestation> compositionTroupeauPrestations) {
		this.compositionTroupeauPrestations = compositionTroupeauPrestations;
	}

	public List<PeriodeDisponibilite> getPeriodeDisponibilites() {
		return periodeDisponibilites;
	}

	public void setPeriodeDisponibilites(List<PeriodeDisponibilite> periodeDisponibilites) {
		this.periodeDisponibilites = periodeDisponibilites;
	}

	public MotifRetraitTroupeau getMotifRetraitTroupeau() {
		return motifRetraitTroupeau;
	}

	public void setMotifRetraitTroupeau(MotifRetraitTroupeau motifRetraitTroupeau) {
		this.motifRetraitTroupeau = motifRetraitTroupeau;
	}

	public int getIdTroupeau() {
		return idTroupeau;
	}

	public List<CompositionTroupeau> getCompositionTroupeau() {
		return compositionTroupeau;
	}

	public void setCompositionTroupeau(List<CompositionTroupeau> compositionTroupeau) {
		this.compositionTroupeau = compositionTroupeau;
	}
	
	public List<CompositionTroupeauPrestation> getCompositiontroupeauprestations() {
		return compositionTroupeauPrestations;
	}

	public void setCompositiontroupeauprestations(List<CompositionTroupeauPrestation> compositiontroupeauprestations) {
		this.compositionTroupeauPrestations = compositiontroupeauprestations;
	}

	public void setIdTroupeau(int idTroupeau) {
		this.idTroupeau = idTroupeau;
	}

	public String getNomTroupeau() {
		return nomTroupeau;
	}

	public void setNomTroupeau(String nomTroupeau) {
		this.nomTroupeau = nomTroupeau;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public int getFrequenceVisite() {
		return frequenceVisite;
	}

	public void setFrequenceVisite(int frequenceVisite) {
		this.frequenceVisite = frequenceVisite;
	}

	public float getCoutParAnimal() {
		return coutParAnimal;
	}

	public void setCoutParAnimal(float coutParAnimal) {
		this.coutParAnimal = coutParAnimal;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
