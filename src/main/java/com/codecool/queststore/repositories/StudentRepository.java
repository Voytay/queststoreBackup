package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.StudentMapper;
import com.codecool.queststore.entities.Student;

import java.sql.SQLException;

public class StudentRepository extends AbstractRepository<Student> {
    static {
        ADD_QUERY = "INSERT INTO students(login, exp, wallet_id, class_id) VALUES(?,?,?,?)";
        EDIT_QUERY = "UPDATE students SET login=?, exp=?, wallet_id=?, class_id=? WHERE login=?";
        DELETE_QUERY = "DELETE * FROM students WHERE login=?";
        EDIT_QUERY_KEY_INDEX = 5;
    }

    public StudentRepository() throws PersistenceLayerException {
        super.mapper = new StudentMapper();
    }

    @Override
    void fillStatementWithColumnsData(Student entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getUserData().getLogin());
        super.preparedStatement.setInt(2, entity.getExperience());
        super.preparedStatement.setInt(3, entity.getWallet().getId());
        super.preparedStatement.setInt(4, entity.getCodecoolClass().getId());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Student entity) throws SQLException {
        super.preparedStatement.setString(queryKeyIndex, entity.getUserData().getLogin());
    }
}
