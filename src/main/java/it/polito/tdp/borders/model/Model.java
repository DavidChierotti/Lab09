package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;


public class Model {

	private List<Country> nazioni;
	private Map<Integer,Country> mappaNazioni;
	private Graph<Country,DefaultEdge> grafo;
	private BordersDAO dao;
	
	public Model() {
		this.dao=new BordersDAO();
		this.mappaNazioni=new HashMap<>();
		this.nazioni=new ArrayList<>();
		nazioni=dao.loadAllCountries();
		for(Country c:nazioni)
			mappaNazioni.put(c.getId(), c);
		
		
	}

	public String creaGrafo(int anno) {
		this.grafo=new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		ArrayList<Border> confini=new ArrayList<>();
		confini=(ArrayList<Border>) dao.getCountryPairs(anno);
		Map<Integer,Country> nations=new HashMap<Integer,Country>();
		for(Border b:confini) {
			nations.put(b.getId1(),mappaNazioni.get(b.getId1()));
			nations.put(b.getId2(),mappaNazioni.get(b.getId2()));
		}
		Graphs.addAllVertices(this.grafo, nations.values());
		for(Border b:confini) {
			this.grafo.addEdge(nations.get(b.getId1()), nations.get(b.getId2()));
		}
	
		return stampa(grafo); 
	}
	
	public String visitaGrafo(Country partenza,int anno) {
		String s=creaGrafo(anno);
		if(!grafo.vertexSet().contains(partenza))
			return "Paese non presente nell'anno selezionato";
		GraphIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<>(this.grafo, partenza);
		Map <Integer,Country> nodi=new HashMap<>();
		while(visita.hasNext()) {
			Country c=visita.next();
			nodi.put(c.getId(), c);
		}
		
		nodi.remove(partenza.getId());
		return nodi.toString();
		
	}

	private String stampa(Graph<Country, DefaultEdge> grafo) {
		String s=new String();
		for(Country c:grafo.vertexSet()) {
			s+="Nazione: "+c.getStateName()+" ha "+grafo.degreeOf(c)+" gradi\n";
		}
		s+="Numero sezioni connesse: "+numeroComponentiConnesse(grafo);
		return s;
	}

	private int numeroComponentiConnesse(Graph<Country, DefaultEdge> grafo) {
		ConnectivityInspector ci=new ConnectivityInspector(grafo);
		List<Set<Country>> connessi=ci.connectedSets();
		return connessi.size();
	}

	public List<Country> getNazioni() {
		this.nazioni=dao.loadAllCountries();
		return nazioni;
	}
	
	public List<Country> popolaCmb(){
		return dao.popolaCombo(mappaNazioni);
	}
	
}
