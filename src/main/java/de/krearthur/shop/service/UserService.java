package de.krearthur.shop.service;

import de.krearthur.shop.model.User;
import de.krearthur.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private List<User> userList = new ArrayList<>();
    //private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> fetchUser(Long id) {
        //return userList.stream()
        //        .filter(u -> u.getId().equals(id))
        //        .findFirst();
        return userRepository.findById(id);
    }

    public Long addUser(User user) {
        //user.setId(nextId++);
        //userList.add(user);
        //return user.getId();
        return userRepository.save(user).getId();
    }

    public boolean updateUser(Long id, User updateData) {
        /*return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updateData.getFirstName());
                    existingUser.setLastName(updateData.getLastName());
                    return true;
                })
                .orElse(false);
         */
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateData.getFirstName());
                    existingUser.setLastName(updateData.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
