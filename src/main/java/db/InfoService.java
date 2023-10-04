package db;

import java.sql.ResultSet;

public class InfoService {

    private DBConnector dbConnector = new DBConnector();
    public void printAllStudentsInfo()  {

        try {
            dbConnector.open();
            ResultSet rs = dbConnector.executeQuery("select s.fio as studentFio , g.\"name\" as name , c.fio as cFio  from students s " +
                    "join groups g on g.id = s.id_group "+
                    "join curators c on c.id = g.id_curator");
            System.out.print("----Информация о всех студентах----\n");
            while(rs.next()){
                System.out.print("ФИО студента: " + rs.getString("studentFio") + ",");
                System.out.print(" Наименование группы: " + rs.getString("name") + ",");
                System.out.print(" ФИО куратора: " + rs.getString("cFio") + ";" + "\n");
            }
            System.out.print("-------------------------\n");
        } catch (Exception ignored) {
            //
        } finally {
            dbConnector.close();
        }

    }

    public void printFemaleStudents()  {

        try {
            dbConnector.open();
            ResultSet rs = dbConnector.executeQuery("select s.fio as studentFio , g.\"name\" as name , c.fio as cFio  from groups s " +
                    "join groups g on g.id = s.id_group "+
                    "join curators c on c.id = g.id_curator where s.sex = 'FEMALE'");
            System.out.print("----Информация о всех студентах----\n");
            while(rs.next()){
                System.out.print("ФИО студента: " + rs.getString("studentFio") + ",");
                System.out.print(" Наименование группы: " + rs.getString("name") + ",");
                System.out.print(" ФИО куратора: " + rs.getString("cFio") + ";" + "\n");
            }
            System.out.print("-------------------------\n");
        } catch (Exception ignored) {
            //
        } finally {
            dbConnector.close();
        }

    }

    public void printGroupsAndCurators() {
        try {
            dbConnector.open();
            ResultSet rs = dbConnector.executeQuery("select g.name as name, c.fio as cFio  from groups g " +
                    "join curators c on c.id = g.id_curator ");

            System.out.print("----Информация по всем группам----\n");
            while(rs.next()){
                System.out.print(" Наименование группы: " + rs.getString("name") + ",");
                System.out.print(" ФИО куратора: " + rs.getString("cFio") + ";" + "\n");
            }
            System.out.print("-------------------------\n");
        } catch (Exception ignored) {
            //
        } finally {
            dbConnector.close();
        }
    }

    public void changeGroupCurator() {
        System.out.print("----Обновляем у юристов куратора----\n");
        dbConnector.executeUpdate("update groups set id_curator = (select id from curators where fio = 'Миронов Андрей Витольдович') where name = 'Юристы'");
        System.out.print("----у юристов теперь куратор Миронов Андрей Витольдович---------------------\n");

    }

    public void printStudentByName()  {

        try {
            dbConnector.open();
            ResultSet rs = dbConnector.executeQuery("select s.fio as studentFio , g.\"name\" as name , c.fio as cFio  from students s " +
                    "join groups g on g.id = s.id_group "+
                    "join curators c on c.id = g.id_curator where s.fio like '%Мария%'");
            System.out.print("----Информация о поиске студентов по имени Мария----\n");
            while(rs.next()){
                System.out.print("ФИО студента: " + rs.getString("studentFio") + ",");
                System.out.print(" Наименование группы: " + rs.getString("name") + ",");
                System.out.print(" ФИО куратора: " + rs.getString("cFio") + ";" + "\n");
            }
            System.out.print("-------------------------\n");
        } catch (Exception ignored) {
            //
        } finally {
            dbConnector.close();
        }

    }
}
