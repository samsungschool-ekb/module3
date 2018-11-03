package schooljournal.belenkov.samsung.ru.schooljournalexample3.model.data_providers;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Teacher;

public class ShoolDataProvider {

    public static Teacher[] getTeachers() {
        return new Teacher[]{
                new Teacher("Ivan Ivanov", "123-123", "Russian Language"),
                new Teacher("Anna Petrova", "123-123", "Mathematical"),
                new Teacher("Petr Sidorov", "123-123", "Geography"),
                new Teacher("Alexander Petrov", "123-123", "Computer Science"),
                new Teacher("John Snow", "123-123", "PE"),
                new Teacher("Ivan Ivanov", "123-123", "Russian Language"),
                new Teacher("Anna Petrova", "123-123", "Mathematical"),
                new Teacher("Petr Sidorov", "123-123", "Geography"),
                new Teacher("Alexander Petrov", "123-123", "Computer Science"),
                new Teacher("John Snow", "123-123", "PE"),
        };
    }
}
