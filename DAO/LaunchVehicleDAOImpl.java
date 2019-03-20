package DAO;
import java.util.HashMap;
import Model.LaunchVehicle;
import Utility.ObjectPersistance;

public class LaunchVehicleDAOImpl implements LaunchVehicleDAO{
	
	HashMap<Integer,LaunchVehicle> hm = new HashMap<Integer,LaunchVehicle>();  // to avoid duplicating objects
	
	public void addLaunchVehicle(LaunchVehicle lv) {
		ObjectPersistance.saveObject(lv, lv.getId());
		hm.put(lv.getId(), lv);
	}
	
	public LaunchVehicle getLaunchVehicle(int id) {
		if (hm.containsKey(id))
			return hm.get(id);
		else {
			LaunchVehicle lv = (LaunchVehicle) ObjectPersistance.getObject(LaunchVehicle.class, id);
			hm.put(id, lv);
			return lv;
		}
	}
	
	public HashMap<Integer, LaunchVehicle> getLaunchVehicles() {
		for (LaunchVehicle i : ObjectPersistance.getObjectsOfClass(LaunchVehicle.class)) hm.put(i.getId(),i);
		return hm;
	}
	
	public void updateLaunchVehicle(LaunchVehicle lv) {
		deleteLaunchVehicle(lv.getId());
		addLaunchVehicle(lv);
	}
	
	public void deleteLaunchVehicle(int id) {
		ObjectPersistance.removeObject(LaunchVehicle.class, id);
		hm.remove(id);
	}
}
