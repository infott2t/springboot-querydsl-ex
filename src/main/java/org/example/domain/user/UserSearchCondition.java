package org.example.domain.user;

import lombok.Data;

@Data
public class UserSearchCondition {

    private String field;       
    private String s;          

    private String sdate;      
    private String edate;      
}