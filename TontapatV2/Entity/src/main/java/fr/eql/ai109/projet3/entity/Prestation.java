package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
//import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
@Table(name="prestation")
public class Prestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<QuantiteEquipementPrestation> getQuantiteEquipementPrestations() {
		return quantiteEquipementPrestations;
	}

	public void setQuantiteEquipementPrestations(List<QuantiteEquipementPrestation> quantiteEquipementPrestations) {
		this.quantiteEquipementPrestations = quantiteEquipementPrestations;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestation", unique=true, nullable=false)
	private int idPrestation;
	
	@Column(name="cout_prestation")
	private float coutPrestation;
	
	@Column(name="besoin_berger")
	private boolean besoinBerger;
	
	@Column(name="numero_prestation", length=254)
	private String numeroPrestation;
	
	@Column(name="reservation")
	private LocalDateTime reservation;
	
	@Basic
	private LocalDateTime confirmation;
	
	@Column(name="debut_prestation")
	private LocalDateTime debutPrestation;
	
	@Column(name="fin_prestation")
	private LocalDateTime finPrestation;
	
	@Column(name="premiere_visite")
	private LocalDateTime premiereVisite;
	
	@Column(name="acceptation_eleveur")
	private LocalDateTime acceptationEleveur;
	
	@Column(name="confirmation_berger")
	private LocalDateTime confirmationBerger;
	
	@Column(name="etat_lieu_client")
	private LocalDateTime etatLieuClient;

	@Column(name="etat_lieu_eleveur")
	private LocalDateTime etatLieuEleveur;
	
	@Column(name="etat_lieu_berger")
	private LocalDateTime etatLieuBerger;

	@Column(name="contrat_client")
	private LocalDateTime contratClient;
	
	@Column(name="contrat_eleveur")
	private LocalDateTime contratEleveur;
	
	@Column(name="contrat_berger")
	private LocalDateTime contratBerger;
	
	@Column(name="date_annullation_prestation")
	private LocalDateTime annullationPrestation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_utilisateur_initiateur")
	private Utilisateur initiateurPrestation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_utilisateur_berger")
	private Utilisateur berger;
	
	@ManyToOne //( fetch = FetchType.LAZY)  it is a simple object
	@JoinColumn(name="id_terrain", nullable=false)
	private Terrain terrain;
	
	/* Troupeau more complex with compo_troupeau_presta */
	@OneToMany(mappedBy="prestation")
	private List<CompositionTroupeauPrestation> compositionTroupeauPrestations;
	
	// Lazy by default, need only for compute price full details
	@OneToMany(mappedBy="prestation")
	private List<QuantiteEquipementPrestation> quantiteEquipementPrestations;
	
	@OneToMany(mappedBy="prestation")
	private List<Incident> incidents;
	
	@OneToMany(mappedBy="prestation")
	private List<Evaluation> evaluations;
	
	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public int getIdPrestation() {
		return idPrestation;
	}

	public void setIdPrestation(int idPrestation) {
		this.idPrestation = idPrestation;
	}

	public float getCoutPrestation() {
		return coutPrestation;
	}

	public void setCoutPrestation(float coutPrestation) {
		this.coutPrestation = coutPrestation;
	}

	public boolean isBesoinBerger() {
		return besoinBerger;
	}

	public void setBesoinBerger(boolean besoinBerger) {
		this.besoinBerger = besoinBerger;
	}

	public String getNumeroPrestation() {
		return numeroPrestation;
	}

	public void setNumeroPrestation(String numeroPrestation) {
		this.numeroPrestation = numeroPrestation;
	}

	public LocalDateTime getReservation() {
		return reservation;
	}

	public void setReservation(LocalDateTime reservation) {
		this.reservation = reservation;
	}

	public LocalDateTime getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(LocalDateTime confirmation) {
		this.confirmation = confirmation;
	}

	public LocalDateTime getDebutPrestation() {
		return debutPrestation;
	}

	public void setDebutPrestation(LocalDateTime debutPrestation) {
		this.debutPrestation = debutPrestation;
	}

	public LocalDateTime getFinPrestation() {
		return finPrestation;
	}

	public void setFinPrestation(LocalDateTime finPrestation) {
		this.finPrestation = finPrestation;
	}

	public LocalDateTime getPremiereVisite() {
		return premiereVisite;
	}

	public void setPremiereVisite(LocalDateTime premiereVisite) {
		this.premiereVisite = premiereVisite;
	}

	public LocalDateTime getAcceptationEleveur() {
		return acceptationEleveur;
	}

	public void setAcceptationEleveur(LocalDateTime acceptationEleveur) {
		this.acceptationEleveur = acceptationEleveur;
	}

	public LocalDateTime getConfirmationBerger() {
		return confirmationBerger;
	}

	public void setConfirmationBerger(LocalDateTime confirmationBerger) {
		this.confirmationBerger = confirmationBerger;
	}

	public LocalDateTime getEtatLieuClient() {
		return etatLieuClient;
	}

	public void setEtatLieuClient(LocalDateTime etatLieuClient) {
		this.etatLieuClient = etatLieuClient;
	}

	public LocalDateTime getEtatLieuEleveur() {
		return etatLieuEleveur;
	}

	public void setEtatLieuEleveur(LocalDateTime etatLieuEleveur) {
		this.etatLieuEleveur = etatLieuEleveur;
	}

	public LocalDateTime getEtatLieuBerger() {
		return etatLieuBerger;
	}

	public void setEtatLieuBerger(LocalDateTime etatLieuBerger) {
		this.etatLieuBerger = etatLieuBerger;
	}

	public LocalDateTime getContratClient() {
		return contratClient;
	}

	public void setContratClient(LocalDateTime contratClient) {
		this.contratClient = contratClient;
	}

	public LocalDateTime getContratEleveur() {
		return contratEleveur;
	}

	public void setContratEleveur(LocalDateTime contratEleveur) {
		this.contratEleveur = contratEleveur;
	}

	public LocalDateTime getContratBerger() {
		return contratBerger;
	}

	public void setContratBerger(LocalDateTime contratBerger) {
		this.contratBerger = contratBerger;
	}

	public LocalDateTime getAnnullationPrestation() {
		return annullationPrestation;
	}

	public void setAnnullationPrestation(LocalDateTime annullationPrestation) {
		this.annullationPrestation = annullationPrestation;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Utilisateur getInitiateurPrestation() {
		return initiateurPrestation;
	}

	public void setInitiateurPrestation(Utilisateur initiateurPrestation) {
		this.initiateurPrestation = initiateurPrestation;
	}

	public Utilisateur getBerger() {
		return berger;
	}

	public void setBerger(Utilisateur berger) {
		this.berger = berger;
	}
	
	public List<CompositionTroupeauPrestation> getCompositionTroupeauPrestations() {
		return compositionTroupeauPrestations;
	}

	public void setCompositionTroupeauPrestations(List<CompositionTroupeauPrestation> compositionTroupeauPrestations) {
		this.compositionTroupeauPrestations = compositionTroupeauPrestations;
	}

}
