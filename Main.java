import Controller.LaunchManagerController;
import Model.LaunchManager;
import View.LaunchManagerView;

public class Main {
	public static void main(String[] args) {
		
		LaunchManager model = new LaunchManager();
		LaunchManagerView view = new LaunchManagerView(model);
		LaunchManagerController controller = new LaunchManagerController(model, view);
		
		view.init();
		controller.close();
	}
}

/*
	Vehicle launches can be stored as list in Launch vehicles. This makes serialization of just two corresponding entities easier.
	Alternatively Vehicle launch can store reference to Launch vehicle but then we need to take care of duplicates (WeakHashMap in Model / DAO?)
*/

/*
 	-c -vl -1, 2019-03-02T10:15:30-06:00, CCAFS LC-40, 4875, false
 	-c -lv B01, Atlas V, 10000, 2, 7600
 */

/*
LaunchVehicle lv = new LaunchVehicle();
ObjectPersistance.saveObject(lv, 1);
ObjectPersistance.removeObject(lv.getClass().getSimpleName(), 1);
*/
//System.out.println(ZonedDateTime.parse("2019-03-02T10:15:30-06:00"));
//new View();

/*
VehicleLaunch vl = new VehicleLaunch();
VehicleLaunchDAO vlDAO = new VehicleLaunchDAOImpl();
vlDAO.addVehicleLaunch(vl);
System.out.println(vlDAO.getVehicleLaunches());
vlDAO.deleteLaunchVehicle(vl.getId());

LaunchVehicle lv = new LaunchVehicle();
LaunchVehicleDAO lvDAO = new LaunchVehicleDAOImpl();
lvDAO.addVehicleLaunch(lv);
System.out.println(lvDAO.getVehicleLaunches());
lvDAO.deleteLaunchVehicle(lv.getId());
*/

/*
LaunchVehicle lv = new LaunchVehicle(idsGen, "B01", "Atlas V", 10000, 2, 7600); 
VehicleLaunch vl1 = new VehicleLaunch(idsGen, lv, "2019-03-02T10:15:30-06:00", "CCAFS LC-40", 4875, false);
VehicleLaunch vl2 = new VehicleLaunch(idsGen, lv, "2019-03-02T10:15:30-06:00", "CCAFS LC-40", 4875, false);
VehicleLaunchDAO vlDAO = new VehicleLaunchDAOImpl();
vlDAO.addVehicleLaunch(vl1);
vlDAO.addVehicleLaunch(vl2);
LaunchVehicleDAO lvDAO = new LaunchVehicleDAOImpl();
lvDAO.addVehicleLaunch(lv);

vl1 = vlDAO.getVehicleLaunch(1);
System.out.println(vl1);
vl2 = vlDAO.getVehicleLaunch(1);
System.out.println(vl2);
System.out.println(vl2.getLaunchVehicle());
vlDAO.getVehicleLaunches().stream().filter(vl -> vl.getId() == 1);
*/