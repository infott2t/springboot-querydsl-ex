package org.example.domain.address;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressStrService {

    private final AddressStrRepository addressStrRepository;
    
    @Transactional(readOnly = true)
    public List<AddressStrApiDto> searchFindAllDesc() {
        return  addressStrRepository.searchFindAllDesc();
    }

    @Transactional
    public void save(AddressStr addressStr0) {
        addressStrRepository.save(addressStr0);
    }

    @Transactional(readOnly = true)
    public AddressStr findById(Long addressStrId) {
        return addressStrRepository.findById(addressStrId).orElseThrow();
    }
    @Transactional(readOnly = true)
    public Page<AddressStrApiDto> searchAllV2(AddressStrSearchCondition condition, Pageable pageable) {
        return addressStrRepository.searchAllV2(condition, pageable);
    }
}