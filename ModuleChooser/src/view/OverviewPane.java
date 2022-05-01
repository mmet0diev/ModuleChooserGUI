package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OverviewPane extends VBox {
    private ListView<String> studentDataList;
    private ListView<Module> selectModsList, reserveModsList;
    private Button saveBtn;

    public OverviewPane() {
        this.setPadding(new Insets(8));
        this.setSpacing(8);
        this.setAlignment(Pos.TOP_CENTER);

        studentDataList = new ListView<>();
        studentDataList.setPrefSize(400, 75);

        //Inner HBox holding 2 listViews
        HBox listViewBox = new HBox();
        listViewBox.setAlignment(Pos.CENTER);
        listViewBox.setSpacing(8);
        selectModsList = new ListView<>();
        selectModsList.setPrefSize(400, 350);
        reserveModsList = new ListView<>();
        reserveModsList.setPrefSize(400, 350);

        listViewBox.getChildren().addAll(selectModsList, reserveModsList);

        saveBtn = new Button("Save Overview");

        this.getChildren().add(studentDataList);
        this.getChildren().add(listViewBox);
        this.getChildren().add(saveBtn);
    }

    public ListView<Module> getSelectModsList(){
        return selectModsList;
    }

    public ListView<Module> getReserveModsList(){
        return reserveModsList;
    }

    public ListView<String> getStudentDataList(){return studentDataList;}

    public void addSaveBtnHandler(EventHandler<ActionEvent> handler){ saveBtn.setOnAction(handler);}

}
