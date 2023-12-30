package com.example.ecsite2023.repository;

import com.example.ecsite2023.repository.entity.Cart;
import com.example.ecsite2023.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "select * "
            + "from carts c "
            + "left join items i on c.item_id = i.id "
            + "where c.user_id = ?1 ",
            nativeQuery = true)
    public List<Cart> findByUserId(Integer id);

}
