package de.krearthur.shop.service;

import de.krearthur.shop.dto.AddressDto;
import de.krearthur.shop.dto.UserRequest;
import de.krearthur.shop.dto.UserResponse;
import de.krearthur.shop.model.Address;
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

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public Optional<UserResponse> fetchUser(Long id) {
        //return userList.stream()
        //        .filter(u -> u.getId().equals(id))
        //        .findFirst();
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public Long addUser(UserRequest userRequest) {
        //user.setId(nextId++);
        //userList.add(user);
        //return user.getId();
        User user = new User();
        updateUserFromRequest(user, userRequest);
        return userRepository.save(user).getId();
    }


    public boolean updateUser(Long id, UserRequest updateData) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser, updateData);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if (user.getAddress() != null) {
            Address a = user.getAddress();
            AddressDto addressDto = new AddressDto();
            addressDto.setId(String.valueOf(a.getId()));
            addressDto.setStreet(a.getStreet());
            addressDto.setCity(a.getCity());
            addressDto.setCountry(a.getCountry());
            addressDto.setZipcode(a.getZipcode());
            response.setAddress(addressDto);
        }
        return response;
    }
}
