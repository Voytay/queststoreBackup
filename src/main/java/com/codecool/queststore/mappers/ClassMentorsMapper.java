package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.ClassMentors;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassMentorsMapper implements Mapper<ClassMentors> {
    @Override
    public ClassMentors map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int classId = resultSet.getInt("class_id");
        int mentorId = resultSet.getInt("mentor_id");

        return new ClassMentors(classId, mentorId);
    }
}
