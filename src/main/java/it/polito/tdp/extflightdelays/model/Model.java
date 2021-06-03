package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

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

	public void creaGrafo(Integer distanzaMedia) {
		grafo = new SimpleWeightedGraph(DefaultWeightedEdge.class);

		dao.loadAllAirports(idMapAirports);
		Graphs.addAllVertices(grafo, idMapAirports.values());

		for (Rotta r : dao.getRotte(idMapAirports, distanzaMedia)) {

			DefaultWeightedEdge edge = grafo.getEdge(r.getA1(), r.getA2());
			if(edge==null) {
				Graphs.addEdge(grafo,r.getA1(), r.getA2(), r.getPeso());
			}else {
				Double peso = grafo.getEdgeWeight(edge);
				Double newPeso = (peso+r.getPeso())/2;
				grafo.setEdgeWeight(edge, newPeso);

			}
		}
	}
	public List<Rotta> getRotteSalvate(){
		List<Rotta> result = new ArrayList<Rotta>();
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			Rotta r = new Rotta()
		}
	}

}
