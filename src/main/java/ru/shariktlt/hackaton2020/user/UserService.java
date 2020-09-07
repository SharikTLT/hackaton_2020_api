package ru.shariktlt.hackaton2020.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.user.da.UserGroupsRepository;
import ru.shariktlt.hackaton2020.user.da.UserRepository;
import ru.shariktlt.hackaton2020.user.da.UserSessionRepository;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;
import ru.shariktlt.hackaton2020.user.entity.UserGroupsEntity;
import ru.shariktlt.hackaton2020.user.entity.UserSessionEntity;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserGroupsRepository userGroupsRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    public UserEntity register(UserEntity user) {
        if (userRepository.countByEmail(user.getEmail()) > 0) {
            throw new DuplicateKeyException("user with name or email exist");
        }
        user.setPasswordHash(hashPassword(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    private String hashPassword(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public UserSessionEntity loginByCredentials(String login, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(login);
        if (user.isPresent()) {
            if (bCryptPasswordEncoder.matches(password, user.get().getPasswordHash())) {
                return createSesson(user.get());
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    private UserSessionEntity createSesson(UserEntity userEntity) {
        UserSessionEntity session = new UserSessionEntity();
        session.setUserEntity(userEntity);
        session.setLast_update(new GregorianCalendar());
        return userSessionRepository.save(session);
    }

    public UserEntity getUserByToken(UUID token) {
        UserSessionEntity sessionEntity = userSessionRepository.findById(token).orElseThrow(EntityNotFoundException::new);
        return sessionEntity.getUserEntity();
    }

    public List<UserEntity> getList(int page, int limit) {
        return userRepository.findAll(PageRequest.of(page, limit));
    }

    public long getTotal() {
        return userRepository.count();
    }

    public Set<String> getUserGroups(long userId) {
        return userGroupsRepository.findByUserId(userId)
                .stream()
                .map(UserGroupsEntity::getGroup)
                .collect(Collectors.toSet());
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
