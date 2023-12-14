package com.book.book_project.entity.repository;

import com.book.book_project.entity.DeliveryAddrEntity;
import com.book.book_project.entity.FavoritesEntity;
import com.book.book_project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryAddrEntity, Integer> {


    @Query(value="SELECT d.deliveryseq, d.addr, d.detailaddr, d.zipcode FROM tbl_member m JOIN tbl_deliveraddr d ON m.userid = d.userid WHERE d.userid = :userid", nativeQuery = true)
    List<DeliveryAddrEntity> findDeliveryAddrEntitiesBy(@Param("userid") String userid);



}
