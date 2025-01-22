package fr.eni.lodo.repository;

import fr.eni.lodo.models.Jeu;
import fr.eni.lodo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUsername(String username){
        String sql = "select username, users.password, roles from users WHERE username like '"+username+"'";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void save(User user){
        String sql =    "INSERT INTO public.users(username, password, roles) " +
                        "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword(), user.getRoles());
    }

}
