package DAO;
import java.util.HashMap;
import Model.LaunchVehicle;

public interface LaunchVehicleDAO {
	public void addLaunchVehicle(LaunchVehicle lv);
	
	public LaunchVehicle getLaunchVehicle(int id);
	
	public HashMap<Integer, LaunchVehicle> getLaunchVehicles();
	
	public void updateLaunchVehicle(LaunchVehicle lv);
	
	public void deleteLaunchVehicle(int id);
}
