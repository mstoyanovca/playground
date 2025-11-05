package reactive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;
import reactive.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @PostMapping(path = "/create")
    public Mono<Customer> create(@RequestBody Customer customer) {
        LOGGER.info("Created a customer from POST: {}", customer);
        return customerRepository.save(customer);
    }

    @GetMapping(path = "/create")
    public Mono<Customer> create(@RequestParam String firstName, @RequestParam String lastName) {
        // http://localhost:8080/customer/create?firstName=Martin&lastName=Stoyanov
        Customer customer = new Customer(firstName, lastName);
        LOGGER.info("Created a customer from GET: {}", customer);
        return customerRepository.save(customer);
    }

    @GetMapping(path = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> findAll() {
        // http://localhost:8080/customer/all
        LOGGER.info("findAll()");
        // return customerRepository.findAll().delayElements(Duration.ofSeconds(1));
        return customerRepository.findAll();
    }

    @GetMapping(path = "/loadtest", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> loadTest() {
        // http://localhost:8080/customer/loadtest
        LOGGER.info("loadTest()");
        return customerService.createCustomers();
    }
}
