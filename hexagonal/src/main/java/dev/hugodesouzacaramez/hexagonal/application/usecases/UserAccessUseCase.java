package dev.hugodesouzacaramez.hexagonal.application.usecases;

import dev.hugodesouzacaramez.hexagonal.domain.entity.User;

public interface UserAccessUseCase {

    String createAccount(User user) throws Exception;
    String login(User user);
}