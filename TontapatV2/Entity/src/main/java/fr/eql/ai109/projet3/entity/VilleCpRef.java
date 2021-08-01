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
@Table(name="ville_cp_ref")
public class VilleCpRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="insee_id", nullable=false)
	private int inseeId;
	
	@Column(name="code_postal", nullable=false)
	private int codePostal;
	
	@Column(nullable=false, length=254)
	private String ville;
	
	@Column(length=254)
	private String departement;
	
	@OneToMany(mappedBy="ville", fetch = FetchType.LAZY)
	private List<Utilisateur> utilisateurs;
	
	public int getInseeId() {
		return inseeId;
	}

	public void setInseeId(int inseeId) {
		this.inseeId = inseeId;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
}
