package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImpl<T> implements RowMapper<T> {
    @Override
    public T mapRow(ResultSet resultSet) {

        try {

            BigInteger id = new BigInteger(resultSet.getString("ID"));

            String firstname = resultSet.getString("FIRSTNAME");
            String lastname = resultSet.getString("LASTNAME");
            String middlename = resultSet.getString("MIDDLENAME");

            FullName fullName = new FullName(firstname, lastname, middlename);

            String position = resultSet.getString("POSITION");
            Date hiredate = resultSet.getDate("HIREDATE");
            BigDecimal salary = resultSet.getBigDecimal("SALARY");

            return (T) new Employee(id, fullName, Position.valueOf(position), hiredate.toLocalDate(), salary);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
