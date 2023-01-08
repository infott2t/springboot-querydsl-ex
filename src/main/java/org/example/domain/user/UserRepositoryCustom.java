package org.example.domain.user;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    Page<UserApiDto> searchAllV2(UserSearchCondition condition, Pageable pageable);

  List<UserApiDto> searchFindAllDesc();


}