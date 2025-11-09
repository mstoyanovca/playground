package reactive.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactive.entity.Customer;
import reactive.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebFluxTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockitoBean
    private CustomerService service;

    private final Customer customer1 = new Customer(1L, "firstName1", "lastName1");

    @Test
    public void createFromPostTest() {
        when(service.save(any(Customer.class))).thenReturn(Mono.just(customer1));

        webTestClient
                .post()
                .uri("/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(customer1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Customer.class).isEqualTo(customer1);

        verify(service, times(1)).save(any());
    }

    @Test
    public void createFromGetTest() {
        when(service.save(any(Customer.class))).thenReturn(Mono.just(customer1));

        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/customer/create")
                        .queryParam("firstName", "firstName")
                        .queryParam("lastName", "lastName")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Customer.class).isEqualTo(customer1);

        verify(service, times(1)).save(any());
    }

    @Test
    public void allTest() {
        Customer customer2 = new Customer(2L, "firstName2", "lastName2");
        when(service.findAll()).thenReturn(Flux.just(customer1, customer2));

        webTestClient.get()
                .uri("/customer/all")
                .exchange()
                .expectStatus().isOk();
        //.expectHeader().contentType(MediaType.TEXT_EVENT_STREAM_VALUE)
        //.expectBody(Customer.class).isEqualTo(customer);

        verify(service, times(1)).findAll();
    }

    // loadtest
}
