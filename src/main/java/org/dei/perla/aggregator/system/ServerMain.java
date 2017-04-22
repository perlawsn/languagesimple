package org.dei.perla.aggregator.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dei.perla.core.Plugin;
import org.dei.perla.core.channel.http.HttpChannelPlugin;
import org.dei.perla.core.channel.simulator.SimulatorChannelPlugin;
import org.dei.perla.core.channel.simulator.SimulatorMapperFactory;
import org.dei.perla.core.message.json.JsonMapperFactory;
import org.dei.perla.core.message.urlencoded.UrlEncodedMapperFactory;
import org.dei.perla.core.registry.Registry;
import org.dei.perla.core.registry.TreeRegistry;

public class ServerMain {
	
	private static final List<Plugin> plugins;
    static {
        List<Plugin> ps = new ArrayList<>();
        ps.add(new JsonMapperFactory());
        ps.add(new UrlEncodedMapperFactory());
        ps.add(new SimulatorMapperFactory());
        ps.add(new HttpChannelPlugin());
        ps.add(new SimulatorChannelPlugin());
        plugins = Collections.unmodifiableList(ps);
    }
	
	public static void main(String[] args){
		
		QueryConsumer qc = new QueryConsumer();
		
	
		MessageDispatcher md=new MessageDispatcher(qc.newSystem(plugins), qc);
		
		//Qui parte il thread che tiene su il sistema
		
	}

}
