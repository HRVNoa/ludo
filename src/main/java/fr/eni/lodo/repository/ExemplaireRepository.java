package fr.eni.lodo.repository;

import fr.eni.lodo.models.Client;
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

    public List<Exemplaire> findByIdJeu(int id){
        String sql = "select no_exemplaire, codebarre, louable from exemplaire WHERE jeu = "+id;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Exemplaire.class));
    }

    public Exemplaire findById(int id){
        String sql = "select no_exemplaire, codebarre, louable, jeu from exemplaire WHERE no_exemplaire = "+id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Exemplaire.class));
    }

    public void save(Exemplaire exemplaire) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_exemplaire", exemplaire.getNo_exemplaire())
                .addValue("codebarre", exemplaire.getCodebarre())
                .addValue("louable", exemplaire.isLouable())
                ;
        if (exemplaire.getNo_exemplaire() == null){
            // ajout

            params.addValue("jeu", exemplaire.getJeu().getNo_jeu());
            String sql =   "INSERT INTO exemplaire(codebarre, louable, jeu) " +
                            "VALUES (:codebarre, :louable, :jeu);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_exemplaire"});
            // recup l'id auto générer
//            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE exemplaire " +
                            "SET codebarre=:codebarre, louable=:louable " +
                            "WHERE no_exemplaire=:no_exemplaire;";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_exemplaire"});
        }
    }

    public void supprimer(int id){
        String sql = "DELETE FROM exemplaire WHERE no_exemplaire = "+id;
        jdbcTemplate.update(sql);
    }

}
