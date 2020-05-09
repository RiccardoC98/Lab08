package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();

		model.creaGrafo(700);
		System.out.println(model.getArchiEDistanza());
		System.out.println("Numero vertici " + model.nVertici() + " numero archi: " + model.nArchi());

	}

}
