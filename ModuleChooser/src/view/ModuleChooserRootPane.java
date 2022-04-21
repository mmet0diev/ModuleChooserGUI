package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;


public class ModuleChooserRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private ModuleChooserMenuBar mcmb;
	private TabPane tp;
	private SelectModulesRootPane selectModsPane;
	private ReserveModsRootPane resModsPane;
	
	public ModuleChooserRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		selectModsPane = new SelectModulesRootPane();
		resModsPane = new ReserveModsRootPane();

		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select modules", selectModsPane);
		Tab t3 = new Tab("Reserve modules" ,resModsPane);

		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3);
		
		//create menu bar
		mcmb = new ModuleChooserMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mcmb);
		this.setCenter(tp);
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	
	public ModuleChooserMenuBar getModuleSelectionToolMenuBar() {
		return mcmb;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
