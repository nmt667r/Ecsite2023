package com.example.ecsite2023.repository;

import com.example.ecsite2023.repository.entity.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Integer> {
    @Query(value = "select * "
            + "from carts c "
            + "left join items i on c.item_id = i.id "
            + "where c.user_id = ?1 ",
            nativeQuery = true)
    public List<ItemCart> findByUserId(Integer id);

}
