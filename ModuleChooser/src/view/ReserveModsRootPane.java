package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ReserveModsRootPane extends Accordion {
    TitledPane t1, t2;
    private ListView<Module> unselectedModsList1, unselectedModsList2, reservedModsList1, reservedModsList2;
    private Button addBtn1, rmBtn1, confirmBtn1, addBtn2, rmBtn2, confirmBtn2;
    private int resCredits1, resCredits2;

    public ReserveModsRootPane() {
        this.setPadding(new Insets(8));
        //Instantiate Panes
        SubPaneReserveMods1 reserveMods1 = new SubPaneReserveMods1();
        SubPaneReserveMods2 reserveMods2 = new SubPaneReserveMods2();

        t1 = new TitledPane("Term 1 modules", reserveMods1);
        t2 = new TitledPane("Term 2 modules", reserveMods2);

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
            Label lb2 = new Label("Reserve 30 credits worth of modules (each worth 15)");

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
            Label lb2 = new Label("Reserve 30 credits worth of modules (each worth 15)");

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

//    public void enableT2(){
//        t2.setDisable(true);
//    }

    public int getResCredits1(){
        return resCredits1;
    }

    public int getResCredits2(){
        return resCredits2;
    }

    public void incrementResCreds1(){resCredits1+=15;}
    public void incrementResCreds2(){resCredits2+=15;}
    public void decrementResCreds1(){resCredits1-=15;}
    public void decrementResCreds2(){resCredits2-=15;}

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

    public void addAddBtn1Handler(EventHandler<ActionEvent> handler){
        addBtn1.setOnAction(handler);
    }
    public void addAddBtn2Handler(EventHandler<ActionEvent> handler){
        addBtn2.setOnAction(handler);
    }
    public void addRmBtn1Handler(EventHandler<ActionEvent> handler){
        rmBtn1.setOnAction(handler);
    }
    public void addRmBtn2Handler(EventHandler<ActionEvent> handler){
        rmBtn2.setOnAction(handler);
    }
    public void addConfirmBtnHandler1(EventHandler<ActionEvent> handler){
        confirmBtn1.setOnAction(handler);
    }
    public void addConfirmBtnHandler2(EventHandler<ActionEvent> handler){
        confirmBtn2.setOnAction(handler);
    }
}
