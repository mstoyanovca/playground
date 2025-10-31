package testing_web_layer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import testing_web_layer.controller.HomeController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TestingWebLayerApplicationTests {
    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
