package fr.eni.lodo.repository;

import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Jeu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JeuRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Jeu> findAll(){
        String sql = "select * from jeu";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Jeu.class));
    }

    public Optional<Jeu> findById(int id) {
        String sql = "select * from jeu where no_jeu = ?" ;
        Jeu jeu = null;
        try {
            jeu = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Jeu.class), id);
        }catch(EmptyResultDataAccessException exc) {

        }
        return Optional.ofNullable(jeu);
    }

    public void save(Jeu jeu) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("no_jeu", jeu.getNo_jeu())
            .addValue("titre", jeu.getTitre())
            .addValue("reference", jeu.getReference())
            .addValue("description", jeu.getDescription())
            .addValue("duree", jeu.getDuree())
            .addValue("age_min", jeu.getAge_min())
            .addValue("tarif_journee", jeu.getTarif_journee())
        ;
        if (jeu.getNo_jeu() == null){
            // ajout
            String sql =    "INSERT INTO jeu(titre, reference, description, tarif_journee, age_min, duree) " +
                            "VALUES (:titre, :reference, :description, :tarif_journee, :age_min, :duree);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_client"});
            // recup l'id auto générer
            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE jeu " +
                            "SET titre=:titre, reference=:reference, description=:description, tarif_journee=:tarif_journee, age_min=:age_min, duree=:duree " +
                            "WHERE no_jeu = :no_jeu;";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_jeu"});
        }
    }

    public void supprimer(Jeu jeu){
        String sql =    "DELETE FROM jeu " +
                "WHERE no_jeu = ?;";
        jdbcTemplate.update(sql, jeu.getNo_jeu());
    }

}
