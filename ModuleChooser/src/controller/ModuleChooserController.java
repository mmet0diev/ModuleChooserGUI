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

    private Module focusedMod;
    private int totalCredits;
    private int reserveCredits;

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

        //attach event handler to resetBtn
        view.getSelectModulesPane().addResetBtnHandler(new ResetHandler());

        //attach event handler to submitBtn
        view.getSelectModulesPane().addSubmitBtnHandler(new SubmitHandler());

        //attach event handler to addBtn1 in reserved mods pane
        view.getReserveModsPane().addAddBtn1Handler(new AddReserveModsHandler1());

        //attach event hadnler to addBtn2 in reserved mods pane
        view.getReserveModsPane().addAddBtn2Handler(new AddReserveModsHandler2());

        //attach event handler to rmBtn1 in reserved mods pane
        view.getReserveModsPane().addRmBtn1Handler(new RmReserveModsHandler1());

        //attach event handler to rmBtn2 in reserved mods pane
        view.getReserveModsPane().addRmBtn2Handler(new RmReserveModsHandler2());

        //An information alert whenever one clicks the "about" menuitem in the about menu.
        mcmb.addAboutHandler(new CreateAboutAlertHandler());

        //attach an event handler to the menu bar that closes the application
        mcmb.addExitHandler(e -> System.exit(0));
    }

    //event handler used for creating a profile
    private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
//            boolean pnumFieldIsEmpty = view.getCreateStudentProfilePane().getStudentPnumber().isEmpty();
//            boolean firstNameFieldIsEmpty = view.getCreateStudentProfilePane().getStudentName().getFirstName().isEmpty();
//            boolean familyNameFieldIsEmpty = view.getCreateStudentProfilePane().getStudentName().getFamilyName().isEmpty();
//            boolean emailFieldIsEmpty = view.getCreateStudentProfilePane().getStudentEmail().isEmpty();

            model.setStudentCourse(view.getCreateStudentProfilePane().getSelectedCourse());
            model.setStudentPnumber(view.getCreateStudentProfilePane().getStudentPnumber());
            model.setStudentName(view.getCreateStudentProfilePane().getStudentName());
            model.setStudentEmail(view.getCreateStudentProfilePane().getStudentEmail());
            model.setSubmissionDate(view.getCreateStudentProfilePane().getStudentDate());

            if (!view.getSelectModulesPane().getCreated()) {
                view.getSelectModulesPane().initSelectModulesPane(model.getStudentCourse());
                view.getSelectModulesPane().setCreated(true);
            }
            addCompulsoryToSelectedMods();
            System.out.println("Created.");
        }

        private void addCompulsoryToSelectedMods() {
            Course selectedCourse = model.getStudentCourse();
            if (selectedCourse.getCourseName().equals("Software Engineering")) {
                model.addSelectedModule(view.getSelectModulesPane().getSelectedYLongMods().getItems().get(0));
                model.addSelectedModule(view.getSelectModulesPane().getSelectedMods1().getItems().get(0));
                model.addSelectedModule(view.getSelectModulesPane().getSelectedMods2().getItems().get(0));
            }

            if (selectedCourse.getCourseName().equals("Computer Science")) {
                model.addSelectedModule(view.getSelectModulesPane().getSelectedYLongMods().getItems().get(0));
                model.addSelectedModule(view.getSelectModulesPane().getSelectedMods1().getItems().get(0));
            }
        }
    }

    //Select Modules Tab Handlers
    //Add Handlers..
    private class AddModsHandler1 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            focusedMod =
                    view.getSelectModulesPane().getUnselectedMods1().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getUnselectedMods1().getItems().contains(focusedMod)) {
                if (!view.getSelectModulesPane().getSelectedMods1().getItems().contains(focusedMod)) {
                    if (view.getSelectModulesPane().getCredits1() <= 45) {
                        view.getSelectModulesPane().getSelectedMods1().getItems().add(focusedMod);
                        view.getSelectModulesPane().getUnselectedMods1().getItems().remove(focusedMod);
                        view.getSelectModulesPane().incrementCredits1();
                        view.getSelectModulesPane().updateCredTxt1();
                        model.getAllSelectedModules().add(focusedMod);
                    }
                }
            }
        }
    }

    private class AddModsHandler2 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            focusedMod =
                    view.getSelectModulesPane().getUnselectedMods2().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getUnselectedMods2().getItems().contains(focusedMod)) {
                if (!view.getSelectModulesPane().getSelectedMods2().getItems().contains(focusedMod)) {
                    if (view.getSelectModulesPane().getCredits2() <= 45) {
                        view.getSelectModulesPane().getSelectedMods2().getItems().add(focusedMod);
                        view.getSelectModulesPane().getUnselectedMods2().getItems().remove(focusedMod);
                        view.getSelectModulesPane().incrementCredits2();
                        view.getSelectModulesPane().updateCredTxt2();
                        model.getAllSelectedModules().add(focusedMod);
                    }
                }
            }
        }
    }

    //Remove Handlers..
    private class RemoveModsHandler1 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            focusedMod =
                    view.getSelectModulesPane().getSelectedMods1().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getSelectedMods1().getItems().contains(focusedMod)) {
                if (!focusedMod.isMandatory()) {
                    if (view.getSelectModulesPane().getCredits1() >= 0) {
                        view.getSelectModulesPane().getSelectedMods1().getItems().remove(focusedMod);
                        view.getSelectModulesPane().getUnselectedMods1().getItems().add(focusedMod);
                        view.getSelectModulesPane().decrementCredits1();
                        view.getSelectModulesPane().updateCredTxt1();
                    }
                }
            }
        }
    }

    private class RemoveModsHandler2 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            focusedMod =
                    view.getSelectModulesPane().getSelectedMods2().getSelectionModel().getSelectedItem();

            if (view.getSelectModulesPane().getSelectedMods2().getItems().contains(focusedMod)) {
                if (!focusedMod.isMandatory()) {
                    if (view.getSelectModulesPane().getCredits2() >= 0) {
                        view.getSelectModulesPane().getSelectedMods2().getItems().remove(focusedMod);
                        view.getSelectModulesPane().getUnselectedMods2().getItems().add(focusedMod);
                        view.getSelectModulesPane().decrementCredits2();
                        view.getSelectModulesPane().updateCredTxt2();
                    }
                }
            }
        }
    }

    //Reset Handler..
    private class ResetHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            view.getSelectModulesPane().clearCredits();
            totalCredits = 0;
            model.clearReservedModules();
            model.clearSelectedModules();
            clearListViews();
            view.getSelectModulesPane().initSelectModulesPane(model.getStudentCourse());
        }

        private void clearListViews() {
            view.getSelectModulesPane().getUnselectedMods1().getItems().clear();
            view.getSelectModulesPane().getSelectedMods1().getItems().clear();
            view.getSelectModulesPane().getUnselectedMods2().getItems().clear();
            view.getSelectModulesPane().getSelectedMods2().getItems().clear();
            view.getSelectModulesPane().getSelectedYLongMods().getItems().clear();
        }
    }

    //Submit Handler..
    private class SubmitHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            totalCredits = view.getSelectModulesPane().getCredits1()
                    + view.getSelectModulesPane().getCredits2();

            if (totalCredits == 120) {
                submitReservedMods1();
                submitReservedMods2();
            } else {
                System.out.println("Not enough modules worth of credits selected.\n" +
                        "Please select 120 credits worth of modules.");
            }
        }

        private void submitReservedMods1() {
            for (Module mod : view.getSelectModulesPane().getUnselectedMods1().getItems()) {
                model.getAllReservedModules().add(mod);
                view.getReserveModsPane().getUnselectedModsList1().getItems().add(mod);
            }
        }

        private void submitReservedMods2() {
            for (Module mod : view.getSelectModulesPane().getUnselectedMods2().getItems()) {
                model.getAllReservedModules().add(mod);
                view.getReserveModsPane().getUnselectedModsList2().getItems().add(mod);
            }
        }
    }

    //Reserve Modules Tab Handlers
    //Add Handlers
    private class AddReserveModsHandler1 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            focusedMod =
                    view.getReserveModsPane().getUnselectedModsList1().getSelectionModel().getSelectedItem();

            if (view.getReserveModsPane().getUnselectedModsList1().getItems().contains(focusedMod)) {
                if (!view.getReserveModsPane().getReservedModsList1().getItems().contains(focusedMod)) {
                    model.getAllReservedModules().add(focusedMod);
                    view.getReserveModsPane().getUnselectedModsList1().getItems().remove(focusedMod);
                    view.getReserveModsPane().getReservedModsList1().getItems().add(focusedMod);
                }
            }
        }
    }

    private class AddReserveModsHandler2 implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            focusedMod =
                    view.getReserveModsPane().getUnselectedModsList2().getSelectionModel().getSelectedItem();

            if (view.getReserveModsPane().getUnselectedModsList2().getItems().contains(focusedMod)) {
                if (!view.getReserveModsPane().getReservedModsList2().getItems().contains(focusedMod)) {
                    model.getAllReservedModules().add(focusedMod);
                    view.getReserveModsPane().getUnselectedModsList2().getItems().remove(focusedMod);
                    view.getReserveModsPane().getReservedModsList2().getItems().add(focusedMod);
                }
            }
        }
    }

    private class RmReserveModsHandler1 implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            focusedMod =
                    view.getReserveModsPane().getReservedModsList1().getSelectionModel().getSelectedItem();

            if(view.getReserveModsPane().getReservedModsList1().getItems().contains(focusedMod)){
                if(!view.getReserveModsPane().getUnselectedModsList1().getItems().contains(focusedMod)){
                    model.getAllReservedModules().remove(focusedMod);
                    view.getReserveModsPane().getReservedModsList1().getItems().remove(focusedMod);
                    view.getReserveModsPane().getUnselectedModsList1().getItems().add(focusedMod);
                }
            }
        }
    }

    private class RmReserveModsHandler2 implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            focusedMod =
                    view.getReserveModsPane().getReservedModsList2().getSelectionModel().getSelectedItem();

            if(view.getReserveModsPane().getReservedModsList2().getItems().contains(focusedMod)){
                if(!view.getReserveModsPane().getUnselectedModsList2().getItems().contains(focusedMod)){
                    model.getAllReservedModules().remove(focusedMod);
                    view.getReserveModsPane().getReservedModsList2().getItems().remove(focusedMod);
                    view.getReserveModsPane().getUnselectedModsList2().getItems().add(focusedMod);
                }
            }
        }
    }

    private class CreateAboutAlertHandler implements EventHandler<ActionEvent> {
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
