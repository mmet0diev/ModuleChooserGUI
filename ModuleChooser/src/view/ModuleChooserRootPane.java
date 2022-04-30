package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;


public class ModuleChooserRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private ModuleChooserMenuBar mcmb;
	private TabPane tp;
	private SelectModulesRootPane selectModsPane;
	private ReserveModsRootPane reserveModsPane;
	private OverviewPane op;
	
	public ModuleChooserRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);


		//create panes
		cspp = new CreateStudentProfilePane();
		selectModsPane = new SelectModulesRootPane();
		reserveModsPane = new ReserveModsRootPane();
		op = new OverviewPane();


		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select modules", selectModsPane);
		Tab t3 = new Tab("Reserve modules" , reserveModsPane);
		Tab t4 = new Tab("Overview", op);

		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4);
		
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

	public SelectModulesRootPane getSelectModulesPane(){ return selectModsPane;	}

	public ReserveModsRootPane getReserveModsPane(){ return reserveModsPane;}


	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}

}
