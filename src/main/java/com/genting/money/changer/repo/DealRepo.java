package com.genting.money.changer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genting.money.changer.domain.Deal;

@Repository
public interface DealRepo extends JpaRepository<Deal, Long> {

}
