package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModsRootPane extends Accordion {
    private ListView<Module> unselectedModsList1, unselectedModsList2, reservedModsList1, reservedModsList2;
    private Button addBtn1, rmBtn1, confirmBtn1, addBtn2, rmBtn2, confirmBtn2;

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
            VBox unselectModsBox = new VBox();
            VBox reserveModsBox = new VBox();
            //Label 1
            Label lb1 = new Label("Unselected Term 1 modules\n");

            //List view for not selected modules term 1
            unselectedModsList1 = new ListView<>();
            unselectedModsList1.setPrefSize(400, 250);

            //Label 2
            Label lb2 = new Label("Reserve 30 credits worth of modules");

            Label lb3 = new Label("Reserved Term 1 modules\n");

            reservedModsList1 = new ListView<>();
            reservedModsList1.setPrefSize(400, 250);

            ButtonBar btns = new ButtonBar();
            addBtn1 = new Button("Add");
            rmBtn1 = new Button("Remove");
            confirmBtn1 = new Button("Confirm");

            btns.getButtons().addAll(addBtn1, rmBtn1, confirmBtn1);

            //Set the spacing for the elements inside each VBox
            unselectModsBox.setSpacing(6);
            reserveModsBox.setSpacing(6);

            //Add each sub node/element to the VBoxes
            unselectModsBox.getChildren().addAll(lb1, unselectedModsList1, lb2);
            reserveModsBox.getChildren().addAll(lb3, reservedModsList1, btns);

            //Add the VBoxes to the containing HBox
            this.getChildren().addAll(unselectModsBox, reserveModsBox);
        }
    }

    private class SubPaneReserveMods2 extends HBox{
        private SubPaneReserveMods2(){
            this.setPadding(new Insets(10));
            this.setSpacing(8);

            //Instantiate 2 VBoxes, left VBox and right VBox
            VBox unselectModsBox = new VBox();
            VBox reserveModsBox = new VBox();
            //Label 1
            Label lb1 = new Label("Unselected Term 2 modules\n");

            //List view for not selected modules term 1
            unselectedModsList2 = new ListView<>();
            unselectedModsList2.setPrefSize(400, 250);

            //Label 2
            Label lb2 = new Label("Reserve 30 credits worth of modules");

            Label lb3 = new Label("Reserved Term 2 modules\n");

            reservedModsList2 = new ListView<>();
            reservedModsList2.setPrefSize(400, 250);

            ButtonBar btns = new ButtonBar();
            addBtn2 = new Button("Add");
            rmBtn2 = new Button("Remove");
            confirmBtn2 = new Button("Confirm");

            btns.getButtons().addAll(addBtn2, rmBtn2, confirmBtn2);

            //Set the spacing for the elements inside each VBox
            unselectModsBox.setSpacing(6);
            reserveModsBox.setSpacing(6);

            //Add each sub node/element to the VBoxes
            unselectModsBox.getChildren().addAll(lb1, unselectedModsList2, lb2);
            reserveModsBox.getChildren().addAll(lb3, reservedModsList2, btns);

            //Add the VBoxes to the containing HBox
            this.getChildren().addAll(unselectModsBox, reserveModsBox);
        }
    }
    public ListView<Module> getUnselectedModsList1(){
        return unselectedModsList1;
    }

    public ListView<Module> getUnselectedModsList2(){
        return unselectedModsList2;
    }

    public ListView<Module> getReservedModsList1(){
        return reservedModsList1;
    }

    public ListView<Module> getReservedModsList2(){
        return reservedModsList2;
    }
}
