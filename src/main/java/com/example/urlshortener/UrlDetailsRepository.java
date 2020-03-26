package com.example.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UrlDetailsRepository extends JpaRepository<UrlDetails, Long> {
    UrlDetails findByCode(String code);
    UrlDetails findByUrl(String url);
    List<UrlDetails> findAll();
    UrlDetails save(UrlDetails obj);
    void deleteByCode(String code);
}
