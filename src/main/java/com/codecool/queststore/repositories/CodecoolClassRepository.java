package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.CodecoolClassMapper;
import com.codecool.queststore.entities.CodecoolClass;

import java.sql.SQLException;

public class CodecoolClassRepository extends AbstractRepository<CodecoolClass> {
    static {
        ADD_QUERY = "INSERT INTO classes(name) VALUES(?)";
        EDIT_QUERY = "UPDATE classes SET name=? WHERE id=?";
        DELETE_QUERY = "DELETE * FROM classes WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 0;
    }

    public CodecoolClassRepository() throws PersistenceLayerException {
        super.mapper = new CodecoolClassMapper();
    }

    @Override
    void fillStatementWithColumnsData(CodecoolClass entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, CodecoolClass entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}