package org.example.domain.user;
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
public class UserApiDto {
    private Long id;

    private String name;
    private String email;
    private String picture;

    private RoleGUEST roleGuest;
    private RoleUSER roleUser;
    private RoleCOMPANY roleCompany;
    private RoleADMIN roleAdmin;

    private Role role;
    private String isDel;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public UserApiDto(Long id
, String name, String email, String picture,RoleGUEST roleGuest, RoleUSER roleUser, RoleCOMPANY roleCompany,RoleADMIN roleAdmin, Role role, String isDel
, LocalDateTime createdDate, LocalDateTime modifiedDate
) {
     this.id = id;

     this.name = name;
     this.email = email;
     this.picture = picture;

     this.roleGuest = roleGuest;
     this.roleUser = roleUser;
     this.roleCompany = roleCompany;
     this.roleAdmin = roleAdmin;

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