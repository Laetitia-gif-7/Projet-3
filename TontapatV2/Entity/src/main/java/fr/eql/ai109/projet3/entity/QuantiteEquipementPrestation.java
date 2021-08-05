package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="quantite_equipement_prestation")
public class QuantiteEquipementPrestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private QuantiteEquipementPrestationPK id;

	private float cout;

	private int quantite;
	
	// do not manange to use em.refresh
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_equipement", nullable=false, insertable=false, updatable=false)
	private Equipement equipement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_prestation", nullable=false, insertable=false, updatable=false)
	private Prestation prestation;

	public QuantiteEquipementPrestationPK getId() {
		return id;
	}

	public void setId(QuantiteEquipementPrestationPK id) {
		this.id = id;
	}

	public float getCout() {
		return cout;
	}

	public void setCout(float cout) {
		this.cout = cout;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	public Prestation getPrestation() {
		return prestation;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

}
