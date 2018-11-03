package schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.data_providers.ShoolDataProvider;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.lessons.Elective;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.lessons.Section;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Employee;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Learner;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Parent;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Teacher;

public class School {

    protected Teacher[] teachersList;

    protected Learner[] learnersList;

    protected Parent[] parentsList;

    protected Employee[] employeesList;

    protected Class[] classesList;

    protected Elective[] electivesList;

    protected Section[] sectionsList;

    protected String name;

    protected String address;

    public School() {
        teachersList = ShoolDataProvider.getTeachers();
    }

    public School(Teacher[] teachersList, Learner[] learnersList, Parent[] parentsList, Employee[] employeesList, Class[] classesList, Elective[] electivesList, Section[] sectionsList, String name, String address) {
        this.teachersList = teachersList;
        this.learnersList = learnersList;
        this.parentsList = parentsList;
        this.employeesList = employeesList;
        this.classesList = classesList;
        this.electivesList = electivesList;
        this.sectionsList = sectionsList;
        this.name = name;
        this.address = address;
    }

    public Teacher[] getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(Teacher[] teachersList) {
        this.teachersList = teachersList;
    }

    public Learner[] getLearnersList() {
        return learnersList;
    }

    public void setLearnersList(Learner[] learnersList) {
        this.learnersList = learnersList;
    }

    public Parent[] getParentsList() {
        return parentsList;
    }

    public void setParentsList(Parent[] parentsList) {
        this.parentsList = parentsList;
    }

    public Employee[] getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(Employee[] employeesList) {
        this.employeesList = employeesList;
    }

    public Class[] getClassesList() {
        return classesList;
    }

    public void setClassesList(Class[] classesList) {
        this.classesList = classesList;
    }

    public Elective[] getElectivesList() {
        return electivesList;
    }

    public void setElectivesList(Elective[] electivesList) {
        this.electivesList = electivesList;
    }

    public Section[] getSectionsList() {
        return sectionsList;
    }

    public void setSectionsList(Section[] sectionsList) {
        this.sectionsList = sectionsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
