package Controller;
import java.util.ArrayList;
import java.util.List;
import Model.LaunchVehicle;
import Model.LaunchManager;
import Model.VehicleLaunch;
import Utility.ObjectIdsGenerator;
import Utility.ObjectPersistance;
import View.LaunchManagerView;

public class LaunchManagerController {
	private LaunchManager model;
	private LaunchManagerView view;
	private ObjectIdsGenerator idsGen;
	
	public LaunchManagerController(LaunchManager _model, LaunchManagerView _view) {
		idsGen = ObjectIdsGenerator.getInstance();
		this.model = _model;
		this.view = _view;
		
		view.addCreateVlListener(args -> {
			String[] argsTab = args[0].split(",\\s");
			VehicleLaunch vl = new VehicleLaunch(idsGen, Integer.parseInt(argsTab[0]) > -1 ? model.getLaunchVehicle(Integer.parseInt(argsTab[0])) : null,
					argsTab[1], argsTab[2], Integer.parseInt(argsTab[3]), Boolean.parseBoolean(argsTab[4]));
			model.registerVehicleLaunch(vl);
		});
		
		view.addCreateLvListener(args -> {
			String[] argsTab = args[0].split(",\\s");
			LaunchVehicle lv = new LaunchVehicle(idsGen, argsTab[0], argsTab[1], Integer.parseInt(argsTab[2]), Integer.parseInt(argsTab[3]), Integer.parseInt(argsTab[4]));
			model.registerLaunchVehicle(lv);
		});
		
		/*
		view.addReadLvListener(args -> {
		});
		
		view.addReadVlListener(args -> {
		});
		*/
		
		view.addUpdateLvListener(args -> {
			String[] argsTab = args[1].split(",\\s");
			
			LaunchVehicle lv = model.getLaunchVehicle(Integer.parseInt(args[0])); 
			lv.bulkSet(argsTab[0], argsTab[1], Integer.parseInt(argsTab[2]), Integer.parseInt(argsTab[3]), Integer.parseInt(argsTab[4]));
			model.updateLaunchVehicle(lv);
		});
		
		
		view.addUpdateVlListener(args -> {
			String[] argsTab = args[1].split(",\\s");
			
			VehicleLaunch vl = model.getVehicleLaunch(Integer.parseInt(args[0])); 
			vl.bulkSet(Integer.parseInt(argsTab[0]) > -1 ? model.getLaunchVehicle(Integer.parseInt(argsTab[0])) : null,
					argsTab[1], argsTab[2], Integer.parseInt(argsTab[3]), Boolean.parseBoolean(argsTab[4]));
			model.updateVehicleLaunch(vl);
		});
		
		view.addDeleteLvListener(args -> {
			if (args.equals("all")) {
				List<LaunchVehicle> list = new ArrayList<LaunchVehicle>(model.getLaunchVehicles().values());
				for (LaunchVehicle entry : list)
					model.deleteLaunchVehicle(entry.getId());
			}
			else {
				String[] ids = args[0].split(",\\s*");
				for (String id : ids)
					model.deleteLaunchVehicle(Integer.parseInt(id));
			}
		});
		
		view.addDeleteVlListener(args -> {
			if (args.equals("all")) {
				List<VehicleLaunch> list = new ArrayList<VehicleLaunch>(model.getVehicleLaunches().values());
				for (VehicleLaunch entry : list)
					model.deleteVehicleLaunch(entry.getId());
			}
			else {
				String[] ids = args[0].split(",\\s*");
				for (String id : ids)
					model.deleteVehicleLaunch(Integer.parseInt(id));
			}
		});
	}//public LaunchManagerController(LaunchManager _model, LaunchManagerView _view) {
	
	public void close() {
		ObjectPersistance.saveObject(idsGen, 1);
	}
}
