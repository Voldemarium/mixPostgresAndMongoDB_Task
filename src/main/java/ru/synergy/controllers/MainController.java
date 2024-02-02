package ru.synergy.controllers;

import ru.synergy.models.Contact;
import ru.synergy.models.User;
import ru.synergy.services.ContactService;
import ru.synergy.services.LogService;
import ru.synergy.services.UserService;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainController implements Closeable {
    final LogService log;
    final ContactService contactService;

    final UserService userService;

    public MainController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
        log = new LogService();
    }

    @Override
    public void close() {
        log.close();
    }

    public void addContactWithMeta(Contact contact) {
        contactService.saveContact(contact);
        Map<String, Object> meta = new HashMap<>();
        meta.put("action", "create contact");
        meta.put("creation time", LocalDateTime.now());
        meta.put("userId", contact.getUser().getId());
        meta.put("newContactId", contact.getId());
        log.add(meta);
    }

    public void printContactsWithMetaByUserId(int user_id) {
        User user = userService.findUser(user_id);
        Set<Contact> contacts = user.getContacts();
        for (Contact contact : contacts) {
            System.out.println(contact+ "\nmeta:\n" + log.printMetaByContactId(contact.getId()));
        }
    }
}
