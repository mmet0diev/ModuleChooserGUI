package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import model.Module;

public class SelectModulesRootPane extends HBox {

    public SelectModulesRootPane(){
        this.setAlignment(Pos.CENTER);

        LeftPaneHalf topLeft = new LeftPaneHalf();
        RightPaneHalf topRight = new RightPaneHalf();
        this.getChildren().add(topLeft);
        this.getChildren().add(topRight);

    }

    private class LeftPaneHalf extends VBox{
        LeftPaneHalf(){
            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(20));
            this.setSpacing(10);

            Label lbl1 = new Label("Unselect Term 1 modules");

            ListView<Module> modOptions1 = new ListView<>();
            modOptions1.setPrefSize(200,150);

            Label lbl2 = new Label("Unselect Term 2 modules");

            ListView<Module> modOptions2 = new ListView<>();
            modOptions2.setPrefSize(200,150);

            //An HBox made up of: Label, Button, Button
            HBox btnBox1 = new HBox();
            btnBox1.setSpacing(20);

            Label lb2 = new Label("Term 1");
            Button addBtn = new Button("Add");
            Button rmBtn = new Button("Remove");

            //add all leaf elements to the HBox
            btnBox1.getChildren().addAll(lb2, addBtn, rmBtn);

            HBox credBox = new HBox();
            credBox.setPadding(new Insets(10));
            credBox.setSpacing(6);

            Label lbCredits = new Label("Current Term 1 credits");
            TextField credTxtField = new TextField();

            credBox.getChildren().addAll(lbCredits, credTxtField);

            this.getChildren().add(lbl1);
            this.getChildren().add(modOptions1);
            this.getChildren().add(btnBox1);
            this.getChildren().add(lbl2);
            this.getChildren().add(modOptions2);
            this.getChildren().add(credBox);
        }
    }
    private class RightPaneHalf extends VBox{
        public RightPaneHalf(){
            this.setPadding(new Insets(10));
            this.setAlignment(Pos.CENTER);

            //"Select Year Long Module" label
            Label lbl1 = new Label("Selected Year Long modules");
            this.getChildren().add(lbl1);

            //Selected Modules for the whole year
            ListView<Module> selectModules = new ListView<>();
            selectModules.setPrefSize(150,50);
            this.getChildren().add(selectModules);

            //"Select term 1 modules" label
            Label lbl2 = new Label("Select term 1 modules");
            this.getChildren().add(lbl2);

            ListView<Module> selectModules1 = new ListView<>();
            selectModules1.setPrefSize(150,150);
            this.getChildren().add(selectModules1);

            //"Select term 1 modules" label
            Label lbl3 = new Label("Select term 2 modules");
            this.getChildren().add(lbl3);

            ListView<Module> selectModules2 = new ListView<>();
            selectModules2.setPrefSize(150,150);
            this.getChildren().add(selectModules2);

            HBox credBox = new HBox();
            credBox.setPadding(new Insets(10));
            credBox.setSpacing(6);

            Label lbCredits = new Label("Current Term 2 credits");
            TextField credTxtField1 = new TextField();

            credBox.getChildren().addAll(lbCredits, credTxtField1);

            this.getChildren().add(credBox);
        }
    }
}
