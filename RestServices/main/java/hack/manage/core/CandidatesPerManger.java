package hack.manage.core;

import java.util.Date;

public class CandidatesPerManger {

	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getCandidateProfile() {
		return candidateId;
	}
	public void setCandidateProfile(String candidateProfile) {
		this.candidateId = candidateProfile;
	}
	public boolean isContacted() {
		return isContacted;
	}
	public void setContacted(boolean isContacted) {
		this.isContacted = isContacted;
	}
	public boolean isInterested() {
		return isInterested;
	}
	public void setInterested(boolean isInterested) {
		this.isInterested = isInterested;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	String managerId;
	String candidateId;
	boolean isContacted;
	boolean isInterested;
	String visaType;
	String location;
	Date contactDate;	
	String email;
	String candidateName;
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
