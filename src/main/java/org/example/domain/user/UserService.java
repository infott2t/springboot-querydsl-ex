package org.example.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email=" + userEmail));
    }

    @Transactional(readOnly = true)
    public List<UserApiDto> searchFindAllDesc() {
        return  userRepository.searchFindAllDesc();
    }

    @Transactional
    public void save(User userBase) {
        userRepository.save(userBase);
    }

    @Transactional(readOnly = true)
    public User findById(Long valueOf) {
        return userRepository.findById(valueOf).orElseThrow();
    }

    @Transactional
    public User delete(Long valueOf) {
        User user = userRepository.findById(valueOf).orElseThrow();

        user.setIsDel("Y");
        user.setModifiedDate(LocalDateTime.now());

        userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    public Page<UserApiDto> searchAllV2(UserSearchCondition condition, Pageable pageable) {
        return userRepository.searchAllV2(condition, pageable);
    }
}
