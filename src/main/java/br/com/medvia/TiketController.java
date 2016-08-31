package br.com.medvia;

import br.com.medvia.db.DBManager;
import br.com.medvia.resources.Equipment;
import br.com.medvia.resources.Ticket;
import br.com.medvia.resources.User;
import br.com.medvia.util.Fakes;
import br.com.medvia.util.ReplyMessage;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Willian
 */
@RestController
@CrossOrigin
public class TiketController extends AbstractController {

    private static final String QUERY_LIST = "select t.state, t.title, t.description, e.description as equipment, t.dateOcurrence, t.prediction, t.situation, t.priority from Ticket t, Equipment e where t.equipmentID = e.ID";

    private static final String METHOD_LIST = "/api/tickets";
    private static final String METHOD_CREATE = "/api/tickets";
    private static final String METHOD_EDIT = "/api/tickets/{id}";
    private static final String METHOD_GET = "/api/tickets/{id}";
    private static final String METHOD_DELETE = "/api/tickets/{id}";
    private static final String METHOD_DROP = "/api/tickets/drop";
    private static final String METHOD_CREATEFAKES = "/api/tickets/createfakes";

    public TiketController() {
        System.out.println(TiketController.class.getSimpleName() + " OK!");
    }

    @RequestMapping(path = METHOD_LIST, method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> list() {
        List<Map<String, Object>> selectAll = DBManager.getInstance().getDbTicket().executeQuery(QUERY_LIST);
        return new ResponseEntity<>(selectAll, HttpStatus.OK);
    }

    @RequestMapping(path = METHOD_CREATE, method = RequestMethod.POST)
    public ResponseEntity<ReplyMessage> create(@RequestBody Ticket ticket) {
        System.out.println(ticket.toString());
        ticket.setState("a");

        // valida campos obrigatórios
        if (ticket.getDescription() == null || ticket.getDescription().isEmpty()) {
            return returnOK("Campo obrigatório não informado: Descrição");
        }

        boolean insert = DBManager.getInstance().getDbTicket().insert(ticket);
        return returnOK(insert ? "Criou novo chamado com sucesso!" : "Não foi possível criar um novo chamdo!");
    }

    @RequestMapping(path = METHOD_GET, method = RequestMethod.GET)
    public ResponseEntity<Ticket> get(@PathVariable(value = "id") Integer id) {
        System.out.println("ID = " + id);
        return new ResponseEntity<>(DBManager.getInstance().getDbTicket().selectByID(id), HttpStatus.OK);
    }

    @RequestMapping(path = METHOD_EDIT, method = RequestMethod.PUT)
    public ResponseEntity<ReplyMessage> edit(@PathVariable(value = "id") Integer id, @RequestBody Ticket ticket) {
        System.out.println("ID = " + id);
        if (id == null) {
            return returnOK("ID inválido!");
        }
        ticket.setID(id);
        boolean update = DBManager.getInstance().getDbTicket().update(ticket);
        return returnOK(update ? "Update OK!" : "Update FAIL!");
    }

    @RequestMapping(path = METHOD_DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<ReplyMessage> delete(@PathVariable(value = "id") Integer id) {
        System.out.println("ID = " + id);
        if (id == null) {
            return returnOK("ID inválido!");
        }
        Ticket ticket = DBManager.getInstance().getDbTicket().selectByID(id);
        ticket.setState("e");
        boolean update = DBManager.getInstance().getDbTicket().update(ticket);
        return returnOK(update ? "Delete OK!" : "Delete FAIL!");
    }

    @RequestMapping(METHOD_DROP)
    public ResponseEntity<ReplyMessage> drop() {
        DBManager.getInstance().getDbTicket().dropAndCreateTable();
        return returnOK("Todos tickets foram deletados com sucesso!");
    }

    @RequestMapping(METHOD_CREATEFAKES)
    public ResponseEntity<ReplyMessage> createfakes() {
        List<User> users = DBManager.getInstance().getDbUser().selectAll(null);
        // se ainda não existir nenhum 
        if (users.isEmpty()) {
            return returnOK("Nenhum usuário ainda foi criado!");
        }
        List<Equipment> equipments = DBManager.getInstance().getDbEquipment().selectAll(null);
        // se ainda não existir nenhum 
        if (equipments.isEmpty()) {
            return returnOK("Nenhum equipamento ainda foi criado!");
        }
        List<Ticket> created = Fakes.createTickets(users, equipments);
        created.stream().forEach((element) -> {
            create(element);
        });
        return returnOK(created.size() + " fakes foram criados com sucesso!");
    }

}
