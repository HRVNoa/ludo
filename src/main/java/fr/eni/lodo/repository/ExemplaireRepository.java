package fr.eni.lodo.repository;

import fr.eni.lodo.models.Exemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExemplaireRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Exemplaire> findById(int id){
        String sql = "select codebarre, louable from exemplaire WHERE jeu = "+id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Exemplaire.class));
    }

}
