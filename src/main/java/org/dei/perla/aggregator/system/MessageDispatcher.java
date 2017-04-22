package org.dei.perla.aggregator.system;

public class MessageDispatcher {
	
	ServerSystem system;
	QueryConsumer qc;
	
	public MessageDispatcher(ServerSystem system, QueryConsumer qc){
		
				
	}
	
	public void consumer(Object message){
		
		//if--> arriva una query->parsing, tabelle ecc.
		
		//else if--> arriva un FPC--> si registra nel register e si registra l'aggregatore
					//se l'FPC arriva e l'aggregatore c'è già non ci interessa perché
					//l'aggregatorRegistry tiene conto dell'accoppiata aggregatore-fpc
		
		//else if-->arriva una risposta alla query-> ogni risposta alla query ha dentro anche 
					//l'identificativo della query
					
		
		
		
	}

}
