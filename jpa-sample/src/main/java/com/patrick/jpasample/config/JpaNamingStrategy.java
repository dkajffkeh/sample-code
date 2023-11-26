package com.patrick.jpasample.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class JpaNamingStrategy extends PhysicalNamingStrategyStandardImpl {
/*    private static final String PREFIX = "tb_";*/


    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        /*Identifier prefixedTableName = new Identifier(PREFIX + camelToSnake(name.getText()), name.isQuoted());*/
        Identifier prefixedTableName = new Identifier(camelToSnake(name.getText()), name.isQuoted());
        return super.toPhysicalTableName(prefixedTableName, context);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        Identifier prefixedTableName = new Identifier(camelToSnake(name.getText()), name.isQuoted());
        return super.toPhysicalColumnName(prefixedTableName, context);
    }

    private String camelToSnake(String str){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        str = str
                .replaceAll( regex, replacement)
                .toLowerCase();
        return str;
    }
}

