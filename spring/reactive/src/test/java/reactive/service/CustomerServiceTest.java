package reactive.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository repository;
    @InjectMocks
    private CustomerService service;

    @Test
    public void saveTest() {
        Customer customer = new Customer(1L, "firstName", "lastName");

        when(repository.save(customer)).thenReturn(Mono.just(customer));

        StepVerifier
                .create(service.save(customer))
                .expectNextMatches(c -> c.id() == 1L && c.firstName().equals("firstName") && c.lastName().equals("lastName"))
                .verifyComplete();

        verify(repository, times(1)).save(customer);
    }

    @Test
    public void findAllTest() {
        Customer customer1 = new Customer(1L, "firstName1", "lastName1");
        Customer customer2 = new Customer(2L, "firstName2", "lastName2");

        when(repository.findAll()).thenReturn(Flux.just(customer1, customer2));

        StepVerifier
                .create(service.findAll())
                .expectNextMatches(c -> c.id() == 1L && c.firstName().equals("firstName1") && c.lastName().equals("lastName1"))
                .expectNextMatches(c -> c.id() == 2L && c.firstName().equals("firstName2") && c.lastName().equals("lastName2"))
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
    public void loadTestTest() {
        final int quantity = 1000;
        List<Customer> customers = IntStream
                .rangeClosed(1, quantity)
                .boxed()
                .map(i -> new Customer(null, "firstname" + i, "lastname" + i))
                .toList();

        when(repository.deleteAll()).thenReturn(Mono.empty());
        when(repository.saveAll(customers)).thenReturn(Flux.fromIterable(customers));
        when(repository.findAll()).thenReturn(Flux.fromIterable(customers));

        StepVerifier
                .create(service.loadTest())
                .expectNextCount(1000)
                .verifyComplete();

        verify(repository, times(1)).deleteAll();
        verify(repository, times(1)).saveAll(customers);
        verify(repository, times(1)).findAll();
    }
}
