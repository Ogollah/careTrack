package com.stephen.careTrack.helper;

import com.stephen.careTrack.DTO.PatientDTO;
import com.stephen.careTrack.DTO.UserDTO;
import com.stephen.careTrack.model.Patient;
import com.stephen.careTrack.model.User;
import com.stephen.careTrack.service.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper extends PersonMapper
{
    public static UserPrincipal userToPrincipal(User user) {
        UserPrincipal userp = new UserPrincipal();
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());

        userp.setUsername(user.getUsername());
        userp.setPassword(user.getPassword());
        userp.setEnabled(user.isEnabled());
        userp.setAuthorities(authorities);
        return userp;
    }

    public static User dtoToEntity(UserDTO userDTO)
    {
        User user = new User();
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setBirth_date(userDTO.getBirth_date());
        user.setIDNumber(userDTO.getIDNumber());
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
