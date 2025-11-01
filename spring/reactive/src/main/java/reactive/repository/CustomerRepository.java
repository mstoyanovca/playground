package reactive.repository;

import org.springframework.data.repository.CrudRepository;
import reactive.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    // Spring Data JPA creates a customerRepository implementation, when you run the application;
}
