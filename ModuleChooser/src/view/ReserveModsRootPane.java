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
        SubPaneReserveMods1 reserveMods1 = new SubPaneReserveMods1();
        SubPaneReserveMods2 reserveMods2 = new SubPaneReserveMods2();

        TitledPane t1 = new TitledPane("Term 1 modules", reserveMods1);
        TitledPane t2 = new TitledPane("Term 2 modules", reserveMods2);

        this.getPanes().add(t1);
        this.getPanes().add(t2);
    }

    private class SubPaneReserveMods1 extends HBox{
        private SubPaneReserveMods1(){
            this.setPadding(new Insets(10));
            this.setSpacing(8);

            //Instantiate 2 VBoxes, left VBox and right VBox
            VBox unSelectModsBox = new VBox();
            VBox reserveModsBox = new VBox();
            //Label 1
            Label lb1 = new Label("Unselected Term 1 modules\n");

            //List view for not selected modules term 1
            ListView<Module> notSelectedModsList = new ListView<>();
            notSelectedModsList.setPrefSize(400, 250);

            //Label 2
            Label lb2 = new Label("Reserve 30 credits worth of modules");

            Label lb3 = new Label("Reserved Term 1 modules\n");

            ListView<Module> reservedModsList = new ListView<>();
            reservedModsList.setPrefSize(400, 250);

            ButtonBar btns = new ButtonBar();
            Button btn1 = new Button("Add");
            Button btn2 = new Button("Remove");
            Button btn3 = new Button("Confirm");

            btns.getButtons().addAll(btn1, btn2, btn3);

            //Set the spacing for the elements inside each VBox
            unSelectModsBox.setSpacing(6);
            reserveModsBox.setSpacing(6);

            //Add each sub node/element to the VBoxes
            unSelectModsBox.getChildren().addAll(lb1, notSelectedModsList, lb2);
            reserveModsBox.getChildren().addAll(lb3, reservedModsList, btns);

            //Add the VBoxes to the containing HBox
            this.getChildren().addAll(unSelectModsBox, reserveModsBox);
        }
    }

    private class SubPaneReserveMods2 extends HBox{
        private SubPaneReserveMods2(){
            this.setPadding(new Insets(10));
            this.setSpacing(8);

            //Instantiate 2 VBoxes, left VBox and right VBox
            VBox unSelectModsBox = new VBox();
            VBox reserveModsBox = new VBox();
            //Label 1
            Label lb1 = new Label("Unselected Term 2 modules\n");

            //List view for not selected modules term 1
            ListView<Module> notSelectedModsList = new ListView<>();
            notSelectedModsList.setPrefSize(400, 250);

            //Label 2
            Label lb2 = new Label("Reserve 30 credits worth of modules");

            Label lb3 = new Label("Reserved Term 2 modules\n");

            ListView<Module> reservedModsList = new ListView<>();
            reservedModsList.setPrefSize(400, 250);

            ButtonBar btns = new ButtonBar();
            Button btn1 = new Button("Add");
            Button btn2 = new Button("Remove");
            Button btn3 = new Button("Confirm");

            btns.getButtons().addAll(btn1, btn2, btn3);

            //Set the spacing for the elements inside each VBox
            unSelectModsBox.setSpacing(6);
            reserveModsBox.setSpacing(6);

            //Add each sub node/element to the VBoxes
            unSelectModsBox.getChildren().addAll(lb1, notSelectedModsList, lb2);
            reserveModsBox.getChildren().addAll(lb3, reservedModsList, btns);

            //Add the VBoxes to the containing HBox
            this.getChildren().addAll(unSelectModsBox, reserveModsBox);
        }
    }
}
