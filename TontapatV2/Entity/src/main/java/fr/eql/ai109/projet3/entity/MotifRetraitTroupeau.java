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
	@Column(name="id_motif_retrait_troupeau", unique=true, nullable=false)
	private int idMotifRetraitTroupeau;

	@Column(name="libelle_motif_retrait_troupeau", length=254)
	private String libelleMotifRetraitTroupeau;

	@OneToMany(mappedBy="motifRetraitTroupeau")
	private List<Troupeau> troupeaux;

	public int getId_MotifRetraitTroupeau() {
		return idMotifRetraitTroupeau;
	}

	public void setId_MotifRetraitTroupeau(int idMotifRetraitTroupeau) {
		this.idMotifRetraitTroupeau = idMotifRetraitTroupeau;
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
