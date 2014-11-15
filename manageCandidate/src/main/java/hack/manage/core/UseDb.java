package hack.manage.core;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

public class UseDb {

	public static void main(String[] args) {
		// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
		// if it's a member of a replica set:

		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "test" );
			
			//print all collection
			Set<String> colls = db.getCollectionNames();
			for (String s : colls) {
			    System.out.println(s);
			}
			
			//get the collection
			DBCollection coll = db.getCollection("profile");
			
			//insert
			/*BasicDBObject doc = new BasicDBObject("name", "gg")
	        .append("type", "25")
	        .append("isInterested", 0);
			coll.insert(doc);*/
			
			//get first record
			System.out.println("Printing first records");
			DBObject myDoc = coll.findOne();
			System.out.println(myDoc);

			//go thru records
			System.out.println("Printing all records");
			DBCursor cursor = coll.find();
			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			
			System.out.println("Printing particular records with name");
			BasicDBObject query = new BasicDBObject("name", "Drew");

			cursor = coll.find(query);

			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			
			System.out.println("Printing particular records with condition");
			query = new BasicDBObject("isInterested", new BasicDBObject("$gt", 0));

			cursor = coll.find(query);
		
			try {
			    while(cursor.hasNext()) {
			        System.out.println(cursor.next());
			    }
			} finally {
			    cursor.close();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
