package com.jy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl, Long> {

    List<Girl> findByAge(int age);
}
