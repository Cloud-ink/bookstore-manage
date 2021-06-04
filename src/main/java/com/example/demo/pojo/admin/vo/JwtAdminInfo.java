package com.example.demo.pojo.admin.vo;

import com.example.demo.pojo.admin.Admin;
import com.example.demo.pojo.system.SysRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtAdminInfo extends Admin implements Serializable, UserDetails {

    private Integer id;
    private String adminName;
    private String adminAvatar;
    private String password;
    private SysRole role;
    private List<String> permissions;
    // 权限（角色）列表
    Collection<? extends GrantedAuthority> authorities;

    public JwtAdminInfo(Admin admin){
        this.id = admin.getId();
        this.adminName = admin.getAdminName();
        this.adminAvatar = getAdminAvatar();
        this.password = getPassword();
        //Collections.singleton集合中所有与之重复的元素
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(admin.getRole()));;
    }

    public JwtAdminInfo(Integer id, String adminName, String adminAvatar, String password, List<GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.adminName = adminName;
        this.adminAvatar = adminAvatar;
        this.password = password;
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collection = new HashSet<>();
//        if (!CollectionUtils.isEmpty(sysRoles)) {
//            sysRoles.forEach(role -> {
//                if (role.getCode().startsWith("ROLE_")) {
//                    collection.add(new SimpleGrantedAuthority(role.getCode()));
//                } else {
//                    collection.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
//                }
//            });
//        }
//
//        if (!CollectionUtils.isEmpty(permissions)) {
//            permissions.forEach(per -> {
//                collection.add(new SimpleGrantedAuthority(per));
//            });
//        }
//
//        return collection;
//    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
