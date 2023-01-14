package org.mql.java.models;

public class VoitureModel {
	

		private String model, marque, matricule, coleur, année, automatique;
		
		public VoitureModel() {}

		public VoitureModel(String model, String marque, String matricule, String coleur, String annee, String atomatique) {
			this.model = model;
			this.marque = marque;
			this.matricule = matricule;
			this.coleur = coleur;
			this.année = annee;
			this.automatique=atomatique;
	}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getMarque() {
			return marque;
		}

		public void setMarque(String marque) {
			this.marque = marque;
		}

		public String getMatricule() {
			return matricule;
		}

		public void setMatricule(String matricule) {
			this.matricule = matricule;
		}

		public String getColeur() {
			return coleur;
		}

		public void setColeur(String coleur) {
			this.coleur = coleur;
		}

		public String getAnnée() {
			return année;
		}

		public void setAnnée(String année) {
			this.année = année;
		}

		public String getAutomatique() {
			return automatique;
		}

		public void setAutomatique(String automatique) {
			this.automatique = automatique;
		}

		@Override
		public String toString() {
			return "VoitureModel [model=" + model + ", marque=" + marque + ", matricule=" + matricule + ", coleur="
					+ coleur + ", année=" + année + ", automatique=" + automatique + "]";
		}
		
		

	

}
