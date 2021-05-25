package com.example.demo.pojo.admin.vo;

import com.example.demo.pojo.system.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtInfo implements Serializable {

    private Integer id;
    private String adminName;
    private String adminAvatar;

    private List<SysRole> roles;
    private List<String> permissions;

}
