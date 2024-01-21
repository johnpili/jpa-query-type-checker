package com.johnpili.query_type_checker;

import jakarta.persistence.Query;
import org.eclipse.persistence.internal.jpa.EJBQueryImpl;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.query.sqm.internal.QuerySqmImpl;

public interface QueryTypeChecker {
    enum QueryType {
        JPQL,
        SQL
    }

    default QueryType getQueryType(Query query) throws Exception {
        if (query instanceof EJBQueryImpl ejbQuery) {
            if(ejbQuery.getDatabaseQuery().isJPQLCallQuery()) {
                return QueryType.JPQL;
            }

            if(ejbQuery.getDatabaseQuery().isSQLCallQuery()) {
                return QueryType.SQL;
            }
        }

        if(query instanceof QuerySqmImpl<?>) {
            return QueryType.JPQL;
        }

        if(query instanceof NativeQueryImpl) {
            return QueryType.SQL;
        }

        throw new Exception("Unknown type");
    }
}
