package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.*;
import model.Module;
import view.ModuleChooserRootPane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;


public class ModuleChooserController {

    //fields to be used throughout class
    private ModuleChooserRootPane view;
    private StudentProfile model;

    private CreateStudentProfilePane cspp;
    private ModuleChooserMenuBar mcmb;
    private int totalCredits;

    public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
        //initialise view and model fields
        this.view = view;
        this.model = model;

        //initialise view subcontainer fields
        cspp = view.getCreateStudentProfilePane();
        mcmb = view.getModuleSelectionToolMenuBar();

        //add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
        cspp.addCoursesToComboBox(generateAndGetCourses());


        //attach event handlers to view using private helper method
        this.attachEventHandlers();
    }

    //helper method - used to attach event handlers
    private void attachEventHandlers() {
        //attach an event handler to the create student profile pane
        cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());

        //attach event handler to addBtn1
        view.getSelectModulesPane().addAddBtnHandler1(new AddModsHandler1());

        //attach event handler to rmBtn1
        view.getSelectModulesPane().addRmBtnHandler1(new RemoveModsHandler1());

        //attach event handler to addBtn2
        view.getSelectModulesPane().addAddBtnHandler2(new AddModsHandler2());

        //attach event handler to rmBtn2
        view.getSelectModulesPane().addRmBtnHandler2(new RemoveModsHandler2());

        //attach event handler to submitBtn
        view.getSelectModulesPane().addResetBtnHandler(new ResetHandler());

        //An information alert whenever one clicks the "about" menuitem in the about menu.
        mcmb.addAboutHandler(new CreateAboutAlert());

        //attach an event handler to the menu bar that closes the application
        mcmb.addExitHandler(e -> System.exit(0));
    }

    //event handler used for creating a profile
    private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            model.setStudentCourse(view.getCreateStudentProfilePane().getSelectedCourse());
            model.setStudentPnumber(view.getCreateStudentProfilePane().getStudentPnumber());
            model.setStudentName(view.getCreateStudentProfilePane().getStudentName());
            model.setStudentEmail(view.getCreateStudentProfilePane().getStudentEmail());
            model.setSubmissionDate(view.getCreateStudentProfilePane().getStudentDate());

            if (!view.getSelectModulesPane().getCreated()) {
                view.getSelectModulesPane().InitPopulateListViews(model.getStudentCourse());
                view.getSelectModulesPane().setCreated(true);
            }
        }
    }

    //Add Handlers..
    private class AddModsHandler1 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selectedMod =
                    view.getSelectModulesPane().getUnselectedMods1().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getUnselectedMods1().getItems().contains(selectedMod)) {
                if (!view.getSelectModulesPane().getSelectedMods1().getItems().contains(selectedMod)) {
                    view.getSelectModulesPane().getSelectedMods1().getItems().add(selectedMod);
                    view.getSelectModulesPane().getUnselectedMods1().getItems().remove(selectedMod);
                }
            }
        }
    }

    private class AddModsHandler2 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selectedMod =
                    view.getSelectModulesPane().getUnSelectedMods2().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getUnSelectedMods2().getItems().contains(selectedMod)) {
                if (!view.getSelectModulesPane().getSelectedMods2().getItems().contains(selectedMod)) {
                    view.getSelectModulesPane().getSelectedMods2().getItems().add(selectedMod);
                    view.getSelectModulesPane().getUnSelectedMods2().getItems().remove(selectedMod);
                }
            }
        }
    }

    //Remove Handlers..
    private class RemoveModsHandler1 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selectedMod =
                    view.getSelectModulesPane().getSelectedMods1().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getSelectedMods1().getItems().contains(selectedMod)) {
                if(!selectedMod.isMandatory()) {
                    view.getSelectModulesPane().getSelectedMods1().getItems().remove(selectedMod);
                    view.getSelectModulesPane().getUnselectedMods1().getItems().add(selectedMod);
                }
            }
        }
    }

    private class RemoveModsHandler2 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selectedMod =
                    view.getSelectModulesPane().getSelectedMods2().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getSelectedMods2().getItems().contains(selectedMod)) {
                if (!selectedMod.isMandatory()) {
                    view.getSelectModulesPane().getSelectedMods2().getItems().remove(selectedMod);
                    view.getSelectModulesPane().getUnSelectedMods2().getItems().add(selectedMod);
                }
            }
        }
    }

    //Reset Handler..
    private class ResetHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            model.clearReservedModules();
            model.clearSelectedModules();
            clearListViews();
            view.getSelectModulesPane().InitPopulateListViews(model.getStudentCourse());
        }

        private void clearListViews() {
            view.getSelectModulesPane().getUnselectedMods1().getItems().clear();
            view.getSelectModulesPane().getSelectedMods1().getItems().clear();
            view.getSelectModulesPane().getUnSelectedMods2().getItems().clear();
            view.getSelectModulesPane().getSelectedMods2().getItems().clear();
            view.getSelectModulesPane().getSelectedYLongMods().getItems().clear();
        }
    }

    //Submit Handler..
    private class SubmitHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {

        }
    }

    private class CreateAboutAlert implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Meto's about content.\nModule Chooser GUI");
            alert.show();
        }
    }

    //helper method - generates course and module data and returns courses within an array
    private Course[] generateAndGetCourses() {
        Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
        Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
        Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);
        Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
        Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
        Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);
        Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);
        Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
        Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
        Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
        Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
        Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
        Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
        Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
        Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
        Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
        Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
        Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


        Course compSci = new Course("Computer Science");
        compSci.addModuleToCourse(imat3423);
        compSci.addModuleToCourse(ctec3451);
        compSci.addModuleToCourse(ctec3902_CompSci);
        compSci.addModuleToCourse(ctec3110);
        compSci.addModuleToCourse(ctec3605);
        compSci.addModuleToCourse(ctec3606);
        compSci.addModuleToCourse(ctec3410);
        compSci.addModuleToCourse(ctec3904);
        compSci.addModuleToCourse(ctec3905);
        compSci.addModuleToCourse(ctec3906);
        compSci.addModuleToCourse(ctec3911);
        compSci.addModuleToCourse(imat3410);
        compSci.addModuleToCourse(imat3406);
        compSci.addModuleToCourse(imat3611);
        compSci.addModuleToCourse(imat3613);
        compSci.addModuleToCourse(imat3614);
        compSci.addModuleToCourse(imat3428_CompSci);

        Course softEng = new Course("Software Engineering");
        softEng.addModuleToCourse(imat3423);
        softEng.addModuleToCourse(ctec3451);
        softEng.addModuleToCourse(ctec3902_SoftEng);
        softEng.addModuleToCourse(ctec3110);
        softEng.addModuleToCourse(ctec3605);
        softEng.addModuleToCourse(ctec3606);
        softEng.addModuleToCourse(ctec3410);
        softEng.addModuleToCourse(ctec3904);
        softEng.addModuleToCourse(ctec3905);
        softEng.addModuleToCourse(ctec3906);
        softEng.addModuleToCourse(ctec3911);
        softEng.addModuleToCourse(imat3410);
        softEng.addModuleToCourse(imat3406);
        softEng.addModuleToCourse(imat3611);
        softEng.addModuleToCourse(imat3613);
        softEng.addModuleToCourse(imat3614);

        Course[] courses = new Course[2];
        courses[0] = compSci;
        courses[1] = softEng;

        return courses;
    }
}
