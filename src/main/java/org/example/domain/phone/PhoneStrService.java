package org.example.domain.phone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneStrService {

    private final PhoneStrRepository phoneStrRepository;
    
    @Transactional(readOnly = true)
    public List<PhoneStrApiDto> searchFindAllDesc() {
        return  phoneStrRepository.searchFindAllDesc();
    }

    @Transactional
    public void save(PhoneStr phoneStr0) {
        phoneStrRepository.save(phoneStr0);
    }

    @Transactional(readOnly = true)
    public PhoneStr findById(Long phoneStrId) {
       return phoneStrRepository.findById(phoneStrId).orElseThrow();
    }

    @Transactional
    public Page<PhoneStrApiDto> searchAllV2(PhoneStrSearchCondition condition, Pageable pageable) {
        return phoneStrRepository.searchAllV2(condition, pageable);
    }
}