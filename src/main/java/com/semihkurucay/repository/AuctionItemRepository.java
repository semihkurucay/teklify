package com.semihkurucay.repository;

import com.semihkurucay.entity.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {

    @Query("SELECT a " +
            "FROM AuctionItem a " +
            "WHERE a.createdUser.login.username = :username " +
            "AND a.id = :id")
    Optional<AuctionItem> findMyAuctionItemById(String username, Long id);
}
