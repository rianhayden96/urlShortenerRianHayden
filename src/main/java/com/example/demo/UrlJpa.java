package com.example.demo;

import com.example.demo.UrlObj;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UrlJpa extends JpaRepository<UrlObj, Long>{

}
