package org.dei.perla.aggregator.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dei.perla.core.Plugin;
import org.dei.perla.core.channel.http.HttpChannelPlugin;
import org.dei.perla.core.channel.simulator.SimulatorChannelPlugin;
import org.dei.perla.core.channel.simulator.SimulatorMapperFactory;
import org.dei.perla.core.fpc.FpcCreationException;
import org.dei.perla.core.message.json.JsonMapperFactory;
import org.dei.perla.core.message.urlencoded.UrlEncodedMapperFactory;
import org.dei.perla.core.registry.Registry;
import org.dei.perla.core.registry.TreeRegistry;
import org.dei.perla.language.Parser;
import org.dei.perla.language.QueryClass;

public class QueryConsumer {
	
	private List<QueryClass> queryList = new ArrayList<QueryClass>();
	private Parser parser;
	ServerSystem ps = null;
	
		
	/**
	 * receiveQuery : riceve la query e lancia il parser che crea una QueryClass. Aggiunge alla lista 
	 * delle query e controlla i matching con FPC register e aggregator register
	 * @param query
	 */
		
	public void newQuery(String query){
		parser = new Parser(query);
		QueryClass parsedQuery = parser.createQueryClass();
		queryList.add(parsedQuery);
		
		parsedQuery.matchFpcRegister((TreeRegistry)ps.getRegistry());
		parsedQuery.matchAggregatorRegister();
		
		
	}
	
	public ServerSystem newSystem(List<Plugin> plugins){
		   
	    ps = new ServerSystem(plugins);
		return ps;
	}
	
	public void newFpc(){
		try {
			ps.injectDescriptor(null);
		} catch (FpcCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAggregator(){
		
	}
	
}
