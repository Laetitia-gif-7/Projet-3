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
@Table(name="tache_ref")
public class TacheRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tache_ref", unique=true, nullable=false)
	private int idTacheRef;
	
	@Column(name="libelle_tache", length=254)
	private String libelleTache;

	@OneToMany(mappedBy="tacheRef")
	private List<Tache> taches;

	public int getIdTacheRef() {
		return idTacheRef;
	}

	public void setIdTacheRef(int idTacheRef) {
		this.idTacheRef = idTacheRef;
	}

	public String getLibelleTache() {
		return libelleTache;
	}

	public void setLibelleTache(String libelleTache) {
		this.libelleTache = libelleTache;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	
}
