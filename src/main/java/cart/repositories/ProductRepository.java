package cart.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cart.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
