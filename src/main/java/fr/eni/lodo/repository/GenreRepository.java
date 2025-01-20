package fr.eni.lodo.repository;

import fr.eni.lodo.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Genre> findAll(){
        String sql = "select * from genres";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Genre.class));
    }

    public Optional<Genre> findById(int id) {
        String detailSql = "select * from genres where no_genre = "+id ;
        Genre genre = null;
        try {
            genre = jdbcTemplate.queryForObject(detailSql, new BeanPropertyRowMapper<>(Genre.class));
            if (null == genre){
                return Optional.empty();
            }
        }catch(EmptyResultDataAccessException exc) {

        }
        return Optional.ofNullable(genre);
    }

    public Optional<Genre> findByLibelle(String libelle) {
        String detailSql = "select * from genres where libelle like ?";
        Genre genre = null;
        try {
            genre = jdbcTemplate.queryForObject(detailSql, new BeanPropertyRowMapper<>(Genre.class), libelle);
            if (null == genre){
                return Optional.empty();
            }
        }catch(EmptyResultDataAccessException exc) {

        }
        return Optional.ofNullable(genre);
    }

//    public void save(Genre genre) {
//        MapSqlParameterSource params = new MapSqlParameterSource()
//            .addValue("no_jeu", jeu.getNo_jeu())
//            .addValue("titre", jeu.getTitre())
//            .addValue("reference", jeu.getReference())
//            .addValue("description", jeu.getDescription())
//            .addValue("duree", jeu.getDuree())
//            .addValue("age_min", jeu.getAge_min())
//            .addValue("tarif_journee", jeu.getTarif_journee())
//        ;
//        if (jeu.getNo_jeu() == null){
//            // ajout
//            String sql =    "INSERT INTO jeu(titre, reference, description, tarif_journee, age_min, duree) " +
//                            "VALUES (:titre, :reference, :description, :tarif_journee, :age_min, :duree);";
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_jeu"});
//            // recup l'id auto générer
//            System.out.println(keyHolder.getKey());
//        }else{
//            // modif
//            String sql =    "UPDATE jeu " +
//                            "SET titre=:titre, reference=:reference, description=:description, tarif_journee=:tarif_journee, age_min=:age_min, duree=:duree " +
//                            "WHERE no_jeu = :no_jeu;";
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_jeu"});
//        }
//        System.out.println(jeu.getGenres().toString());
//    }

//    public void supprimer(Jeu jeu){
//        String sql =    "DELETE FROM jeu " +
//                "WHERE no_jeu = ?;";
//        jdbcTemplate.update(sql, jeu.getNo_jeu());
//    }

}
