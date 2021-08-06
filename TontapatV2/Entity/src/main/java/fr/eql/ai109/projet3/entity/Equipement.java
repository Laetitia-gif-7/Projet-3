package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipement")
public class Equipement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_equipement", unique=true, nullable=false)
	private int idEquipement;
	
	@Column(name="libelle_equipement", length=254)
	private String libelleEquipement;
	
	@ManyToOne
	@JoinColumn(name="id_unite")
	private UniteRef uniteRef;
	
	@OneToMany(mappedBy="equipement")
	private List<QuantiteEquipement> quantiteEquipements;
//
	@OneToMany(mappedBy="equipement")
	private List<QuantiteEquipementPrestation> quantiteEquipementPrestations;

	public int getIdEquipement() {
		return idEquipement;
	}

	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	public String getLibelleEquipement() {
		return libelleEquipement;
	}

	public void setLibelleEquipement(String libelleEquipement) {
		this.libelleEquipement = libelleEquipement;
	}

	public UniteRef getUniteRef() {
		return uniteRef;
	}

	public void setUniteRef(UniteRef uniteRef) {
		this.uniteRef = uniteRef;
	}

	public List<QuantiteEquipement> getQuantiteEquipements() {
		return quantiteEquipements;
	}

	public void setQuantiteEquipements(List<QuantiteEquipement> quantiteEquipements) {
		this.quantiteEquipements = quantiteEquipements;
	}
	
	public List<QuantiteEquipementPrestation> getQuantiteEquipementPrestations() {
		return quantiteEquipementPrestations;
	}

	public void setQuantiteEquipementPrestations(List<QuantiteEquipementPrestation> quantiteEquipementPrestations) {
		this.quantiteEquipementPrestations = quantiteEquipementPrestations;
	}

}
