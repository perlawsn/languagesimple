package org.dei.perla.language;

public class Protot {

	private static Parser parser;
	
public static void main(String args[]){
	
	String queryTest1 = "CREATE OUTPUT STREAM 'tablename' (attributo1 INT, attributo2 FLOAT, attributo3 FLOAT, attributo4 FLOAT, attributo5 FLOAT) AS: "
			+ "EVERY 5 MINUTES"
			+ "SELECT AVG(attributo1; 2 MINUTES), attributo2, MAX(attributo3; 2 MINUTES), MIN(attributo4; 2 MINUTES), attributo5 "
			+ "SAMPLING IF temperature < 50 EVERY 10 MINUTES "
            + "ELSE IF TEMPERATURE >= 50 EVERY 1 MINUTES "
			+ "EXECUTE IF EXISTS (attributo1) AND EXISTS (attributo2) AND EXISTS (attributo3) ";
	
	String queryTest2 = "CREATE OUTPUT STREAM 'tablename' (attributo1 INT, attributo2 FLOAT, attributo3 FLOAT, attributo4 FLOAT, attributo5 FLOAT) AS: "
			+ "EVERY 5 MINUTES "
			+ "SELECT AVG(attributo1; 2 MINUTES), attributo2, MAX(attributo3; 2 MINUTES), MIN(attributo4; 2 MINUTES), attributo5 "
			+ "SAMPLING "
			+ "EVERY 1 MINUTES "
			+ "EXECUTE IF EXISTS (attributo1) AND EXISTS (attributo2) "
			+ "TERMINATE AFTER 1 SAMPLE ";
	
	String queryTest3 = "CREATE OUTPUT STREAM 'tablename' (attributo1 INT, attributo2 FLOAT, attributo3 FLOAT, attributo4 FLOAT, attributo5 FLOAT) AS: "
			+ "EVERY 5 MINUTES"
			+ "SELECT AVG(attributo1; 2 MINUTES), attributo2, MAX(attributo3; 2 MINUTES), MIN(attributo4; 2 MINUTES), attributo5 "
			+ "SAMPLING "
			+ "ON EVENT explosion "
			+ "EXECUTE IF EXISTS (attributo1) AND EXISTS (attributo2) ";
	
	String queryTest4 = "CREATE OUTPUT STREAM 'tablename' (attributo1 INT, attributo2 FLOAT, attributo3 FLOAT, attributo4 FLOAT, attributo5 FLOAT) AS: "
			+ "EVERY 5 MINUTES"
			+ "SELECT attributo2, attributo5 "
			+ "SAMPLING "
			+ "ON EVENT explosion "
			+ "EXECUTE IF EXISTS (attributo1) AND EXISTS (attributo2)";
	
	parser=new Parser(queryTest2);
	
	QueryClass query =parser.createQueryClass();
	query.createTable();
	// query.matchFpcRegister();
	query.matchAggregatorRegister();
	
	//Scattano gli invii delle query o ai singoli FPC
	
	}

}
