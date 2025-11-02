package reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // this is what would be coming from an Angular front end:
    /*@PostMapping(path = "/create")
    public Customer create(@RequestBody Customer customer) {
        return customer;
    }*/

    @GetMapping(path = "/create")
    public Customer create(@RequestParam String firstName, @RequestParam String lastName) {
        // http://localhost:8080/customer/create?firstName=Martin&lastName=Stoyanov
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        Customer savedCustomer = customerRepository.save(customer);
        customer.setId(savedCustomer.getId());
        return customer;
    }

    @GetMapping(path = "/all")
    public List<Customer> findAll() {
        // http://localhost:8080/customer/all
        return customerRepository.findAll();
    }
}
