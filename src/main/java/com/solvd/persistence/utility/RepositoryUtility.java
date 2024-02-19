package com.solvd.persistence.utility;

import com.solvd.persistence.connection.MyBaitsConfig;
import org.apache.ibatis.session.SqlSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class RepositoryUtility {

    public static <T> void setIdFromDatabase(T object, PreparedStatement preparedStatement, IdSetter<T> idSetter) throws SQLException {
        try (ResultSet key = preparedStatement.getGeneratedKeys()) {
            if (key.next()) {
                idSetter.setId(object, key.getLong(1));
            }
        }
    }

    public static <T, R> Optional<R> executeTypeSQL(Class<T> repositoryClass, Function<T, Optional<R>> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            return sqlOperation.apply(repository);
        }
    }

    public static <T> void executeVoidSQL(Class<T> repositoryClass, Consumer<T> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            sqlOperation.accept(repository);
            sqlSession.commit();
        }
    }

    public static <T, R> List<R> executeListSQL(Class<T> repositoryClass, Function<T, List<R>> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            return sqlOperation.apply(repository);
        }
    }

    public static <T> boolean executeBooleanSQL(Class<T> repositoryClass, Function<T, Boolean> sqlOperation) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            T repository = sqlSession.getMapper(repositoryClass);
            return sqlOperation.apply(repository);
        }
    }
}
