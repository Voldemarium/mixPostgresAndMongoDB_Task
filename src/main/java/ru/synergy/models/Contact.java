package ru.synergy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //, referencedColumnName = "id"
    private User user;

    @Column(name = "\"contactName\"", nullable = false)
    private String contactName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    public Contact() {
    }

    public Contact(User user, String contactName, String phone, String email) {
        this.user = user;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
    }

    public Contact(String contactName, String phone, String email) {
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id= " + id +
                ", user_id=" + user.getId() +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
