package br.com.medvia.db;

import br.com.medvia.resources.Cost;
import br.com.medvia.resources.Equipment;
import br.com.medvia.resources.Institution;
import br.com.medvia.resources.Note;
import br.com.medvia.resources.Ticket;
import br.com.medvia.resources.User;
import java.io.File;

/**
 *
 * @author Willian
 */
public class DBManager {

//    private final String propJavaTmp = System.getProperty("java.io.tmpdir");
    private final String propUserHome = System.getProperty("user.home");

    private static final DBManager INSTANCE = new DBManager();

    public static DBManager getInstance() {
        return INSTANCE;
    }

    private final File fileDB;
    private final WkDB<Ticket> dbTicket;
    private final WkDB<Equipment> dbEquipment;
    private final WkDB<User> dbUser;
    private final WkDB<Note> dbNote;
    private final WkDB<Cost> dbCost;
    private final WkDB<Institution> dbInstitution;

    private DBManager() {
        fileDB = new File(propUserHome, "medvia.db");
        System.out.println("fileDB=" + fileDB.getAbsolutePath());

        WkDB.setFileDB(fileDB);

        dbTicket = new WkDB<>(Ticket.class);
        dbEquipment = new WkDB<>(Equipment.class);
        dbUser = new WkDB<>(User.class);
        dbNote = new WkDB<>(Note.class);
        dbCost = new WkDB<>(Cost.class);
        dbInstitution = new WkDB<>(Institution.class);

        // se o arquivo ainda não existir
        if (!fileDB.exists() || fileDB.length() < 1) {
            dbTicket.createTable();
            dbEquipment.createTable();
            dbUser.createTable();
            dbNote.createTable();
            dbCost.createTable();
            dbInstitution.createTable();
        }
    }

    public File getFileDB() {
        return fileDB;
    }

    public WkDB<Ticket> getDbTicket() {
        return dbTicket;
    }

    public WkDB<Equipment> getDbEquipment() {
        return dbEquipment;
    }

    public WkDB<User> getDbUser() {
        return dbUser;
    }

    public WkDB<Note> getDbNote() {
        return dbNote;
    }

    public WkDB<Cost> getDbCost() {
        return dbCost;
    }

    public WkDB<Institution> getDbInstitution() {
        return dbInstitution;
    }

    public synchronized void dropAndCreateTable() {
        dbTicket.dropAndCreateTable();
        dbEquipment.dropAndCreateTable();
        dbUser.dropAndCreateTable();
        dbNote.dropAndCreateTable();
        dbCost.dropAndCreateTable();
        dbInstitution.dropAndCreateTable();

        System.out.println("dropAndCreateTable!");
    }

}
