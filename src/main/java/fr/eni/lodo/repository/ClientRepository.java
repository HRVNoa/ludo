package fr.eni.lodo.repository;

import fr.eni.lodo.models.Client;
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
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Client> findAll(){
        String sql = "select * from clients";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Client.class));
    }

    public Optional<Client> findById(int id) {
        String sql = "select * from clients where no_client = ?" ;
        Client client = null;
        try {
            client = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Client.class), id);
        }catch(EmptyResultDataAccessException exc) {

        }
        return Optional.ofNullable(client);
    }

    public void save(Client client) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("no_client", client.getNo_client())
            .addValue("nom", client.getNom())
            .addValue("prenom", client.getPrenom())
            .addValue("email", client.getEmail())
            .addValue("no_tel", client.getNo_tel())
            .addValue("rue", client.getRue())
            .addValue("code_postal", client.getCode_postal())
            .addValue("ville", client.getVille())
        ;
        if (client.getNo_client() == null){
            // ajout
            String sql =   "INSERT INTO clients(nom, prenom, email, no_tel, rue, code_postal, ville) " +
                    "VALUES (:nom, :prenom, :email, :no_tel, :rue, :code_postal, :ville);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_client"});
            // recup l'id auto générer
            System.out.println(keyHolder.getKey());
        }else{
            // modif
            String sql =    "UPDATE clients " +
                            "SET nom=:nom, prenom=:prenom, email=:email, no_tel=:no_tel, rue=:rue, code_postal=:code_postal, ville=:ville " +
                            "WHERE no_client = :no_client;";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"no_client"});
        }
    }

    public void supprimer(Client client){
        String sql =    "DELETE FROM clients " +
                "WHERE no_client = ?;";
        jdbcTemplate.update(sql, client.getNo_client());
    }

}
