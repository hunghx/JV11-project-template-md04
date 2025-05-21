package ra.project.security.principle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.project.exception.NotFoundException;
import ra.project.model.entity.User;
import ra.project.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceCustom implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("Username not found"));
        // bieens đổi user => userDeetails
        List<GrantedAuthority> list = new ArrayList<>();
        user.getRoles().forEach(role->{
            list.add(new SimpleGrantedAuthority(role.getRoleName().name()));
        });
        return UserDetailsCustom.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .authorities(list)
                .build();
    }
}
