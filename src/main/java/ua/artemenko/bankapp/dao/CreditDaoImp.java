package ua.artemenko.bankapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.artemenko.bankapp.model.Credit;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CreditDaoImp implements CreditDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(CreditDaoImp.class);


    @Override
    public int addCredit(Credit credit) throws DataAccessException{
        String sql = "INSERT INTO manager(amountOfCredit,sumOfIndebtedness,durationOfContract,typePayment,interestRate) " +
                "VALUE (:amountOfCredit,:sumOfIndebtedness,:durationOfContract,:typePayment,:interestRate)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("amountOfCredit",credit.getAmountOfCredit());
        mapSqlParameterSource.addValue("sumOfIndebtedness",credit.getSumOfIndebtedness());
        mapSqlParameterSource.addValue("durationOfContract",credit.getDurationOfContract());
        mapSqlParameterSource.addValue("typePayment",credit.getTypePayment());
        mapSqlParameterSource.addValue("interestRate",credit.getInterestRate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        logger.info("A new entry is added to the database.Loan information: " + credit);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Credit getCreditById(int id) throws DataAccessException {
        String sql = "SELECT idContract,amountOfCredit,sumOfIndebtedness,durationOfContract,typePayment,interestRate " +
                "FROM manager " +
                "WHERE idContract=:id;";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        Credit credit = jdbcTemplate.queryForObject(sql,mapSqlParameterSource,new CreditRowMapper());
        logger.info("Entry obtained.Information: " + credit);
        return credit;
    }

    @Override
    public List<Credit> getListCredit() throws DataAccessException {
        String sql = "SELECT idContract,amountOfCredit,sumOfIndebtedness,durationOfContract,typePayment,interestRate FROM manager;";
        List<Credit> list = jdbcTemplate.query(sql, new CreditRowMapper());
        logger.info("All entries obtained from the database");
        return list;
    }


    @Override
    public void updateCredit(Credit credit) throws DataAccessException {
        String sql = "UPDATE manager SET sumOfIndebtedness=:sumOfIndebtedness WHERE idContract=:id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",credit.getIdContract());
        mapSqlParameterSource.addValue("sumOfIndebtedness",credit.getSumOfIndebtedness());
        jdbcTemplate.update(sql,mapSqlParameterSource);
        logger.info("Entry is updated");
    }

    private static final class CreditRowMapper implements RowMapper<Credit> {

        @Override
        public Credit mapRow(ResultSet resultSet, int i) throws SQLException {
            Credit credit = new Credit();
            credit.setIdContract(resultSet.getInt("idContract"));
            credit.setAmountOfCredit(resultSet.getBigDecimal("amountOfCredit"));
            credit.setSumOfIndebtedness(resultSet.getBigDecimal("sumOfIndebtedness"));
            credit.setDurationOfContract(resultSet.getInt("durationOfContract"));
            credit.setTypePayment(resultSet.getInt("typePayment"));
            credit.setInterestRate(resultSet.getDouble("interestRate"));
            return credit;
        }
    }

    public void setJdbcTemplate() {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
