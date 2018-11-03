package schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples;

import java.io.Serializable;

public class Teacher extends Participant{
    protected String qualification;

    public Teacher(String name, String phone, String qualification) {
        this.qualification = qualification;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
