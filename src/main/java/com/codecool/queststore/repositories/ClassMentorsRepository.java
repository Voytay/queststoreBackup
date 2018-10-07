package com.codecool.queststore.repositories;

import com.codecool.queststore.entities.ClassMentors;
import com.codecool.queststore.mappers.ClassMentorsMapper;

import java.sql.SQLException;

public class ClassMentorsRepository extends AbstractRepository<ClassMentors> {

    static {
        ADD_QUERY = "INSERT INTO class_mentors(class_id, mentor_id) VALUES(?,?)";
        EDIT_QUERY = "UPDATE class_mentors SET class_id=?, mentor_id=? WHERE class_id=? AND mentor_id=?";
        DELETE_QUERY = "DELETE FROM class_mentors * WHERE class_id=? AND mentor_id=?";
        EDIT_QUERY_KEY_INDEX = 3;
    }

    public ClassMentorsRepository() throws PersistenceLayerException {
        super.mapper = new ClassMentorsMapper();
    }

    @Override
    void fillStatementWithColumnsData(ClassMentors entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getClassId());
        super.preparedStatement.setInt(2, entity.getMentorId());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, ClassMentors entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getClassId());
        super.preparedStatement.setInt(queryKeyIndex+1, entity.getMentorId());
    }
}
