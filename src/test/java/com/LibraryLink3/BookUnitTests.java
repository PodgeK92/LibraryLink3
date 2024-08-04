package com.example.librarylink3.LibraryLink3;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        String isbn = "9780553902549";
        Book newBook = new Book(isbn, "Journey to the Centre of the Earth", "Jules Verne", 2001, "Fiction", "paperback");
        //entityManager.persist(alex);
        //entityManager.flush();

        // when
        //Employee found = employeeRepository.findByName(alex.getName());

        // then
        //assertThat(found.getName()).isEqualTo(alex.getName());
    }

}
