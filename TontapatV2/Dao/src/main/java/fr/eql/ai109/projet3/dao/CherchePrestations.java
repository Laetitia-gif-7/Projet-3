package fr.eql.ai109.projet3.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.eql.ai109.projet3.entity.CompositionTroupeau;
import fr.eql.ai109.projet3.entity.MorphologieRef;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.VegetationRef;
import fr.eql.ai109.projet3.entity.dto.TroupeauTrouveApresRechercheDTO;
import fr.eql.ai109.projet3.idao.CherchePrestationIDao;

@Remote(CherchePrestationIDao.class)
@Stateless
public class CherchePrestations implements CherchePrestationIDao {

	@PersistenceContext(unitName = "PUTontapatV2")
	protected EntityManager entityManager;
	
	public List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles(Terrain terrain) {
		List<TroupeauTrouveApresRechercheDTO> correspondances = new ArrayList<>();
		
		List<Object[]> temp_correspondances;
		
		
		
		Query query = entityManager.createQuery(
				"SELECT tr,  "
				+ "CASE WHEN ( pd.debutPeriode > pd2.debutPeriode ) " 
				+ "  THEN pd.debutPeriode "
				+ "ELSE ( pd2.debutPeriode ) "
				+ "END, "
				+ "CASE WHEN ( pd.finPeriode > pd2.finPeriode ) "
				+  "  THEN pd2.finPeriode "
				+ "ELSE pd.finPeriode "
				+ "END "
				+ "FROM PeriodeDisponibilite pd, PeriodeDisponibilite pd2 "
				+ "JOIN pd2.troupeau tr "
				+ "WHERE " // pd2.troupeau is not null "
				+ "pd.terrain = :paramTerrain "
				+ "AND ( not ( pd.finPeriode < pd2.debutPeriode OR pd.debutPeriode > pd2.finPeriode ) ) "
		);
				
		query.setParameter("paramTerrain", terrain);
		
		temp_correspondances = query.getResultList();
		
		for(int i= 0; i< temp_correspondances.size(); i++) {
			
			int matchMorpho = 0;
			int matchVege = 0;
			
			TroupeauTrouveApresRechercheDTO oneTroupeau = new TroupeauTrouveApresRechercheDTO();
			Object[] temp = temp_correspondances.get(i);
			
			oneTroupeau.setTroupeau( (Troupeau) (temp[0]) );
			oneTroupeau.setDateMin( (Date) temp[1]);
			oneTroupeau.setDateMax( (Date) temp[2]);
			correspondances.add(oneTroupeau);
			
			for (int k=0; k<correspondances.get(i).getTroupeau().getCompositionTroupeau().size(); k++) {
				CompositionTroupeau compteTroup = correspondances.get(i).getTroupeau().getCompositionTroupeau().get(k);
				for(int j=0; j<compteTroup.getRaceRef().getMorphologieRefs().size(); j++) {
					MorphologieRef morphoRef = compteTroup.getRaceRef().getMorphologieRefs().get(j);
					if (morphoRef.getProportionMorphologies().size() > 0) {
						for(int m=0; m<morphoRef.getProportionMorphologies().get(0).getTerrain().getProportionMorphologies().size(); m++) {
							if (morphoRef.getLibelleMorphologie()
									.equals(morphoRef.getProportionMorphologies()
									.get(0).getTerrain().getProportionMorphologies()
									.get(m).getMorphologieRef().getLibelleMorphologie())
									&& terrain.getIdTerrain()==morphoRef.getProportionMorphologies()
									.get(0).getTerrain().getIdTerrain()) {
								
								matchMorpho += (morphoRef.getProportionMorphologies().get(0)
										.getProportionMorphologie());
							}
						}
					}
				}
				
				for(int j=0; j<compteTroup.getRaceRef().getVegetationRefs().size(); j++) {
					VegetationRef vegeRef = compteTroup.getRaceRef().getVegetationRefs().get(j);
					if (vegeRef.getProportionVegetations().size() > 0) {
						for(int m=0; m<vegeRef.getProportionVegetations().get(0).getTerrain().getProportionMorphologies().size(); m++) {
							if (vegeRef.getLibelleVegetation()
									.equals(vegeRef.getProportionVegetations()
									.get(0).getTerrain().getProportionVegetations()
									.get(m).getVegetationRef().getLibelleVegetation())
									&& terrain.getIdTerrain()==vegeRef.getProportionVegetations()
															.get(0).getTerrain().getIdTerrain()) {
								
								matchVege += (vegeRef.getProportionVegetations().get(0)
										.getProportionVege());
							}
						}
					}
				}
				
			}
			oneTroupeau.setPourcentagePropoMorpho(matchMorpho/(correspondances.get(i).getTroupeau().getCompositionTroupeau().size()));
			oneTroupeau.setPourcentagePropoVege(matchVege/(correspondances.get(i).getTroupeau().getCompositionTroupeau().size()));
			
		}
		return correspondances;
	}
	
	// Native SQL request
	public List<Troupeau> chercheTroupeauxCompatibles2(Terrain terrain) {
		List<Troupeau> correspondances = new ArrayList<>();
		
		Query query = entityManager.createNativeQuery(
				"SELECT DISTINCT tr.id_troupeau "
				+ "FROM terrain t "
				+ "JOIN periode_disponibilite as pd ON t.id_terrain = pd.id_terrain "
				+ "JOIN troupeau tr "
				+ "JOIN  periode_disponibilite as pd2 ON tr.id_troupeau = pd2.id_troupeau "
				+ "WHERE ( not ( pd.fin_periode < pd2.debut_periode OR pd.debut_periode > pd2.fin_periode ) ) "
				+ " AND pd.id_terrain = :idField "
				);
		
		query.setParameter("idField", terrain.getIdTerrain());
		correspondances = query.getResultList();
		
		return correspondances;
	}
	
	// for further tests
	public List<TroupeauTrouveApresRechercheDTO> chercheTroupeauxCompatibles3(Terrain terrain) {
		List<TroupeauTrouveApresRechercheDTO> correspondances = new ArrayList<>();
		
		List<Object[]> temp_correspondances;
		
		//TypedQuery<TroupeauTrouve>
		Query query = entityManager.createQuery(
				"SELECT tr,  "
				+ "CASE WHEN ( pd.debutPeriode > pd2.debutPeriode ) " 
				+ "  THEN pd.debutPeriode "
				+ "ELSE ( pd2.debutPeriode ) "
				+ "END, " //AS 'MinDateService' " 
				+ "CASE WHEN ( pd.finPeriode > pd2.finPeriode ) "
				+  "  THEN pd2.finPeriode "
				+ "ELSE pd.finPeriode "
				+ "END " // AS 'MaxDateService'
				+ "FROM PeriodeDisponibilite pd, PeriodeDisponibilite pd2 "
				+ "JOIN pd2.troupeau tr "
				+ "WHERE " // pd2.troupeau is not null "
				+ "pd.terrain = :paramTerrain "
				+ "AND ( not ( pd.finPeriode < pd2.debutPeriode OR pd.debutPeriode > pd2.finPeriode ) ) "
		);
				
		query.setParameter("paramTerrain", terrain);
		
		temp_correspondances = query.getResultList();
		for(int i= 0; i< temp_correspondances.size(); i++) {
			
			TroupeauTrouveApresRechercheDTO oneTroupeau = new TroupeauTrouveApresRechercheDTO();
			Object[] temp = temp_correspondances.get(i);
			
			oneTroupeau.setTroupeau( (Troupeau) (temp[0]) );
			oneTroupeau.setDateMin( (Date) temp[1]);
			oneTroupeau.setDateMax( (Date) temp[2]);
			correspondances.add(oneTroupeau);
		}
		/*
		Iterator<Object[]> it = query.getResultList().iterator();
		while (it.hasNext()) {
			Object[] oneResult = (Object[]) it.next();
			TroupeauTrouveApresRechercheDTO oneTroupeau = new TroupeauTrouveApresRechercheDTO();
			oneTroupeau.setTroupeau( (Troupeau) oneResult[0] );
			oneTroupeau.setDateMin( (Date) oneResult[1]);
			oneTroupeau.setDateMax( (Date) oneResult[2]);
			correspondances.add(oneTroupeau);
		}
		*/
		return correspondances;
	}
}
