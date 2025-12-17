package org.example.spring_boot.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SnakeCasePhysicalNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier == null ? null : convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier == null ? null : convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier == null ? null : convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier == null ? null : convertToSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier == null ? null : convertToSnakeCase(identifier);
    }

    private Identifier convertToSnakeCase(Identifier identifier) {
        String snakeCaseName = toSnakeCase(identifier.getText());
        return new Identifier(snakeCaseName, identifier.isQuoted());
    }

    private String toSnakeCase(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}
