package DAO;
import java.util.HashMap;
import Model.VehicleLaunch;
import Utility.ObjectPersistance;

public class VehicleLaunchDAOImpl implements VehicleLaunchDAO{
	
	HashMap<Integer,VehicleLaunch> hm = new HashMap<Integer,VehicleLaunch>();  // to avoid duplicating objects
	
	public void addVehicleLaunch(VehicleLaunch vl) {
		ObjectPersistance.saveObject(vl, vl.getId());
		hm.put(vl.getId(), vl);
	}
	
	public VehicleLaunch getVehicleLaunch(int id) {
		if (hm.containsKey(id))
			return hm.get(id);
		else {
			VehicleLaunch vl = (VehicleLaunch) ObjectPersistance.getObject(VehicleLaunch.class, id);
			hm.put(id, vl);
			return vl;
		}
	}
	
	public HashMap<Integer, VehicleLaunch> getVehicleLaunches() {
		for (VehicleLaunch i : ObjectPersistance.getObjectsOfClass(VehicleLaunch.class)) hm.put(i.getId(),i);
		return hm;
	}
	
	public void updateVehicleLaunch(VehicleLaunch vl) {
		deleteLaunchVehicle(vl.getId());
		addVehicleLaunch(vl);
	}
	
	public void deleteLaunchVehicle(int id) {
		ObjectPersistance.removeObject(VehicleLaunch.class, id);
		hm.remove(id);
	}
}
