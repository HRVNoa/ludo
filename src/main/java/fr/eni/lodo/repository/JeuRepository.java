package fr.eni.lodo.repository;

import fr.eni.lodo.models.Client;
import fr.eni.lodo.models.Genre;
import fr.eni.lodo.models.Jeu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JeuRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Jeu> findAll(){
        String sql = "select * from jeux";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Jeu.class));
    }

    public Optional<Jeu> findById(int id) {
        String detailSql = "select * from jeux where no_jeu = "+id ;

        String getGenreSql = "SELECT libelle FROM jeux JOIN jeux_genres ON jeux.no_jeu = jeux_genres.no_jeu " +
                "JOIN genres ON jeux_genres.no_genre = genres.no_genre " +
                "WHERE jeux.no_jeu = " + id +
                " ORDER BY jeux.no_jeu ASC;";

        Jeu jeu = null;
        List<Genre> genre;
        try {
            jeu = jdbcTemplate.queryForObject(detailSql, new BeanPropertyRowMapper<>(Jeu.class));
            if (null == jeu){
                return Optional.ofNullable(jeu);
            }
            genre = jdbcTemplate.query(getGenreSql, new BeanPropertyRowMapper<>(Genre.class));
            jeu.setGenres(genre);
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (jeu.getNo_jeu() == null){
            // ajout
            String sql =    "INSERT INTO jeux(titre, reference, description, tarif_journee, age_min, duree) " +
                            "VALUES (:titre, :reference, :description, :tarif_journee, :age_min, :duree);";
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_jeu"});
            jeu.setNo_jeu((Integer) keyHolder.getKey());
            // recup l'id auto générer
            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE jeux " +
                            "SET titre=:titre, reference=:reference, description=:description, tarif_journee=:tarif_journee, age_min=:age_min, duree=:duree " +
                            "WHERE no_jeu = :no_jeu;";
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_jeu"});
        }
        // Gestion des genres
        String deletesql = "DELETE FROM jeux_genres " +
                "WHERE no_jeu = :no_jeu;";
        MapSqlParameterSource paramsDelete = new MapSqlParameterSource().addValue("no_jeu", jeu.getNo_jeu());
        namedParameterJdbcTemplate.update(deletesql, paramsDelete, keyHolder, new String[]{"no_jeu","no_genre"});

        for (Genre genre : jeu.getGenres()){
            if (genre != null){
                MapSqlParameterSource paramsGenres = new MapSqlParameterSource().addValue("no_jeu", jeu.getNo_jeu()).addValue("no_genre", genre.getNo_genre());
                String genresSql = "INSERT INTO jeux_genres(" +
                        "no_jeu, no_genre)" +
                        "VALUES (:no_jeu, :no_genre);";
                namedParameterJdbcTemplate.update(genresSql, paramsGenres, keyHolder, new String[]{"no_jeu","no_genre"});
            }
        }
    }

    public void supprimer(Jeu jeu){
        String sql =    "DELETE FROM jeu " +
                "WHERE no_jeu = ?;";
        jdbcTemplate.update(sql, jeu.getNo_jeu());
    }

}
