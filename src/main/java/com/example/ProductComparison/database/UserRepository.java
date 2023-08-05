package com.example.ProductComparison.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<ProductHistory, Integer> {
    List<ProductHistory> findAllByuserEmailEquals(String userEmails);
}