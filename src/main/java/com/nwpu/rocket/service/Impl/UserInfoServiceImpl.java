package com.nwpu.rocket.service.Impl;




import com.nwpu.rocket.entity.User;
import com.nwpu.rocket.exception.InsufficientPermissionException;
import com.nwpu.rocket.exception.PasswordWrongException;
import com.nwpu.rocket.exception.UserExistException;
import com.nwpu.rocket.exception.UserNotFoundException;
import com.nwpu.rocket.repository.UserRepository;
import com.nwpu.rocket.service.UserInfoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zcy10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserRepository userRepository;

    public UserInfoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByAccount(String account) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User addAdmin(User newAdmin) {
        String userAccount = newAdmin.getAccount();
        User user = userRepository.findByAccount(userAccount);
        if (user != null) {
            throw new UserExistException();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = newAdmin.getPassword();
        newAdmin.setPassword(encoder.encode(rawPassword));
        newAdmin.setPasswordClearText(rawPassword);
        newAdmin.setRoles(User.ROLE_ADMIN);
        return userRepository.saveAndFlush(newAdmin);
    }

    @Override
    public User addUser(User newUser) {
        String userAccount = newUser.getAccount();
        User user = userRepository.findByAccount(userAccount);
        if (user != null) {
            throw new UserExistException();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = newUser.getPassword();
        newUser.setPasswordClearText(rawPassword);
        newUser.setPassword(encoder.encode(rawPassword));
        newUser.setRoles(User.ROLE_NORMAL);
        return userRepository.saveAndFlush(newUser);
    }
    @Override
    public User userStatusOnOffByUser(User user, Integer status) {
        user.setStatus(status);
        user=userRepository.saveAndFlush(user);

        return user;
    }

    @Override
    public List<User> findSpecificUsers(String role) {
        return userRepository.findAllByRoles(role);
    }

    @Override
    public User updateUser(User user, String newName, String newPhone, String newEmail) {
        user.setName(newName);
        user.setPhone(newPhone);
        user.setEmail(newEmail);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User changePassword(User user, String oldPassword, String newPassword) {
        user = userRepository.findById(user.getId()).orElse(null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(user.getPassword());
        if (!encoder.matches(oldPassword, user.getPassword())) {
            throw new PasswordWrongException();
        }
        user.setPasswordClearText(newPassword);
        newPassword = encoder.encode(newPassword);
        user = userRepository.saveAndFlush(user.setPassword(newPassword));
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User resetPassword(String account, String newPassword) {
        User user = findByAccount(account);
        if(user==null){
            throw new UserNotFoundException();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String finalPassword = encoder.encode(newPassword);
        user.setPassword(finalPassword);
        user.setPasswordClearText(newPassword);
        return userRepository.saveAndFlush(user);
    }
}