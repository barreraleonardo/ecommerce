package com.nocountry.ecommerce.domain.usecase.impl;

import com.nocountry.ecommerce.common.exception.error.AlreadyExistsException;
import com.nocountry.ecommerce.common.exception.error.ResourceNotFoundException;
import com.nocountry.ecommerce.common.exception.error.RoleNotFoundException;
import com.nocountry.ecommerce.domain.model.User;
import com.nocountry.ecommerce.domain.repository.RoleRepository;
import com.nocountry.ecommerce.domain.repository.UserRepository;
import com.nocountry.ecommerce.domain.usecase.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String NAME = "User";

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final static String ROLE_USER = "ROLE_USER";

    //=========================Create User=========================//

    @Override
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) throw new AlreadyExistsException(user.getEmail());

        user.setRole(roleRepository.findByName(ROLE_USER)
                .orElseThrow((() -> new RoleNotFoundException(ROLE_USER))));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    //=========================Login=========================//

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
           .orElseThrow(() -> new UsernameNotFoundException("User not found with the email: " + email));

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("id", user.getId());

        return user;
    }


    //=========================Get User=========================//

    @Transactional
    @Override
    public User updateUser(Long id, User user) {

        User userFromDb = getByIdIfExist(id);

        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setPhone(user.getPhone());
        userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userFromDb);
    }

    //=========================Delete=========================//

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = getByIdIfExist(id);
        userRepository.delete(user);
    }

    //=========================Util=========================//

    @Transactional
    @Override
    public User getByIdIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NAME, id));
    }


}
