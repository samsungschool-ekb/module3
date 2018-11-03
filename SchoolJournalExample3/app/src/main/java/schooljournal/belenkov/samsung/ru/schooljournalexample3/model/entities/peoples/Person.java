package schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples;

import java.io.Serializable;

public class Person implements Serializable {
    protected String name;
    protected String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
