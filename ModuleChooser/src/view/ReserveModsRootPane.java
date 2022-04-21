package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModsRootPane extends Accordion {
    public ReserveModsRootPane() {
        this.setPadding(new Insets(8));
        //Instantiate Panes
        HBox t1box = new HBox();
        t1box.setPadding(new Insets(10));
        t1box.setSpacing(8);
        NotSelectedT1Mods uSelectModsPane = new NotSelectedT1Mods();
        ReservedT1ModsPane reservedModsPane = new ReservedT1ModsPane();

        t1box.getChildren().addAll(uSelectModsPane, reservedModsPane);

        TitledPane t1 = new TitledPane("Term 1 modules", t1box);
        TitledPane t2 = new TitledPane("Term 2 modules", new HBox());

        this.getPanes().add(t1);
        this.getPanes().add(t2);
    }

    private class NotSelectedT1Mods extends VBox {
        private NotSelectedT1Mods() {
//            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(10));
            this.setSpacing(8);
            Label lb1 = new Label("Unselected Term 1 modules\n");

            ListView<Module> notSelectedModsList = new ListView<>();
            notSelectedModsList.setPrefSize(350, 200);

            Label lb2 = new Label("Reserve 30 credits worth of modules");

            this.getChildren().add(lb1);
            this.getChildren().add(notSelectedModsList);
            this.getChildren().add(lb2);
        }
    }

    private class ReservedT1ModsPane extends VBox {
        public ReservedT1ModsPane() {
            this.setPadding(new Insets(10));
            this.setSpacing(8);

            Label lb1 = new Label("Reserved Term 1 modules\n");

            ListView<Module> reservedModsList = new ListView<>();
            reservedModsList.setPrefSize(350, 200);

            ButtonBar btns = new ButtonBar();
            Button btn1 = new Button("Add");
            Button btn2 = new Button("Remove");
            Button btn3 = new Button("Confirm");

            btns.getButtons().addAll(btn1, btn2, btn3);

            this.getChildren().add(lb1);
            this.getChildren().add(reservedModsList);
            this.getChildren().add(btns);
        }
    }
}
