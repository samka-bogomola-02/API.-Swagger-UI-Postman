package ka.irissin.apipostmanspringdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Reader {
    @Id
    @GeneratedValue
    private long id;
    private int personalNumber;
    private String name;
    private String lastName;
    private String middleName;
    @OneToMany(mappedBy = "reader")
    private Collection<Book> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id == reader.id && personalNumber == reader.personalNumber && Objects.equals(name, reader.name) && Objects.equals(lastName, reader.lastName) && Objects.equals(middleName, reader.middleName) && Objects.equals(books, reader.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalNumber, name, lastName, middleName, books);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", personalNumber=" + personalNumber +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", books=" + books +
                '}';
    }
}
