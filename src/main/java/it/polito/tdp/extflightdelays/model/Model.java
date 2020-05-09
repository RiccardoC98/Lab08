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
	
	public Model() {
		idMap = new HashMap<>();
	}
	
	public void creaGrafo(int minimaDistanza) {
		
		for (Airport a : dao.loadAllAirports()) {
			this.idMap.put(a.getId(), a);
		}
		
		int md = minimaDistanza; // comodità
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class); // inizializzo
		
		
		// carico tutti i possibili archi
		List<Arco> archi = dao.getConnections();
	
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		for (Arco a : archi) {
			if (a.getWeight() > md) { // aggiungo solo quelli con distanza media > minimaDistanza
				Airport sourceVertex = idMap.get(a.getOriginID());
				Airport targetVertex = idMap.get(a.getDestID());
				double weight = (double) a.getWeight();
				
				Graphs.addEdge(this.grafo, sourceVertex, targetVertex, weight);
			}
		}		
		
		this.grafo.rem
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public String getArchiEDist() {
		String res = "";
		for (Airport a : grafo.vertexSet()) {
			res += a.getAirportName() + 
		}
	}
}
