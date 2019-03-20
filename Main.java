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
  Example usage:
 	-c -vl -1, 2019-03-02T10:15:30-06:00, CCAFS LC-40, 4875, false
 	-c -lv B01, Atlas V, 10000, 2, 7600
 */
