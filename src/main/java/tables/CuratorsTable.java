package tables;

import java.util.UUID;

public class CuratorsTable extends AbsTable {

    private static final String NAME = "curators";

    public CuratorsTable() {
        super(NAME);
    }

    @Override
    public void create() {
        dbConnector.executeUpdate(String.format ("CREATE TABLE public.%s (\n" +
                "\tid UUID primary key,\n" +
                "\t\"fio\" varchar(255) NULL\n" +
                ")", this.tableName));
    }

    public UUID createRowAndGetId(String fio) {
        UUID id = UUID.randomUUID();
        dbConnector.executeUpdate(String.format ("INSERT INTO public.%s " +
                "\t(id, fio)\n" +
                "\tVALUES('%s', '%s')\n",this.tableName, id, fio));

        return id;
    }
}
