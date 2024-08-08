package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.repository.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

}
