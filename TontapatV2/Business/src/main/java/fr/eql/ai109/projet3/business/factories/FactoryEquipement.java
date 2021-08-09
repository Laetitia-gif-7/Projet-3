package fr.eql.ai109.projet3.business.factories;

import fr.eql.ai109.projet3.entity.Equipement;

public enum FactoryEquipement {
	
	Cloture {
		@Override
		Equipement createEquipement() {
			Equipement equip = new Equipement();
			//equip.setIdEquipement(1);
			equip.setLibelleEquipement("clôture");
			//equip.setUniteRef(null);
			return equip;
		}
	},
	Abri {
		@Override
		Equipement createEquipement() {
			Equipement equip = new Equipement();
			equip.setLibelleEquipement("abri");
			return equip;
		}
	},
	Abreuvoir {
		@Override
		Equipement createEquipement() {
			Equipement equip = new Equipement();
			equip.setLibelleEquipement("abreuvoir");
			return equip;
		}
	};
	
	abstract Equipement createEquipement();
}
