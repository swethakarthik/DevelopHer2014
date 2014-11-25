package hack.manage.core;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;

public class UseDb {

	public static void main(String[] args) {
		// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
		// if it's a member of a replica set:

		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "CandidatesProfilesPerManager" );
			
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
			BasicDBObject query = new BasicDBObject("managerId", "drew");
			cursor = coll.find(query);

			try {
			   while(cursor.hasNext()) {
				   CandidatesPerManger cm = new CandidatesPerManger();
				   BasicDBObject dbObj = (BasicDBObject) cursor.next();
				   cm.setManagerId(dbObj.getString("managerId"));
				   System.out.println(dbObj.getString("managerId"));
				   cm.setCandidateProfile(dbObj.get("candidateProfile").toString());
				   cm.setContacted(dbObj.getBoolean("isContacted"));
				   cm.setInterested(dbObj.getBoolean("isContacted"));
				   cm.setVisaType(dbObj.getString("visa"));
				   cm.setLocation(dbObj.getString("location"));
				   cm.setContactDate(dbObj.getDate("contactDate"));
				   //String json = JSON.serialize( dbObj );
				   //System.out.println(json);
				   Gson gson = new Gson();
				   System.out.println("cm" + gson.toJson(cm));
			       
			   }
			} finally {
			   cursor.close();
			}
			
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", 
				new BasicDBObject().append("isInterested", "true")
				.append("visa", "H2")
				.append("location", "LA"));
		 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("managerId", "drew");
			searchQuery.append("candidateProfile", "xyz");
		 
			coll.update(searchQuery, updateQuery);
			
			DBObject myDoc1 = coll.findOne();
			System.out.println(myDoc1);
			
			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:8090/manageCandidate/rest/");

			MultiPart mp = new MultiPart();
			Entry e = new Entry();
			e.setArchive(false);
			e.setManagerId("yawen");
			e.setCandidateId("swetha");
			e.setCreate(true);
			e.setEmailId("swetha@gmail.com");
			BodyPart bp = new BodyPart(e, MediaType.APPLICATION_JSON_TYPE);
			mp.getBodyParts().add(bp);
			
			Form form = new Form();
		    form.add("managerId", "yona");
		    form.add("candidateId", "12345");
		    form.add("emailId", "gg@gmail.com");
		    form.add("isArchive", "false");
		    form.add("candidateName", "gg");
		    ClientResponse responseJson = webResource.path("/CandidateCreation/create").type(MediaType.APPLICATION_FORM_URLENCODED)
		            .post(ClientResponse.class, form);
			System.out.println("Candidate created" + responseJson.getStatus());

			Form formArchive = new Form();
			formArchive.add("managerId", "yona");
			formArchive.add("candidateId", "67643");
			formArchive.add("emailId", "xyz@gmail.com");
			form.add("isArchive", "true");
		    form.add("candidateName", "abc");
		   // form.add("Create", "abc");
		    ClientResponse responseJson1 = webResource.path("/CandidateCreation/create").type(MediaType.APPLICATION_FORM_URLENCODED)
		            .post(ClientResponse.class, form);
			System.out.println("Candidate created" + responseJson1.getStatus());
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
