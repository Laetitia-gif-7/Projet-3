package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name="tache")
public class Tache implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tache", unique=true, nullable=false)
	private int idTache;
	
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_tache")
	private LocalDateTime creationTache;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_annulation")
	private Date dateAnnulation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="debut_plannifiee")
	private Date debutPlannifiee;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="debut_realisation")
	private Date debutRealisation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fin_planifiee")
	private Date finPlanifiee;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fin_realisation")
	private Date finRealisation;
	
	@Column(length=254)
	private String rapport;
	
	@ManyToOne
	@JoinColumn(name="id_prestation")
	private Prestation prestation;
	
	@ManyToOne
	@JoinColumn(name="id_tache_ref", nullable=false)
	private TacheRef tacheRef;
	
	@ManyToOne
	@JoinColumn(name="id_annulation")
	private AnnulationTache annulationTache;

	public int getIdTache() {
		return idTache;
	}

	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}

	public LocalDateTime getCreationTache() {
		return creationTache;
	}

	public void setCreationTache(LocalDateTime creationTache) {
		this.creationTache = creationTache;
	}

	public Date getDateAnnulation() {
		return dateAnnulation;
	}

	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}

	public Date getDebutPlannifiee() {
		return debutPlannifiee;
	}

	public void setDebutPlannifiee(Date debutPlannifiee) {
		this.debutPlannifiee = debutPlannifiee;
	}

	public Date getDebutRealisation() {
		return debutRealisation;
	}

	public void setDebutRealisation(Date debutRealisation) {
		this.debutRealisation = debutRealisation;
	}

	public Date getFinPlanifiee() {
		return finPlanifiee;
	}

	public void setFinPlanifiee(Date finPlanifiee) {
		this.finPlanifiee = finPlanifiee;
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

	public Prestation getPrestation() {
		return prestation;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

	public TacheRef getTacheRef() {
		return tacheRef;
	}

	public void setTacheRef(TacheRef tacheRef) {
		this.tacheRef = tacheRef;
	}

	public AnnulationTache getAnnulationTache() {
		return annulationTache;
	}

	public void setAnnulationTache(AnnulationTache annulationTache) {
		this.annulationTache = annulationTache;
	}


}
