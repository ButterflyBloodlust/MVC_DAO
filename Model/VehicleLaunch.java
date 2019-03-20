package Model;
import java.io.Serializable;
import java.time.ZonedDateTime;
import Utility.ObjectIdsGenerator;

public class VehicleLaunch implements Serializable{

	private static final long serialVersionUID = 8860587335362790060L;  // Generated automatically

	private int id;
	private LaunchVehicle launchVehicle;  // transient
	//private int launchVehicleId;
	private ZonedDateTime launchDate;
	private String launchSite;
	private int payloadMass;
	private boolean launchSuccess = false;
	
	public VehicleLaunch(ObjectIdsGenerator oIdGen, LaunchVehicle launchVehicle, String launchDate, String launchSite,
			int payloadMass, boolean launchSuccess) {
		super();
		this.id = oIdGen.getAndIncrementIdVl();
		this.launchVehicle = launchVehicle;
		//this.launchVehicleId = launchVehicle == null ? -1 : launchVehicle.getId();
		this.launchDate = ZonedDateTime.parse(launchDate);
		this.launchSite = launchSite;
		this.payloadMass = payloadMass;
		this.launchSuccess = launchSuccess;
	}
	
	public VehicleLaunch() {}
	
	public int getId() { return id; }

	public LaunchVehicle getLaunchVehicle() {
		return launchVehicle;
	}

	public void setLaunchVehicle(LaunchVehicle launchVehicle) {
		this.launchVehicle = launchVehicle;
	}

	public boolean wasAttempted() {
		return launchDate != null && ZonedDateTime.now(launchDate.getZone()).isAfter(launchDate);
	}
	
	public void bulkSet(LaunchVehicle launchVehicle, String launchDate, String launchSite, int payloadMass, boolean launchSuccess) {
		this.launchVehicle = launchVehicle;
		//this.launchVehicleId = launchVehicle == null ? -1 : launchVehicle.getId();
		this.launchDate = ZonedDateTime.parse(launchDate);
		this.launchSite = launchSite;
		this.payloadMass = payloadMass;
		this.launchSuccess = launchSuccess;
	}
	
	public String toString() {
		return "<< " + "vehicle id: " + launchVehicle == null ? "-1" : launchVehicle.getId() + ", date: " + launchDate.toString() + ", site: " + launchSite + ", payload mass: " + payloadMass +  ", success: " + launchSuccess + " >>";
	}
}

/*
	 private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		 if (launchVehicleId > 0) {
			 LaunchVehicleDAO lvDAO = new LaunchVehicleDAOImpl();
			 launchVehicle = lvDAO.getLaunchVehicle(launchVehicleId);
		 }
	 }
*/
