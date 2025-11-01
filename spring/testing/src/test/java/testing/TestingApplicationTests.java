package testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import testing.controller.HomeController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TestingApplicationTests {
    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
