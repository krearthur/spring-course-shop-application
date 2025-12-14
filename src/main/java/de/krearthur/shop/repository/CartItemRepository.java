package de.krearthur.shop.repository;

import de.krearthur.shop.model.CartItem;
import de.krearthur.shop.model.Product;
import de.krearthur.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);
}
