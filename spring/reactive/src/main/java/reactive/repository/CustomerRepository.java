package reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reactive.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Spring Data JPA creates a customerRepository implementation, when you run the application;
}
