package ru.synergy.services;

import ru.synergy.dao.ContactDao;
import ru.synergy.models.Contact;

import java.util.List;

public class ContactService {
    private final ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public Contact findContact(int id) {return contactDao.findById(id);}

    public void saveContact(Contact contact) {
        contactDao.save(contact);}

    public void updateContact(Contact contact) {
        contactDao.update(contact);}

    public void deleteContact(Contact contact) {
        contactDao.delete(contact);}

    public List<Contact> findAllContacts() {return contactDao.findAll();}
}


