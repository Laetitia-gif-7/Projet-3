package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="annulation_tache")
public class AnnulationTache implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_annulation", unique=true, nullable=false)
	private int idAnnulation;
	
	@Column(name="libelle_annulation_tache", length=254)
	private String libelleAnnulationTache;
	
	@OneToMany(mappedBy="annulationTache")
	private List<Tache> taches;

	public int getIdAnnulation() {
		return idAnnulation;
	}

	public void setIdAnnulation(int idAnnulation) {
		this.idAnnulation = idAnnulation;
	}

	public String getLibelleAnnulationTache() {
		return libelleAnnulationTache;
	}

	public void setLibelleAnnulationTache(String libelleAnnulationTache) {
		this.libelleAnnulationTache = libelleAnnulationTache;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

}
