package com.excercise.lab7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.excercise.lab7.object.Ingredient;

public class MyConnectDatabase {
	private JdbcTemplate jdbc;

//	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

//	@Override
//	public Ingredient findOneRawJdpc(String id) {
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = dataSource.getConnection();
//			statement = connection.prepareStatement("select id, name, type from Ingredient");
//			statement.setString(1, id);
//			resultSet = statement.executeQuery();
//			Ingredient ingredient = null;
//			if (resultSet.next()) {
//				ingredient = new Ingredient(resultSet.getString("id"), resultSet.getString("name"),
//						Ingredient.Type.valueOf(resultSet.getString("type")));
//			}
//			return ingredient;
//		} catch (SQLException e) {
//			// ??? What should be done here ???
//		} finally {
//			if (resultSet != null) {
//				try {
//					resultSet.close();
//				} catch (SQLException e) {
//				}
//			}
//			if (statement != null) {
//				try {
//					statement.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//	}
}
