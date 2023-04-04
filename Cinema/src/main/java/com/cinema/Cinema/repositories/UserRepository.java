package com.cinema.Cinema.repositories;

import com.cinema.Cinema.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface UserRepository extends JpaRepository<User,Long> {
}
