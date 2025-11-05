package reactive.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private static final int QUANTITY = 10000;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> createCustomers() {
        List<Customer> customers = IntStream.rangeClosed(1, QUANTITY).boxed().map(i -> new Customer("firstname" + i, "lastname" + i)).toList();
        // @formatter:off
        return customerRepository
                .deleteAll()
                .log("Deleting all customers...")
                .thenMany(customerRepository.saveAll(customers))
                .log("Persisting the new customers...")
                .thenMany(customerRepository.findAll())
                .log("Querying the new customers...");
        //@formatter:on
    }
}
