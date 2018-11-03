package schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.lessons;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Learner;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Teacher;

public class BaseLesson {
    protected Teacher teacher;
    protected Learner[] learnersList;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Learner[] getLearnersList() {
        return learnersList;
    }

    public void setLearnersList(Learner[] learnersList) {
        this.learnersList = learnersList;
    }
}
