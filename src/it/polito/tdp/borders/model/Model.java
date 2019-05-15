package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.jgrapht.Graph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private BordersDAO dao;
	private Map<Integer, Country> idMap;
	
	public Model() {
		idMap = new HashMap<Integer, Country>();
		dao = new BordersDAO();
		dao.loadAllCountries(idMap);
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		for (Border border : dao.getCountryPairs(anno, idMap)) {
			grafo.addVertex(border.getC1());
			grafo.addVertex(border.getC2());
			grafo.addEdge(border.getC1(), border.getC2());
		}
		
	}
	
	public Graph<Country, DefaultEdge> getGrafo() {
		return grafo;
	}
	
	public Collection<Country> getCountry() {
		return new TreeSet<>(grafo.vertexSet());    // TreeSet ordina sul comparable
	}
	
	public String elencoStati() {
		String s = "Elenco degli stati, per ciascuno il numero di stati confinanti: \n";
		
		for (Country c : getCountry())
			s += c.getAbb()+" "+c.getNome()+" --> "+grafo.degreeOf(c)+"\n";

		return s;
	}
	
	public int componentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<>(grafo);
		
		return ci.connectedSets().size();
	}
	
	public List<Country> trovaVicini(Country c) {
		List<Country> vicini = new ArrayList<>();
		
		DepthFirstIterator<Country, DefaultEdge> it = new DepthFirstIterator<>(grafo, c);
            
        while (it.hasNext())
        	vicini.add(0, it.next());
    	
		return vicini.subList(1, vicini.size());
	}

}
