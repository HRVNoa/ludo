package fr.eni.lodo.repository;

import fr.eni.lodo.models.Exemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExemplaireRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Exemplaire> findById(int id){
        String sql = "select codebarre, louable from exemplaires WHERE no_jeu = "+id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Exemplaire.class));
    }

    public void save(Exemplaire exemplaire) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_exemplaire", exemplaire.getNo_exemplaire())
                .addValue("codebarre", exemplaire.getCodebarre())
                .addValue("louable", exemplaire.isLouable())
                .addValue("no_jeu", exemplaire.getNo_jeu().getNo_jeu())
                ;
        if (exemplaire.getNo_exemplaire() == null){
            // ajout
            String sql =   "INSERT INTO exemplaires(codebarre, louable, no_jeu) " +
                            "VALUES (:codebarre, :louable, :no_jeu);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_exemplaire"});
            // recup l'id auto générer
//            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE exemplaires " +
                            "SET codebarre=:codebarre, louable=:louable, no_jeu=:no_jeu " +
                            "WHERE no_exemplaire=:no_exemplaire;";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_exemplaire"});
        }
    }

}
