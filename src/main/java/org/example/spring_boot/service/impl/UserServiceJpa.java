//package org.example.spring_boot.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.persistence.entity.UserEntity;
//import org.example.persistence.repository.UserRepository;
//import org.example.service.UserService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserServiceJpa implements UserService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bcrypt;
//
//    @Transactional(readOnly = true)
//    public boolean passwordMatches(String username,String password){
//        Optional<UserEntity> userOptional = userRepository.findByUserName(username);
//        if(userOptional.isEmpty())
//            return false;
//        UserEntity user = userOptional.get();
//        return bcrypt.matches(password,user.getPasswordHash());
//    }
//
//
//    @Transactional
////    @PreAuthorize("hasRole('TRAINEE') or hasRole('ADMIN') or hasRole('TRAINER')")
//    public boolean changePassword(String usernameFromToken, String oldPassword, String newPassword) {
//        log.debug("Changing password for user: {}", usernameFromToken);
//
//        UserEntity user = userRepository.findByUserName(usernameFromToken)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        // Verify old password
//        if (!bcrypt.matches(oldPassword, user.getPasswordHash())) {
//            throw new IllegalArgumentException("Old password is incorrect");
//        }
//
//        // Update with new password
//        user.setPasswordHash(bcrypt.encode(newPassword));
//        log.info("Password changed successfully for user {}", usernameFromToken);
//        return true;
//    }
//
//    @Transactional
//    public boolean toggleUserActiveStatus(String username) {
//        UserEntity user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + ": user does exist"));
//        user.setActive(!user.isActive());
//        return user.isActive();
//    }
//}
