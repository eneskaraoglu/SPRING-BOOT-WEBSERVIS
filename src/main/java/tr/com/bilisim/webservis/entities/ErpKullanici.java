package tr.com.bilisim.webservis.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErpKullanici implements UserDetails {
    private String kullanici;
    private String sifre;
    private String fabrika;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // İsterseniz yetkileri burada dönebilirsiniz
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Kullanıcı hesabı süresi dolmuş mu?
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Kullanıcı hesabı kilitlenmiş mi?
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Kullanıcı kimlik bilgileri süresi dolmuş mu?
    }

    @Override
    public boolean isEnabled() {
        return true; // Kullanıcı hesabı etkin mi?
    }

	@Override
	public String getPassword() {
		return sifre;
	}

	@Override
	public String getUsername() {
		return kullanici;
	}
}
