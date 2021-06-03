package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map <Integer, Airport> idMapAirports;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		idMapAirports = new HashMap<Integer,Airport>();
		dao = new ExtFlightDelaysDAO();

	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph(DefaultWeightedEdge.class);
		
		dao.loadAllAirports(idMapAirports);
		Graphs.addAllVertices(grafo, idMapAirports.values());
		
		for(Flight f : dao.loadAllFlights()) {
			Graphs.addEdge(this.grafo, idMapAirports.get(f.getId()));
		}
	}
}
