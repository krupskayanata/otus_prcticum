package tables;


import java.util.UUID;

public class GroupsTable extends AbsTable {

    private static final String NAME = "groups";

    public GroupsTable() {
        super(NAME);
    }


    @Override
    public void create() {
        dbConnector.executeUpdate(String.format ("CREATE TABLE public.%s (\n" +
                "\tid UUID primary key,\n" +
                "\t\"name\" varchar(255) NULL\n," +
                "id_curator UUID" +
                ")", this.tableName));

    }

    public UUID createRowAndGetId(String name, UUID curatorId) {
        UUID id = UUID.randomUUID();
        dbConnector.executeUpdate(String.format ("INSERT INTO public.%s " +
                "\t(id, name, id_curator)\n" +
                "\tVALUES('%s', '%s', '%s')\n", this.tableName, id, name, curatorId));

        return id;
    }
}
