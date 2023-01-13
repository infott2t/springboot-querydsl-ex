package org.example.firstinstance.controller.firstinstanceurl.form;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.roleclass.admin.RoleADMIN;
import org.example.domain.roleclass.company.RoleCOMPANY;
import org.example.domain.roleclass.guest.RoleGUEST;
import org.example.domain.roleclass.user.RoleUSER;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserApiDtoForm {
    private Long id;

    private  String name;
    private String email;
    private String picture;

    private String roleGuestId;
    private String roleUserId;
    private String roleCompanyId;
    private String roleAdminId;

    private String role;
    private String isDel;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserApiDtoForm(Long id, String name, String email, String picture, String roleGuestId, String roleUserId, String roleCompanyId, String roleAdminId, String role, String isDel, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.roleGuestId = roleGuestId;
        this.roleUserId = roleUserId;
        this.roleCompanyId = roleCompanyId;
        this.roleAdminId = roleAdminId;
        this.role = role;
        this.isDel = isDel;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

/*Builder,    id().

 name().
 email().
 picture().
 isDel().

 createDate().
 modifiedDate().

*/}