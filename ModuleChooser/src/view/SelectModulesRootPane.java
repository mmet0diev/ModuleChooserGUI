package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import model.Course;
import model.Module;
import model.Schedule;


public class SelectModulesRootPane extends HBox {

    private ListView<Module> unSelectedMods1, unSelectedMods2, selectedYLongMods, selectedMods1, selectedMods2;
    private Button addBtn1, rmBtn1, submitBtn, addBtn2, rmBtn2 ,resetBtn;
    private TextField credTxtField1, credTxtField2;
    private boolean created;
    private int credits;

    public SelectModulesRootPane(){
        this.setAlignment(Pos.CENTER);

        LeftPaneHalf topLeft = new LeftPaneHalf();
        RightPaneHalf topRight = new RightPaneHalf();
        this.getChildren().add(topLeft);
        this.getChildren().add(topRight);

    }

    private class LeftPaneHalf extends VBox{
        private LeftPaneHalf(){
            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(20));
            this.setSpacing(10);

            Label lbl1 = new Label("Unselected Term 1 modules");

            unSelectedMods1 = new ListView<>();
            unSelectedMods1.setPrefSize(200,150);

            Label lbl2 = new Label("Unselected Term 2 modules");

            unSelectedMods2 = new ListView<>();
            unSelectedMods2.setPrefSize(200,150);

            //An HBox made up of: Label, Button, Button
            HBox btnBox1 = new HBox();
            btnBox1.setAlignment(Pos.CENTER);
            btnBox1.setSpacing(20);

            Label lb1 = new Label("Term 1");
            addBtn1 = new Button("Add");
            rmBtn1 = new Button("Remove");

            //add all leaf elements to the HBox
            btnBox1.getChildren().addAll(lb1, addBtn1, rmBtn1);

            HBox btnBox2 = new HBox();

            btnBox2.setAlignment(Pos.CENTER);
            btnBox2.setSpacing(20);

            Label lb3 = new Label("Term 2");
            addBtn2 = new Button("Add");
            rmBtn2 = new Button("Remove");

            btnBox2.getChildren().addAll(lb3, addBtn2, rmBtn2);

            HBox credBox = new HBox();
            credBox.setPadding(new Insets(10));
            credBox.setSpacing(6);

            Label lbCredits = new Label("Current Term 1 credits");
            credTxtField1 = new TextField();

            credBox.getChildren().addAll(lbCredits, credTxtField1);

            resetBtn = new Button("Reset");

            this.getChildren().add(lbl1);
            this.getChildren().add(unSelectedMods1);
            this.getChildren().add(btnBox1);
            this.getChildren().add(lbl2);
            this.getChildren().add(unSelectedMods2);
            this.getChildren().add(btnBox2);
            this.getChildren().add(credBox);
            this.getChildren().add(resetBtn);
        }
    }

    private class RightPaneHalf extends VBox{
        private RightPaneHalf(){
            this.setPadding(new Insets(10));
            this.setAlignment(Pos.CENTER);

            //Select Year Long Module" label
            Label lbl1 = new Label("Selected Year Long modules");
            this.getChildren().add(lbl1);

            //Selected Modules for the whole year
            selectedYLongMods = new ListView<>();
            selectedYLongMods.setPrefSize(150,50);
            this.getChildren().add(selectedYLongMods);

            //"Select term 1 modules" label
            Label lbl2 = new Label("Selected term 1 modules");
            this.getChildren().add(lbl2);

            selectedMods1 = new ListView<>();
            selectedMods1.setPrefSize(150,150);
            this.getChildren().add(selectedMods1);

            //"Select term 1 modules" label
            Label lbl3 = new Label("Selected term 2 modules");
            this.getChildren().add(lbl3);

            selectedMods2 = new ListView<>();
            selectedMods2.setPrefSize(150,150);
            this.getChildren().add(selectedMods2);

            HBox credBox = new HBox();
            credBox.setPadding(new Insets(10));
            credBox.setSpacing(6);

            Label lbCredits = new Label("Current Term 2 credits");
            credTxtField2 = new TextField();

            credBox.getChildren().addAll(lbCredits, credTxtField2);

            this.getChildren().add(credBox);

            submitBtn = new Button("Submit");

            this.getChildren().add(submitBtn);
        }
    }

    public void InitPopulateListViews(Course course){
        AddUSelectMods1(course);
        AddUSelectMods2(course);
        AddSelectYLMods(course);
        AddSelectedMods1(course);
        AddSelectedMods2(course);
    }

    private void AddUSelectMods1(Course course){
        for (Module mod: course.getAllModulesOnCourse()) {
            if(course.getCourseName().equals("Software Engineering")
            && !mod.isMandatory()) {
                if(mod.getDelivery().equals(Schedule.TERM_1)) {
                    unSelectedMods1.getItems().add(mod);
                }
            }else if(course.getCourseName().equals("Computer Science")
            && !mod.isMandatory()){
                if(mod.getDelivery().equals(Schedule.TERM_1)){
                    unSelectedMods1.getItems().add(mod);
                }
            }
        }
    }

    private void AddUSelectMods2(Course course){
        for (Module mod: course.getAllModulesOnCourse()) {
            if(course.getCourseName().equals("Software Engineering")
            && !mod.isMandatory()) {
                if(mod.getDelivery().equals(Schedule.TERM_2)) {
                    unSelectedMods2.getItems().add(mod);
                }
            }else if(course.getCourseName().equals("Computer Science")
                    && !mod.isMandatory()) {
                if(mod.getDelivery().equals(Schedule.TERM_2)) {
                    unSelectedMods2.getItems().add(mod);
                }
            }
        }
    }

    private void AddSelectYLMods(Course course){
        for (Module mod: course.getAllModulesOnCourse()) {
            if(course.getCourseName().equals("Software Engineering")) {
                if (mod.getDelivery().equals(Schedule.YEAR_LONG)) {
                    selectedYLongMods.getItems().add(mod);
                }
            }else if(course.getCourseName().equals("Computer Science")){
                if(mod.getDelivery().equals(Schedule.YEAR_LONG)){
                    selectedYLongMods.getItems().add(mod);
                }
            }
        }
    }

    private void AddSelectedMods1(Course course){
        for (Module mod: course.getAllModulesOnCourse()) {
            if(course.getCourseName().equals("Software Engineering")){
                if(mod.isMandatory() && mod.getDelivery().equals(Schedule.TERM_1)){
                    selectedMods1.getItems().add(mod);
                }
            }
            if(course.getCourseName().equals("Computer Science")){
                if(mod.isMandatory() && mod.getDelivery().equals(Schedule.TERM_1)){
                    selectedMods1.getItems().add(mod);
                }
            }
        }
    }

    private void AddSelectedMods2(Course course){
        for (Module mod: course.getAllModulesOnCourse()) {
            if(course.getCourseName().equals("Software Engineering")){
                if(mod.isMandatory() && mod.getDelivery().equals(Schedule.TERM_2)){
                    selectedMods2.getItems().add(mod);
                }
            }
            if(course.getCourseName().equals("Computer Science")){
                if(mod.isMandatory() && mod.getDelivery().equals(Schedule.TERM_2)){
                    selectedMods2.getItems().add(mod);
                }
            }
        }
    }

    public ListView<Module> getUnSelectedMods1(){
        return unSelectedMods1;
    }
    public ListView<Module> getUnSelectedMods2(){
        return unSelectedMods2;
    }
    public ListView<Module> getSelectedYLongMods(){
        return selectedYLongMods;
    }
    public ListView<Module> getSelectedMods1(){
        return selectedMods1;
    }
    public ListView<Module> getSelectedMods2(){
        return selectedMods2;
    }

    public void setCreated(boolean created){
        this.created = created;
    }

    public boolean getCreated(){
        return created;
    }

    //Methods for external use of the handlers in the controller
    public void addAddBtnHandler1(EventHandler<ActionEvent> handler){
        addBtn1.setOnAction(handler);
    }
    public void addRmBtnHandler1(EventHandler<ActionEvent> handler){
        rmBtn1.setOnAction(handler);
    }
    public void addSubmitBtnHandler(EventHandler<ActionEvent> handler){
        submitBtn.setOnAction(handler);
    }
    public void addAddBtnHandler2(EventHandler<ActionEvent> handler){
        addBtn2.setOnAction(handler);
    }
    public void addRmBtnHandler2(EventHandler<ActionEvent> handler){
        rmBtn2.setOnAction(handler);
    }
    public void addResetBtnHandler(EventHandler<ActionEvent> handler){
        resetBtn.setOnAction(handler);
    }

}
