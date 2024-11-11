package by.baby.usermicroservice.service;

import by.baby.usermicroservice.persistence.entity.UserEntity;
import by.baby.usermicroservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@Transactional
public class UserService implements BaseService<UserEntity> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public UserEntity update(UserEntity entity, Long id) {
        return userRepository.findById(id)
                .map(entity1 -> {
                    entity1.setLogin(entity.getLogin());
                    entity1.setPassword(entity.getPassword());
                    entity1.setEmail(entity.getEmail());
                    entity1.setVerified(entity.isVerified());
                    return entity1;
                })
                .map(userRepository::saveAndFlush)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @CachePut(value = "user", key = "#entity.id")
    public UserEntity create(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
