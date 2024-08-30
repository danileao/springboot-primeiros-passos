package br.com.danieleleaoe.contatos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleaoe.contatos.ContactService;
import br.com.danieleleaoe.contatos.ContactsModel;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    // Injecao de dependencia
    @Autowired
    ContactService contactService;

    @GetMapping("")
    public List<ContactsModel> contacts() {
        return contactService.listAll();
    }

    @PostMapping("")
    public ContactsModel createContact(@RequestBody ContactsModel contactsModel) {
        var result = contactService.create(contactsModel);
        return result;

    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email,
            @RequestParam(name = "size", required = false, defaultValue = " 3") Long size) {
        System.out.println("Tamanho " + size);
        var result = contactService.findByEmail(email);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(400).body("Contato não encontrado");
    }

    //http://localhost:8085/contacts/{email}
}
