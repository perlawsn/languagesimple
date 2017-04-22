package org.dei.perla.language;

import java.util.ArrayList;
import java.util.List;

public class Parser {
	
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
	
	private ArrayList<ExecuteIfCondition> executeIf = new ArrayList<ExecuteIfCondition>();
	
	private Integer queryPeriod;
	
	public Parser(String query){
		String query2=query.replace('(', '<');
		this.query=query2.replace(')', '>');
		
	}
	
	public void tableName(){
		
		String separator1="' <";
		String creation = creationSection.split(separator1)[0];
		String separator2="'";
		tableName = creation.split(separator2)[1];
		
	}
	
	public void splitQuery(){
		String separator0 = "AS: EVERY ";
		
		creationSection = query.split(separator0)[0];
		
		String separator1 ="SELECT ";
		everySection = query.split(separator0)[1].split(separator1)[0];
		
		String selectSectionRough = query.split(separator0)[1].split(separator1)[1];
		
		String separator2="SAMPLING ";
		samplingSection = selectSectionRough.split(separator2)[1];
		
		selectSection=selectSectionRough.split(separator2)[0];
				
		String separator3=" EXECUTE IF";
		
		samplingInfo=samplingSection.split(separator3)[0];
				
		executeIfSection = samplingSection.split(separator3)[1];
		
		
		String separator4=" TERMINATE AFTER ";
		
		
	}
	
	public void TableAttributeList(){
		String attributeString=creationSection.split("<")[1].replace(">", "");
		//System.out.println(attributeString);
		tableAttributeArray=attributeString.split(", ");
		
	}
	
	public void everyInfo(){
		
		everyPeriod = everySection.split(" ")[0];
		everyMeasure = everySection.split(" ")[1];
	}
	
	public void samplingInfo(){
		
		samplingCondition=new SamplingCondition(samplingInfo);
		samplingCondition.samplingSettings();
	}
	
	public void addAttribute(){
		String[] attributeArray=this.selectSection.split(", ");
				
		for(String att:attributeArray){
		
		 
		 if (att.contains("AVG") || att.contains("MIN") || att.contains("MAX") || 
				 att.contains("SUM") || att.contains("COUNT")){
			 createAggregatedAttribute(att);
		 }else{
			 
			 AttributeQuery newAtt= newAtt= new AttributeQuery(att, null, null, null);
			 this.attributes.add(newAtt);
			 
		 }
		 
		}
		
		
	}
	
	public void createAggregatedAttribute(String att){
		
		String aggregation=att.split("<")[0];
		String aggrPeriodMeasure = att.split("<")[1];
		String attrName=aggrPeriodMeasure.split("; ")[0];
		
		String aggrPeriod=aggrPeriodMeasure.split("; ")[1].replace(">", "");
		
		AttributeQuery newAtt= null;
		newAtt= new AttributeQuery(attrName, aggrPeriod.split(" ")[0], aggrPeriod.split(" ")[1], aggregation);
		this.attributes.add(newAtt);
		
	}
	
	public void executeIf(){
		
		executeIfSection=executeIfSection.replace("EXISTS ", "");
		String[] conditions=executeIfSection.split(" AND ");
		for(String s:conditions){
			ExecuteIfCondition eic=new ExecuteIfCondition(s);
			eic.executeIfVerification();
			this.executeIf.add(eic);
		}
	}
	
	public void terminate(){
		
	}
	
	public QueryClass createQueryClass(){
		
		splitQuery();
		tableName();
		TableAttributeList();
		everyInfo();
		addAttribute();
		samplingInfo(); //manca la parte dell'IF
		executeIf();
		terminate();
		
		QueryClass newQuery = new QueryClass(tableName, tableAttributeArray, 
				everyPeriod, everyMeasure, samplingCondition, attributes, executeIf);
		
		
		return newQuery;
		
	}



public class AttributeQuery{
	 
	private String attribute;
	private String aggrPeriod;
	private String aggrMeasure;
	private String aggregation;
	
	public AttributeQuery(String attribute, String aggrPeriod, String aggrMeasure, String aggregation){
		this.attribute=attribute;
		this.aggrPeriod=aggrPeriod;
		this.aggregation=aggregation;
		this.aggrMeasure=aggrMeasure;
	}
	
}

public class ExecuteIfCondition{
	String attCondition;
	String attributeName;
	String condition;
	String value;
	
	public ExecuteIfCondition(String attCondition){
		if(attCondition.contains("<")&&attCondition.contains(">")){
		this.attCondition=attCondition.replace("<", "").replace(">", "");
		this.attributeName=attCondition.replace("<", "").replace(">", "");
		}else {
			this.attCondition=attCondition;
			this.attributeName=attCondition;
		}
		this.condition="EXISTS";
	}
	
	public void executeIfVerification(){
		if (attCondition.contains(">")){
			attributeName=attCondition.split(">")[0];
			value=attCondition.split(">")[1];
			condition=">";
		}else if (attCondition.contains("<")){
			attributeName=attCondition.split("<")[0];
			value=attCondition.split("<")[1];
			condition="<";
		}else if (attCondition.contains("=")){
			attributeName=attCondition.split("=")[0];
			value=attCondition.split("=")[1];
			condition="=";
		}
	}
	
	
}

public class SamplingCondition{
	
	private String samplingInfo;
	private String samplingPeriod;
	private String samplingMeasure;
	private boolean samplingOnEvent;
	private String samplingNameEvent;
	
	public SamplingCondition(String samplingInfo) {
		this.samplingInfo=samplingInfo;
		
	}
	
	public void samplingSettings(){
		if (samplingInfo.contains("IF")){
			String noif=samplingInfo.replace("IF ", "");
			String[] samplingConditions=noif.split(" ELSE ");
						
			
		}else if (samplingInfo.contains("ON EVENT")){
			samplingOnEvent=true;
			samplingNameEvent=samplingInfo.replace("ON EVENT ", "");
		}else {
			samplingMeasure=samplingInfo.split(" ")[0];
			samplingPeriod=samplingInfo.split(" ")[1];
		}
	}
	
	
	
}

}