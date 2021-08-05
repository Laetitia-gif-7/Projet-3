package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "race_ref")
public class RaceRef implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_race", unique=true, nullable=false)
	private int idRace;
	
	@Column(name="libelle_race", length=254)
	private String libelleRace;
	
	private float ugb;
	
//	@ManyToOne
//	@JoinColumn(name="espece_id", nullable=false)
//	private EspeceRef especeRef;
//
	@ManyToMany
	@JoinTable(
		name="association_race_morpho"
		, joinColumns={
			@JoinColumn(name="id_race", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_morphologie", nullable=false)
			}
		)
	private List<MorphologieRef> morphologieRefs;
//	
	@ManyToMany
	@JoinTable(
		name="association_race_vegetation"
		, joinColumns={
			@JoinColumn(name="id_race", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_vegetation", nullable=false)
			}
		)
	private List<VegetationRef> vegetationRef;
	
//		
		@OneToMany(mappedBy="raceRef")
		private List<CompositionTroupeau> compositionTroupeaux;
//
//		
//		@OneToMany(mappedBy="raceRef")
//		private List<Compositiontroupeauprestation> compositiontroupeauprestations;
//

		public int getIdRace() {
			return idRace;
		}


		public void setIdRace(int idRace) {
			this.idRace = idRace;
		}


		public String getLibelleRace() {
			return libelleRace;
		}


		public void setLibelleRace(String libelleRace) {
			this.libelleRace = libelleRace;
		}


		public float getUgb() {
			return ugb;
		}


		public void setUgb(float ugb) {
			this.ugb = ugb;
		}


//		public EspeceRef getEspeceRef() {
//			return especeRef;
//		}
//
//
//		public void setEspeceRef(EspeceRef especeRef) {
//			this.especeRef = especeRef;
//		}
//
//
//		public List<MorphologieRef> getMorphologieRefs() {
//			return morphologieRefs;
//		}
//
//
//		public void setMorphologieRefs(List<MorphologieRef> morphologieRefs) {
//			this.morphologieRefs = morphologieRefs;
//		}
//
//
//		public List<Compositiontroupeau> getCompositiontroupeaux() {
//			return compositiontroupeaux;
//		}
//
//
//		public void setCompositiontroupeaux(List<Compositiontroupeau> compositiontroupeaux) {
//			this.compositiontroupeaux = compositiontroupeaux;
//		}
//
//
//		public List<VegetationRef> getVegetationRefs() {
//			return vegetationRefs;
//		}
//
//
//		public void setVegetationRefs(List<VegetationRef> vegetationRefs) {
//			this.vegetationRefs = vegetationRefs;
//		}
//
//
//		public List<Compositiontroupeau> getCompositiontroupeaux() {
//			return compositiontroupeaux;
//		}
//
//
//		public void setCompositiontroupeaux(List<Compositiontroupeau> compositiontroupeaux) {
//			this.compositiontroupeaux = compositiontroupeaux;
//		}
//
//
//		public List<Compositiontroupeauprestation> getCompositiontroupeauprestations() {
//			return compositiontroupeauprestations;
//		}
//
//
//		public void setCompositiontroupeauprestations(List<Compositiontroupeauprestation> compositiontroupeauprestations) {
//			this.compositiontroupeauprestations = compositiontroupeauprestations;
//		}
		
		
		

}
