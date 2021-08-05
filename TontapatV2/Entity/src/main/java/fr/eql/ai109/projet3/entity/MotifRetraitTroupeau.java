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
@Table(name="motif_retrait_troupeau")
public class MotifRetraitTroupeau implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_MotifRetraitTroupeau", unique=true, nullable=false)
	private int id_MotifRetraitTroupeau;

	@Column(name="libelle_MotifRetraitTroupeau", length=254)
	private String libelleMotifRetraitTroupeau;

	@OneToMany(mappedBy="motifRetraitTroupeau")
	private List<Troupeau> troupeaux;

	public int getId_MotifRetraitTroupeau() {
		return id_MotifRetraitTroupeau;
	}

	public void setId_MotifRetraitTroupeau(int id_MotifRetraitTroupeau) {
		this.id_MotifRetraitTroupeau = id_MotifRetraitTroupeau;
	}

	public String getLibelleMotifRetraitTroupeau() {
		return libelleMotifRetraitTroupeau;
	}

	public void setLibelleMotifRetraitTroupeau(String libelleMotifRetraitTroupeau) {
		this.libelleMotifRetraitTroupeau = libelleMotifRetraitTroupeau;
	}

	public List<Troupeau> getTroupeaux() {
		return troupeaux;
	}

	public void setTroupeaux(List<Troupeau> troupeaux) {
		this.troupeaux = troupeaux;
	}
	
	

}
