package com.excercise.lab7.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.excercise.lab7.object.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	private JdbcTemplate jdbc;

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	//query = return nultiple objects.
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient", (rs, n) -> mapRowToIngredient(rs, n));
	}
	//query for object = return only one object
	@Override
	public Ingredient findOne(String id) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select id, name, type from Ingredient where id= ?", 
				(rs, n) -> mapRowToIngredient(rs, n), id);
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
//	@Override
//	public Ingredient findOne(String id) {
//		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", new RowMapper<Ingredient>() {
//			public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
//				return new Ingredient(rs.getString("id"), rs.getString("name"),
//						Ingredient.Type.valueOf(rs.getString("type")));
//			};
//		}, id);
//	}

}
