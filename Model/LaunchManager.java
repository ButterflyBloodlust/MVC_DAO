package Model;
import java.util.HashMap;
import DAO.LaunchVehicleDAO;
import DAO.LaunchVehicleDAOImpl;
import DAO.VehicleLaunchDAO;
import DAO.VehicleLaunchDAOImpl;

public class LaunchManager {
	
	HashMap<Integer,VehicleLaunch> vls;
	HashMap<Integer,LaunchVehicle> lvs;
	
	VehicleLaunchDAO vlDAO = new VehicleLaunchDAOImpl();
	LaunchVehicleDAO lvDAO = new LaunchVehicleDAOImpl();
	
	public LaunchManager( ) {
		vls = vlDAO.getVehicleLaunches();
		lvs = lvDAO.getLaunchVehicles();
	}
	
	public void registerVehicleLaunch(VehicleLaunch vl) {
		vlDAO.addVehicleLaunch(vl);
	}
	
	public void registerLaunchVehicle(LaunchVehicle lv) {
		lvDAO.addLaunchVehicle(lv);
	}
	
	public VehicleLaunch getVehicleLaunch(int id) {
		return vls.get(id);
	}
	
	public LaunchVehicle getLaunchVehicle(int id) {
		return lvs.get(id);
	}
	
	public HashMap<Integer,VehicleLaunch> getVehicleLaunches() {
		return vls;
	}
	
	public HashMap<Integer,LaunchVehicle> getLaunchVehicles() {
		return lvs;
	}
	
	public void updateLaunchVehicle(LaunchVehicle lv) {
		lvDAO.updateLaunchVehicle(lv);
	}
	
	public void updateVehicleLaunch(VehicleLaunch vl) {
		vlDAO.updateVehicleLaunch(vl);
	}
	
	public void deleteLaunchVehicle(int id) {
		lvDAO.deleteLaunchVehicle(id);
	}
	
	public void deleteVehicleLaunch(int id) {
		vlDAO.deleteLaunchVehicle(id);
	}
	
}
