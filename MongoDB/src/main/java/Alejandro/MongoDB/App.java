package Alejandro.MongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    static String connectionString= "mongodb+srv://almorsan1994:1234@cluster0.jgqper8.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    static  MongoCollection<Document> collection;
     
    public static void main( String[] args )
    {
       
        try (MongoClient mongoClient=MongoClients.create(new ConnectionString(connectionString))) {
        	
        	MongoDatabase dataBase=mongoClient.getDatabase("mi_base_de_datos");
        	
        	collection=dataBase.getCollection("mi_coleccion");
        	
        	Document document=new Document("nombre","Alejandro").append("edad", 29).append("ciudad","Albacete");
        	collection.insertOne(document);
        	
        	MongoCursor<Document> cursor=collection.find().iterator();
        	
        	//mostramos los datos
        	try {
        		while (cursor.hasNext()) {
        			System.out.println(cursor.next().toJson());
        		}
        	} finally {
        		cursor.close();
        	}
        	//actualizamos datos
        	
        	actualizarDatos(44,"Cuenca");
        	
        }
    }
    
    static void actualizarDatos (int edad, String ciudad) {
    	
    	collection.updateOne(Filters.eq("nombre", "Alejandro"), Updates.combine(Updates.set("edad", edad), Updates.set("ciudad", ciudad))); 
    	

    	//mostramos los datos para ver que se han actualizado
    	MongoCursor<Document> cursor=collection.find().iterator();
    	try {
    		while (cursor.hasNext()) {
    			System.out.println(cursor.next().toJson());
    		}
    	} finally {
    		cursor.close();
    	}
    	
    }
    
    
}
