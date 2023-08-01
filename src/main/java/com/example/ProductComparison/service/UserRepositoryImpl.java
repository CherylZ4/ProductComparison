package com.example.ProductComparison.service;

import com.example.ProductComparison.database.User;
import com.example.ProductComparison.database.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private List<User> userList = new ArrayList<>();

    @Override
    public <S extends User> S save(S entity) {
        userList.add(entity);
        return entity;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        for (S entity : entities) {
            userList.add(entity);
            savedEntities.add(entity);
        }
        return savedEntities;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        User user = userList.stream().filter(u -> Objects.equals(u.getId(), integer)).findFirst().orElse(null);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userList.stream().anyMatch(u -> Objects.equals(u.getId(), integer));

    }

    @Override
    public Iterable<User> findAll() {
        return userList;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> integers) {
        List<User> users = new ArrayList<>();
        for (Integer id : integers) {
            Optional<User> user = findById(id);
            user.ifPresent(users::add);
        }
        return users;
    }

    @Override
    public long count() {
        return userList.size();
    }

    @Override
    public void deleteById(Integer integer) {
        userList.removeIf(user -> Objects.equals(user.getId(), integer));
    }

    @Override
    public void delete(User entity) {
        userList.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        for (Integer id : integers) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        for (User entity : entities) {
            userList.remove(entity);
        }
    }

    @Override
    public void deleteAll() {
        userList.clear();
    }


}
