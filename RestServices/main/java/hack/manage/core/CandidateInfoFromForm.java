package hack.manage.core;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Path("/CandidateInfo")
public class CandidateInfoFromForm {
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@FormParam("isInterested") List<String> param,
                                @FormParam("visaType") String visaType,
                                @FormParam("location") String location,
                                @FormParam("managerId") String managerId,
                                @FormParam("candidateId") String candidateId) {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
			DB db = mongoClient.getDB( "CandidatesProfilesPerManager" );
			DBCollection coll = db.getCollection("profile");
			
			boolean interested = false;
			for(int i=0;i<param.size();i++){
				System.out.println("IsInterested:" + param.get(i));
				if(param.get(i).equals("a")){
					interested = true;
				}
			}
			
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", 
				new BasicDBObject().append("isInterested", interested)
				.append("visa", visaType)
				.append("location", location));
		 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("managerId", managerId);
			searchQuery.append("candidateProfile", candidateId);
		 
			coll.update(searchQuery, updateQuery);
			
			DBObject myDoc1 = coll.findOne();
			return Response.ok(myDoc1.toString()).build();
			
		}catch(Exception e){
			e.printStackTrace();
			return Response.ok("Data Saved").build();
		}
    }                   

	@GET
	@Path("/sendEmail")
	public Response sendEMail(){
		LinkedinClient lc = new LinkedinClient();

      MemberSwetha m1 = new MemberSwetha();
     MemberGeetanjali m2 = new MemberGeetanjali();

     String x = lc.postMessage(m1,m2,"I7Im5VzJrW","ZwE5qwMdwI","www.linkedin.com/profile/view?id=44645743");
		return Response.ok(x).build();
	}
}
