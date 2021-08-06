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
@Table(name="periode_disponibilite")
public class PeriodeDisponibilite implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dispo_id", unique=true, nullable=false)
	private int dispoId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_dispo")
	private Date creationDispo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="debut_periode")
	private Date debutPeriode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fin_periode")
	private Date finPeriode;
	
	@ManyToOne
	@JoinColumn(name="id_terrain")
	private Terrain terrain;
	
	@ManyToOne
	@JoinColumn(name="id_troupeau")
	private Troupeau troupeau;
	
//	@ManyToOne
//	@JoinColumn(name="id_utilisateur")
//	private Utilisateur utilisateur;

	public int getDispoId() {
		return dispoId;
	}

	public void setDispoId(int dispoId) {
		this.dispoId = dispoId;
	}

	public Date getCreationDispo() {
		return creationDispo;
	}

	public void setCreationDispo(Date creationDispo) {
		this.creationDispo = creationDispo;
	}

	public Date getDebutPeriode() {
		return debutPeriode;
	}

	public void setDebutPeriode(Date debutPeriode) {
		this.debutPeriode = debutPeriode;
	}

	public Date getFinPeriode() {
		return finPeriode;
	}

	public void setFinPeriode(Date finPeriode) {
		this.finPeriode = finPeriode;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Troupeau getTroupeau() {
		return troupeau;
	}

	public void setTroupeau(Troupeau troupeau) {
		this.troupeau = troupeau;
	}


}
