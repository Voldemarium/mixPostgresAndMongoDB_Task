package ru.synergy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    //    mappedBy = "post" - имя поля в классе Comment, на которое ссылается эта связь
//    FetchType.EAGER - значит комментарии всегда подтягиваются из базы данных при вызове
//    CascadeType.ALL - значит все комментарии после удаления поста Post удаляются из базы данных
//     и другие каскадные операции (кроме ON UPDATE, как в SQL)
//@OnDelete(action = OnDeleteAction.CASCADE) тоже значит все комментарии после удаления поста Post удаляются из базы данных
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Contact> contacts = new LinkedHashSet<>();


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, Set<Contact> contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
