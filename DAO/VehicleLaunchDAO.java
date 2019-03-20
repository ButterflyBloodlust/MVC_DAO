package DAO;
import java.util.HashMap;
import Model.VehicleLaunch;

public interface VehicleLaunchDAO {
	
	public void addVehicleLaunch(VehicleLaunch vl);
	
	public VehicleLaunch getVehicleLaunch(int id);
	
	public HashMap<Integer, VehicleLaunch> getVehicleLaunches();
	
	public void updateVehicleLaunch(VehicleLaunch vl);
	
	public void deleteLaunchVehicle(int id);
}
