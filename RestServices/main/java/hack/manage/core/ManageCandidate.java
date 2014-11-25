package hack.manage.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;

@Path("/CandidateCreation")
public class ManageCandidate {

	/*
	 * @POST
	 * 
	 * @Path("/create")
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA) public String
	 * create(@FormDataParam("workgroupId") String workgroupId,
	 * 
	 * @FormDataParam("userId") String userId,
	 * 
	 * @FormDataParam("fileName") String content) {
	 * System.out.println(workgroupId + ":" + userId + ":" +content );
	 * 
	 * return null; }
	 */
/*
	@POST
	@Path("uploadImages")
	@Consumes("multipart/mixed")
	@Produces("application/json")
	public Response uploadImagesWithMetadata(final MultiPart multiPart) {

		List<BodyPart> bp = multiPart.getBodyParts();
		for (int i = 0; i < bp.size(); i++) {
			BodyPart b = bp.get(i);

			String imagesetStr = b.getEntityAs(String.class);
			ObjectMapper mapper = new ObjectMapper();
			Entry e;
			try {
				e = mapper
						.readValue(new StringReader(imagesetStr), Entry.class);
				System.out.println(e.getCandidateId());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return Response.ok().build();
	}

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON(Entry student) {
		String output = student.getCandidateId();
		return Response.status(200).entity(output).build();
	}*/

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(@FormParam("managerId") String id,
			@FormParam("candidateId") String profile,
			@FormParam("candidateName") String cName,
			@FormParam("emailId") String email,
			@FormParam("isArchive") String isArchive) throws IOException {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("CandidatesProfilesPerManager");
			DBCollection coll = db.getCollection("profile");
			if(isArchive.equals("false")){
			BasicDBObject doc = new BasicDBObject("managerId", id)
					.append("candidateId", profile)
					.append("candidateName", cName)
					.append("isContacted", true)
					.append("contactDate", new Date())
					.append("isInterested", null).append("location", null)
					.append("emailId", email)
					.append("visa", null);
				coll.insert(doc);
			}else {
				BasicDBObject doc = new BasicDBObject("managerId", id)
				.append("candidateProfile", profile)
				.append("isContacted", false)
				.append("contactDate", new Date())
				.append("isInterested", null).append("location", null)
				.append("emailId", email)
				.append("visa", null);
				coll.insert(doc);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return Response.ok().build();
	}

	@POST
	@Path("/archive")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response archive(@FormParam("managerId") String id,
			@FormParam("candidateId") String profile,
			@FormParam("emailId") String email) throws IOException {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("CandidatesProfilesPerManager");
			DBCollection coll = db.getCollection("profile");
			BasicDBObject doc = new BasicDBObject("managerId", id)
					.append("candidateProfile", profile)
					.append("isContacted", false)
					.append("contactDate", new Date())
					.append("isInterested", null).append("location", null)
					.append("emailId", email)
					.append("visa", null);
			coll.insert(doc);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return Response.ok().build();
	}
}
