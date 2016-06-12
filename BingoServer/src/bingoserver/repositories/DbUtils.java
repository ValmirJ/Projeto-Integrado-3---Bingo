/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guilherme
 */
class DbUtils {

    static class MultipleBinder {

        private final List<? extends Object> values;

        MultipleBinder(List<? extends Object> values) {
            this.values = values;
        }

        public String questionMarkString() {
            StringBuilder builder = new StringBuilder("(");
            int count = values.size();

            for (int i = 1; i <= count; i++) {
                builder.append("?");

                if (i < count) {
                    builder.append(", ");
                }
            }

            return builder.append(")").toString();
        }

        public int setValuesAsInt(PreparedStatement statement) throws SQLException {
            return setValuesAsInt(statement, 1);
        }

        public int setValuesAsInt(PreparedStatement statement, int startPosition) throws SQLException {
            int paramIndex = startPosition;

            for (Object obj : values) {
                statement.setInt(paramIndex, (Integer) obj);
                paramIndex++;
            }

            return paramIndex;
        }

    }

    public static MultipleBinder buildBinder(List<? extends Object> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }
        return new MultipleBinder(values);
    }
}
