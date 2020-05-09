package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	Graph <Airport, DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	Map <Integer, Airport> idMap;
	String arcWeights;
	
	public Model() {

	}
	
	public void creaGrafo(int minimaDistanza) {
		
		idMap = new HashMap<>();
		arcWeights = "";
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class); // inizializzo
		
		for (Airport a : dao.loadAllAirports()) {
			this.idMap.put(a.getId(), a);
		}
		
		int md = minimaDistanza; // comodità
		
		
		
		// carico tutti i possibili archi
		List<Arco> archi = dao.getConnections();
	
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		for (Arco a : archi) {
			if (a.getWeight() > md) { // aggiungo solo quelli con distanza media > minimaDistanza
				Airport sourceVertex = idMap.get(a.getOriginID());
				Airport targetVertex = idMap.get(a.getDestID());
				double weight = (double) a.getWeight();
				// arcWeights += "From: " + sourceVertex.getAirportName() + " To: " + targetVertex.getAirportName() + " Weight: " + weight + "\n";
				
				arcWeights += String.format("From: %s To: %s Weight: %8.1f\n", sourceVertex.getAirportName(), targetVertex.getAirportName(), weight );
				Graphs.addEdge(this.grafo, sourceVertex, targetVertex, weight);
			}
		}		
		
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String getArchiEDistanza() {
		return arcWeights;
	}
}
