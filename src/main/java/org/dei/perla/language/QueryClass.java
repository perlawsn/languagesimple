package org.dei.perla.language;

import java.util.ArrayList;
import java.util.List;

import org.dei.perla.core.fpc.Attribute;
import org.dei.perla.core.fpc.Fpc;
import org.dei.perla.core.registry.TreeRegistry;
import org.dei.perla.language.Parser.AttributeQuery;
import org.dei.perla.language.Parser.ExecuteIfCondition;
import org.dei.perla.language.Parser.SamplingCondition;

public class QueryClass {
	
	
	private String tableAttributeString="";
	
	private String query ;
	
	private String creationSection;
	private String everySection;
	private String selectSection;
	private String samplingSection;
	private String executeIfSection;
	
	private SamplingCondition samplingCondition;
	
	private String everyPeriod;
	private String everyMeasure;
	private String samplingInfo;
		
	private String[] tableAttributeArray;
	private String tableName;
	
	private ArrayList<AttributeQuery> attributes = new ArrayList<AttributeQuery>();
	private ArrayList<ExecuteIfCondition> executeIf;
	private Integer queryPeriod;
	
	
	public QueryClass(String tableName, String[] tableAttributeArray, String everyPeriod, 
			String everyMeasure, SamplingCondition samplingCondition, ArrayList<AttributeQuery> attributes,
			ArrayList<ExecuteIfCondition> executeIf){
		this.tableName=tableName;
		this.everyPeriod=everyPeriod;
		this.everyMeasure=everyMeasure;
		this.tableAttributeArray=tableAttributeArray;
		this.samplingCondition=samplingCondition;
		this.attributes=attributes;
		this.executeIf=executeIf;
		arrayToString();	
		System.out.println("Crea tabella "+tableName+" e aggiornala ogni "+everyPeriod+" "+everyMeasure);
		
	}
	
	public void createTable(){
		
	}
	
	private void arrayToString(){
		for (String s:tableAttributeArray){
			this.tableAttributeString=this.tableAttributeString+s;
			}
	}
	
	
	//Con gli attributi controllo gli FPC coinvolti
	public void matchFpcRegister(TreeRegistry fpcRegistry){
		
		
	}

	//Con gli FPC coinvolti controllo gli aggregatori
	public void matchAggregatorRegister(){
		
	}
}
