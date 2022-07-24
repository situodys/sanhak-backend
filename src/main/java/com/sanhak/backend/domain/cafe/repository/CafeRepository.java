package com.sanhak.backend.domain.cafe.repository;


import com.sanhak.backend.domain.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {

    void removeByCafeNameAndCategoryName(String cafeName, String categoryName);
}
