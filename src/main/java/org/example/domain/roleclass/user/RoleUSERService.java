package org.example.domain.roleclass.user;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleUSERService {

    private final RoleUSERRepository roleUSERRepository;
    
    @Transactional(readOnly = true)
    public List<RoleUSERApiDto> searchFindAllDesc() {
        return  roleUSERRepository.searchFindAllDesc();
    }

    @Transactional(readOnly = true)
    public RoleUSER findById(Long id) {
        return roleUSERRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void save(RoleUSER roleUSER) {
        roleUSERRepository.save(roleUSER);
    }

    @Transactional(readOnly = true)
    public Page<RoleUSERApiDto> searchAllV2(RoleUSERSearchCondition condition, Pageable pageable) {
        return roleUSERRepository.searchAllV2(condition, pageable);
    }
}