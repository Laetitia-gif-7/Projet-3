package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="terrain")
public class Terrain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_terrain", unique=true, nullable=false)
	private int idTerrain;
	
	@Column(name="nom_terrain", length=254)
	private String nomTerrain;
	
	@Column(length=254)
	private String description;
	
	@Column(precision=10)
	private BigDecimal superficie;
	// private float surface;
	
	@Column(length=254)
	private String rue;
	
	@Column(name="no_rue", length=254)
	private String noRue;
	
	@Column(length=254)
	private String photo;
	
	@Column(length=254)
	private String parcelle;
	
	@Column(precision=10, scale=8)
	private BigDecimal latitude;

	@Column(precision=10, scale=8)
	private BigDecimal longitude;
	
	private byte clos;
	
	@Column(name="date_creation")
	private Date dateCreation;
	
	@Column(name="date_retrait")
	private Date dateRetrait;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_utilisateur", nullable=false)
	private Utilisateur utilisateur;
	
	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name="insee_id")
	private VilleCpRef villeCpRef;
	
	/*
	@ManyToOne(fetch  = FetchType.LAZY)
	@JoinColumn(name="id_motif_retrait_terrain")
	private MotifRetraitTerrain motifRetraitTerrain;
	*/
	
	/*  Préférence d'espece pour le terrain, optionel !
	@ManyToOne
	@JoinColumn(name="espece_id")
	private EspeceRef especePreference;
	*/
}
