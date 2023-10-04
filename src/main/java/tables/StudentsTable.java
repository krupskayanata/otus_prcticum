package tables;

import java.util.UUID;

public class StudentsTable extends AbsTable {

    private static final String NAME = "students";

    public StudentsTable() {
        super(NAME);
    }

    @Override
    public void create() {
        dbConnector.executeUpdate(String.format ("CREATE TABLE public.%s (\n" +
                "\tid UUID primary key,\n" +
                "\t\"fio\" varchar(255) NULL,\n" +
                "\t\"sex\" varchar(255) NULL,\n" +
                "\t\"id_group\" UUID NOT NULL\n" +
                ")", this.tableName));
    }

    public UUID createRowAndGetId(String fio, String sex, UUID groupId) {
        UUID id = UUID.randomUUID();
        dbConnector.executeUpdate(String.format ("INSERT INTO public.%s " +
                "\t(id, fio, sex, id_group)\n" +
                "\tVALUES('%s', '%s', '%s', '%s')\n", this.tableName, id, fio, sex, groupId));

        return id;
    }


}
