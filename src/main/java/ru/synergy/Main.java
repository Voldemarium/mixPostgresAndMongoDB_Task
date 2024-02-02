package ru.synergy;

import ru.synergy.controllers.MainController;
import ru.synergy.dao.ContactDao;
import ru.synergy.dao.UserDao;
import ru.synergy.models.Contact;
import ru.synergy.models.User;
import ru.synergy.services.ContactService;
import ru.synergy.services.UserService;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        User user_1 = new User("Petya");
        User user_2 = new User("Vasya");
        User user_3 = new User("Katya");

        Contact contact_1 = new Contact(user_1, "first", "1234567", "email@lll.ru");
        Contact contact_2 = new Contact(user_1, "second", "456756756", "sefefrfrt@lll.ru");
        Contact contact_3 = new Contact(user_3, "main", "1234565", "herhhwwh@lll.ru");

        user_1.setContacts(Set.of(contact_1, contact_2));
        user_3.setContacts(Set.of(contact_3));

        UserService userService = new UserService(new UserDao());
        ContactService contactService = new ContactService(new ContactDao());
        userService.saveUser(user_1);
        userService.saveUser(user_2);
        userService.saveUser(user_3);

//        List<User> users = userService.findAllUsers();
//        System.out.println(users);
//
//        List<Contact> contacts = contactService.findAllContacts();
//        System.out.println(contacts);

//        User user = userService.findUser(1);
//        user.setName("Vladimir");
//        userService.updateUser(user);
//
//        User userD = userService.findUser(4);
//        userService.deleteUser(userD);

        MainController controller = new MainController(contactService, userService);
        //  Добавление мета информации при добавлении нового контакта к пользователю
        Contact newContact = new Contact("main", "12343434", "hewh@lll.ru");
        newContact.setUser(userService.findUser(2));
        controller.addContactWithMeta(newContact);

        //Вывод в консоль контактов пользователя с метаинформацией
        controller.printContactsWithMetaByUserId(2);

        controller.close();
    }
}