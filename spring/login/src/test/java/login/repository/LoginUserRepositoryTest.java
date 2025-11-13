package login.repository;

import login.entity.Authority;
import login.entity.LoginUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LoginUserRepositoryTest {
    @Autowired
    private LoginUserRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    @Test
    void findByEmailAndPasswordTest() {
        LoginUser user = new LoginUser(1L, "a@a.com", "password", Authority.DISABLED);
        repository.save(user);
        assertThat(repository.findByEmailAndPassword("a@a.com", "password")).isEqualTo(user);
    }
}
