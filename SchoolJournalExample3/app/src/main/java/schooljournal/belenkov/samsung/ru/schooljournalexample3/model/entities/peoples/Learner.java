package schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples;

public class Learner extends Participant {
    protected Parent[] parentsList;

    public Parent[] getParentsList() {
        return parentsList;
    }

    public void setParentsList(Parent[] parentsList) {
        this.parentsList = parentsList;
    }
}
