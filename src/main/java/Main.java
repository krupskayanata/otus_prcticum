import db.InfoService;
import enums.Sex;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        StudentsTable studentsTable = new StudentsTable();
        GroupsTable groupsTable = new GroupsTable();
        CuratorsTable curatorsTable = new CuratorsTable();
        InfoService infoService = new InfoService();
        try {

            curatorsTable.create();
            studentsTable.create();
            groupsTable.create();

            UUID curatorId1 = curatorsTable.createRowAndGetId("Иванов Иван Иванович");
            UUID curatorId2 = curatorsTable.createRowAndGetId("Быков Александр Борисович");
            UUID curatorId3 = curatorsTable.createRowAndGetId("Лобов Кирилл Алексеевич");
            UUID curatorId4 = curatorsTable.createRowAndGetId("Миронов Андрей Витольдович");

            UUID groupId1 = groupsTable.createRowAndGetId("Экономисты", curatorId1);
            UUID groupId2 = groupsTable.createRowAndGetId("Юристы", curatorId2);
            UUID groupId3 = groupsTable.createRowAndGetId("Программисты", curatorId3);

            studentsTable.createRowAndGetId("Жмакина Мария Федоровна", Sex.FEMALE.name(), groupId1);
            studentsTable.createRowAndGetId("Двоишнецова Просковья Николаевна", Sex.FEMALE.name(), groupId2);
            studentsTable.createRowAndGetId("Косов Антов Евгеньевич", Sex.MALE.name(), groupId3);

            studentsTable.createRowAndGetId("Двоишнецова Просковья Федоровна", Sex.FEMALE.name(), groupId1);
            studentsTable.createRowAndGetId("Двоишнецова Мария Николаевна", Sex.FEMALE.name(), groupId2);
            studentsTable.createRowAndGetId("Крокотушкиин Антов Васильевич", Sex.MALE.name(), groupId3);

            studentsTable.createRowAndGetId("Петров Олег Вячеславович", Sex.MALE.name(), groupId2);
            studentsTable.createRowAndGetId("Отличнова Ксения Исаевна", Sex.FEMALE.name(), groupId2);
            studentsTable.createRowAndGetId("Хлебников Пирожок Пряникович", Sex.MALE.name(), groupId3);

            studentsTable.createRowAndGetId("Фигуристая Наталия Васильевна", Sex.FEMALE.name(), groupId1);
            studentsTable.createRowAndGetId("Прогульщикова Анжела Петровна", Sex.FEMALE.name(), groupId2);
            studentsTable.createRowAndGetId("Высоткин Домик Девятиэтажевич", Sex.MALE.name(), groupId3);

            studentsTable.createRowAndGetId("Щеглова Екатерина Дмитриевна", Sex.FEMALE.name(), groupId3);
            studentsTable.createRowAndGetId("Посещалкина Ольга Ивановна", Sex.FEMALE.name(), groupId1);
            studentsTable.createRowAndGetId("Картошкин Василий Евгеньевич", Sex.MALE.name(), groupId3);

            infoService.printAllStudentsInfo();
            infoService.printFemaleStudents();
            infoService.changeGroupCurator();
            infoService.printGroupsAndCurators();
            infoService.printStudentByName();



        } finally {
            studentsTable.delete();
            groupsTable.delete();
            curatorsTable.delete();
        }
    }

}