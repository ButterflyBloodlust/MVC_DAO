package View;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Model.LaunchManager;
import Utility.ActionListener;

public class LaunchManagerView {
	
	boolean run = true;
	LaunchManager model;
	HashMap<String, ActionListener> actListeners = new HashMap<>();
	
	public LaunchManagerView(LaunchManager model) {
		this.model = model;
		//init();
	}
	
	public void addCreateLvListener(ActionListener al) { actListeners.put("-c -lv", al); }
	public void addCreateVlListener(ActionListener al) { actListeners.put("-c -vl", al); }
	public void addReadLvListener(ActionListener al) { actListeners.put("-r -lv", al); }
	public void addReadVlListener(ActionListener al) { actListeners.put("-r -vl", al); }
	public void addUpdateLvListener(ActionListener al) { actListeners.put("-u -lv", al); }
	public void addUpdateVlListener(ActionListener al) { actListeners.put("-u -vl", al); }
	public void addDeleteLvListener(ActionListener al) { actListeners.put("-d -lv", al); }
	public void addDeleteVlListener(ActionListener al) { actListeners.put("-d -vl", al); }
	
	public void init() {
		System.out.println("-----------------------------------");
		System.out.println(" WELCOME TO LAUNCH SCHEDULE CONTOL");
		System.out.println("-----------------------------------");
		System.out.println("Please specify what you want to do:");
		
		Scanner sc;
		while (run) {
			boolean argsMissing = false;
			sc = new Scanner(System.in);
			String args[] = sc.nextLine().split("\\s+");
			System.out.println(Arrays.toString(args));
			
			if (args[0].equals("-c")) {
				if (args.length < 2) {
					argsMissing = true;
				}
				else {
					if (args[1].equals("-lv")) {
						// Create launch vehicle
						sc = new Scanner(System.in);
						actListeners.get("-c -lv").actionPerformed(new String[] {sc.nextLine()});
					}
					else if (args[1].equals("-vl")) {
						// Create vehicle launch
						sc = new Scanner(System.in);
						actListeners.get("-c -vl").actionPerformed(new String[] {sc.nextLine()});
					}
					else {
						argsMissing = true;
					}
				}
			}
			else if (args[0].equals("-r")) {
				if (args.length < 2) {
					argsMissing = true;
				}
				else {
					if (args.length == 3 && !args[2].equals("all") && !isNonNegativeNumeric(args[2])) {
						argsMissing = true;
					}
					else if (args[1].equals("-lv")) {
						// Read launch vehicle
						//actListeners.get("-r -lv").actionPerformed(args.length < 3 ? "all" : args[2]);
						if (args.length < 3 || args[2].equals("all")) 
							display(model.getLaunchVehicles());
						else
							System.out.println(args[2] + " = " + model.getLaunchVehicle(Integer.parseInt(args[2])).toString());
					}
					else if (args[1].equals("-vl")) {
						// Read vehicle launch
						//actListeners.get("-r -vl").actionPerformed(args.length < 3 ? "all" : args[2]);
						if (args.length < 3 || args[2].equals("all")) 
							display(model.getVehicleLaunches());
						else
							System.out.println(args[2] + " = " + model.getVehicleLaunch(Integer.parseInt(args[2])).toString());
					}
					else {
						argsMissing = true;
					}
				}
			}
			else if (args[0].equals("-u") && args.length > 1) {
				if (args.length < 3) {
					argsMissing = true;
				}
				else {
					if (!isNonNegativeNumeric(args[2])) {
						argsMissing = true;
					}
					else if (args[1].equals("-lv")) {
						// Update launch vehicle
						actListeners.get("-u -lv").actionPerformed(new String[] {args[2], sc.nextLine()});
					}
					else if (args[1].equals("-vl")) {
						// Update vehicle launch	
						actListeners.get("-u -vl").actionPerformed(new String[] {args[2], sc.nextLine()});
					}
					else {
						argsMissing = true;
					}
				}
			}
			else if (args[0].equals("-d")) {
				if (args.length < 3) {
					argsMissing = true;
				}
				else {
					if (!args[2].equals("all") && !isNonNegativeNumeric(args[2].replaceAll(",\\s*", ""))) {
						argsMissing = true;
					}
					else if (args[1].equals("-lv")) {
						// Delete launch vehicle
						actListeners.get("-d -lv").actionPerformed(new String[] {args[2]});
					}
					else if (args[1].equals("-vl")) {
						// Delete vehicle launch
						actListeners.get("-d -vl").actionPerformed(new String[] {args[2]});
					}
					else {
						argsMissing = true;
					}
				}
			}
			else if (args[0].equals("?")) {
				System.out.println("  '-c' creates entity");
				System.out.println("      '-lv' registers specified launch vehicle");
				System.out.println("           '\"<String id>, <String modelName>, <int payloadCapacity>, <int stages>, <int thrustInKN>\"'");
				System.out.println("      '-vl' registers specified vehicle launch");
				System.out.println("           '\"<int launchVehicleId>, <String launchDate>, <String launchSite>, <int payloadMass>, <boolean launchSuccess>\"'");
				System.out.println("  '-r' reads entity");
				System.out.println("      '-lv' reads specified launch vehicles");
				System.out.println("      '-vl' reads specified vehicle launches");
				System.out.println("  '-u' updates entity");
				System.out.println("      '-lv' updates specified launch vehicle");
				System.out.println("      '-vl' updates specified vehicle launch");
				System.out.println("  '-d' deletes entity");
				System.out.println("      '-lv' deletes specified launch vehicles");
				System.out.println("      '-vl' deletes specified vehicle launches");
			}
			else if (args[0].equals("stop")) {
				run = false;
				System.out.println("Exiting");
			}
			else {
				System.out.println("Unrecognized command (use '?' for help)");
			}
			
			if (argsMissing) {
				System.out.println("Command arguments missing or ivalid (use '?' for help)");
			}
		}//while (run)
	}//init()
	
	public boolean isNonNegativeNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	public <T,S> void display(HashMap<T,S> hm) {
		for (Map.Entry<T, S> entry : hm.entrySet())
			System.out.println(entry.getKey() + " = " + entry.getValue().toString());
	}

}
