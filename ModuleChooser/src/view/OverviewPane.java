package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

public class OverviewPane extends VBox {
    public OverviewPane() {
        this.setPadding(new Insets(8));
        this.setSpacing(8);
        this.setAlignment(Pos.TOP_CENTER);

        ListView<Module> profileList = new ListView<>();
        profileList.setPrefSize(400, 75);

        //Inner HBox holding 2 listViews
        HBox listViewBox = new HBox();
        listViewBox.setAlignment(Pos.CENTER);
        listViewBox.setSpacing(8);
        ListView<Module> selectModsList = new ListView<>();
        selectModsList.setPrefSize(400, 350);
        ListView<Module> reserveModsList = new ListView<>();
        reserveModsList.setPrefSize(400, 350);

        listViewBox.getChildren().addAll(selectModsList, reserveModsList);

        Button svBtn = new Button("Save Overview");

        this.getChildren().add(profileList);
        this.getChildren().add(listViewBox);
        this.getChildren().add(svBtn);
    }
}
