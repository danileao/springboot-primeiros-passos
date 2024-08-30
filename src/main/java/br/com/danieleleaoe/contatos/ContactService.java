package br.com.danieleleaoe.contatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ContactService {

    List<ContactsModel> contacts = new ArrayList<>();

    public ContactsModel create(ContactsModel contactsModel) {
        contactsModel.setId(UUID.randomUUID());
        contacts.add(contactsModel);
        return contactsModel;
    }

    public List<ContactsModel> listAll() {
        return contacts;
    }

    public Optional<ContactsModel> findByEmail(String email) {
        var contact = this.contacts.stream().filter(c -> c.getEmail().equals(email)).findFirst();
        return contact;
    }

}
