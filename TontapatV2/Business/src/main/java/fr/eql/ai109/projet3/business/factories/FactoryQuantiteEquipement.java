package fr.eql.ai109.projet3.business.factories;

import fr.eql.ai109.projet3.entity.Equipement;
import fr.eql.ai109.projet3.entity.QuantiteEquipement;

public enum FactoryQuantiteEquipement {
	
	Cloture {
		@Override
		public QuantiteEquipement createQuantiteEquipement(int quantite) {
			QuantiteEquipement qe = new QuantiteEquipement();
			qe.setQuantite(quantite);
			Equipement equip = FactoryEquipement.Cloture.createEquipement();
			qe.setEquipement(equip);
			return qe;
		}
	},
	Abri {
		@Override
		public QuantiteEquipement createQuantiteEquipement(int quantite) {
			QuantiteEquipement qe = new QuantiteEquipement();
			qe.setQuantite(quantite);
			Equipement equip = FactoryEquipement.Abri.createEquipement();
			qe.setEquipement(equip);
			return qe;
		}
	},
	Abreuvoir {
		@Override
		public QuantiteEquipement createQuantiteEquipement(int quantite) {
			QuantiteEquipement qe = new QuantiteEquipement();
			qe.setQuantite(quantite);
			Equipement equip = FactoryEquipement.Abreuvoir.createEquipement();
			qe.setEquipement(equip);
			return qe;
		}
	};
	abstract public QuantiteEquipement createQuantiteEquipement(int quantite);
}
