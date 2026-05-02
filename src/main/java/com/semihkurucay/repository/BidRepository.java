package com.semihkurucay.repository;

import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.entity.AuctionItem;
import com.semihkurucay.entity.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("SELECT ai " +
            "FROM Bid b " +
            "JOIN b.auctionItem ai " +
            "WHERE b.user.id = :userId AND ai.isActive = true " +
            "ORDER BY ai.createTime DESC")
    Page<AuctionItem> findJoinedAllAuctionItems(@Param("userId") Long userId, Pageable pageable);
}
