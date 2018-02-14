package com.rks.repository;

import com.rks.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
}
