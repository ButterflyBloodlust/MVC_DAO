package Model;
import java.io.Serializable;

import Utility.ObjectIdsGenerator;

public class LaunchVehicle implements Serializable{

	private static final long serialVersionUID = -6430380104802960948L;  // Generated automatically
	
	private int id;
	private String vehicleId;
	private String modelName;
	private int payloadCapacity;  // in kg; to LEO
	private int stages;
	private int thrustInKN;
	
	public LaunchVehicle(ObjectIdsGenerator oIdGen, String vehicleId, String modelName, int payloadCapacity, int stages, int thrustInKN) {
		super();
		this.id = oIdGen.getAndIncrementIdLv();
		this.vehicleId = vehicleId;
		this.modelName = modelName;
		this.payloadCapacity = payloadCapacity;
		this.stages = stages;
		this.thrustInKN = thrustInKN;
	}
	
	public LaunchVehicle() {}
	
	public int getId() { return id; }
	
	public void bulkSet(String vehicleId, String modelName, int payloadCapacity, int stages, int thrustInKN) {
		this.vehicleId = vehicleId;
		this.modelName = modelName;
		this.payloadCapacity = payloadCapacity;
		this.stages = stages;
		this.thrustInKN = thrustInKN;
	}
	
	public String toString() { 
		return "<< " + vehicleId + ", " + modelName + ", capacity: " + payloadCapacity + "kg, stages: " + stages + ", thrust: " + thrustInKN + "kN >>";
	}
	
}
