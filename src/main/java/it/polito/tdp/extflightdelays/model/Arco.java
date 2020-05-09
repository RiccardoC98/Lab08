package it.polito.tdp.extflightdelays.model;

public class Arco {
	int originID, destID, weight;

	public Arco(int originID, int destID, int weight) {
		super();
		this.originID = originID;
		this.destID = destID;
		this.weight = weight;
	}

	public int getOriginID() {
		return originID;
	}

	public int getDestID() {
		return destID;
	}

	public int getWeight() {
		return weight;
	}
	
	
}
