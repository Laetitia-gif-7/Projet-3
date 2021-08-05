package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="unite_ref")
public class UniteRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unite", unique=true, nullable=false)
	private int idUnite;
	
	@Column(name="libelle_unite", length=254)
	private String libelleUnite;
	
	@OneToMany(mappedBy="uniteRef", fetch = FetchType.LAZY)
	private List<Equipement> equipements;

	public int getIdUnite() {
		return idUnite;
	}

	public void setIdUnite(int idUnite) {
		this.idUnite = idUnite;
	}

	public String getLibelleUnite() {
		return libelleUnite;
	}

	public void setLibelleUnite(String libelleUnite) {
		this.libelleUnite = libelleUnite;
	}

	public List<Equipement> getEquipments() {
		return equipements;
	}

	public void setEquipments(List<Equipement> equipements) {
		this.equipements = equipements;
	}

}
