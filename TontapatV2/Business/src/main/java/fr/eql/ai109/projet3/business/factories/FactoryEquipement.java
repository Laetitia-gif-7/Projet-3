package fr.eql.ai109.projet3.business.factories;

import java.util.Arrays;
import java.util.List;

import fr.eql.ai109.projet3.entity.Equipement;

public enum FactoryEquipement {
	
	Cloture {
		@Override
		Equipement createEquipement() {
			Equipement equip = new Equipement();
			equip.setLibelleEquipement("clôture");
			return equip;
			//equip.setIdEquipement(1);
			//equip.setUniteRef(null);
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
	
	public static List<String >getListOfLibelle() {
		return Arrays.asList("clôture","abri", "abreuvoir");
	}
}
