package hack.manage.core;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Path("/manager")
public class ManagerManagement {

	@GET
	@Path("/{param}")
	@Produces("application/json")
	public String getManager(@PathParam("param") String managerid) {
		System.out.println("Jersey say : " + managerid);
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "CandidatesProfilesPerManager" );
			DBCollection coll = db.getCollection("profile");
			BasicDBObject query = new BasicDBObject("managerId", managerid);
			DBCursor cursor = coll.find(query);
			
			List<CandidatesPerManger> cmList = new ArrayList<CandidatesPerManger>();

			try {
			   while(cursor.hasNext()) {
				   CandidatesPerManger cm = new CandidatesPerManger();
				   BasicDBObject dbObj = (BasicDBObject) cursor.next();
				   cm.setManagerId(dbObj.getString("managerId"));
				   cm.setCandidateName(dbObj.getString("candidateName"));
				   cm.setCandidateProfile(dbObj.getString("candidateId"));
				   cm.setContacted(dbObj.getBoolean("isContacted"));
				   cm.setInterested(dbObj.getBoolean("isInterested"));
				   cm.setVisaType(dbObj.getString("visa"));
				   cm.setLocation(dbObj.getString("location"));
				   cm.setContactDate(dbObj.getDate("contactDate"));
				   cm.setEmail(dbObj.getString("emailId"));
				   cmList.add(cm);
				   
				  /* 
				   //cm = gson.fromJson(json, CandidatesPerManger.class);
				   System.out.println("cm" + gson.toJson(cm));   
				   return gson.toJson(cm);*/
			   }
			   Gson gson = new Gson();
			   JsonElement jsonElement = gson.toJsonTree(cmList);
			   //jsonElement.getAsJsonObject().addProperty("url_to_user", url);
			   return gson.toJson(jsonElement);
			} finally {
			   cursor.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
