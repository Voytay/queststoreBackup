package com.codecool.queststore.mappers;

import com.codecool.queststore.criteria.*;
import com.codecool.queststore.entities.UserData;
import com.codecool.queststore.repositories.Repository;
import com.codecool.queststore.repositories.Repositories;
import com.codecool.queststore.repositories.RepositoryPool;

import com.codecool.queststore.entities.Student;
import com.codecool.queststore.entities.CodecoolClass;
import com.codecool.queststore.entities.Wallet;

import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class StudentMapper implements Mapper {
    private static final RepositoryPool REPOSITORY_POOL = RepositoryPool.getInstance();

    @Override
    public Student map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        String login = resultSet.getString("login");
        int experience = resultSet.getInt("exp");

        UserData userData = getStudentData(login);
        Wallet wallet = getStudentWallet(resultSet);

        return new Student(userData, experience, wallet);
    }

    public String mapToJson(Student student) {

        CodecoolClass codecoolClass = student.getCodecoolClass();
        Wallet wallet = student.getWallet();

        WalletMapper walletMapper = new WalletMapper();
        CodecoolClassMapper codecoolClassMapper = new CodecoolClassMapper();

        String codecoolClassJson = codecoolClassMapper.mapToJson(codecoolClass);
        String walletJson = walletMapper.mapToJson(wallet);

        return String.format("{\"login\": \"%s\", \"fistname\": \"%s\", \"lastname\": \"%s\", \"email\": \"%s\", \"class\": \"%s\", \"wallet\": %s}",
                student.getUserData().getLogin(),
                student.getUserData().getFirstName(),
                student.getUserData().getLastName(),
                student.getUserData().getEmail(),
                codecoolClassJson,
                walletJson);
    }

    public String mapToJson(List<Student> students) {
        StringBuilder json = new StringBuilder();

        json.append("{\"students\": [");
        int indexOfLastElement = students.size() - 1;
        for (Student student: students) {

            json.append(mapToJson(student));

            if (students.indexOf(student) != indexOfLastElement) {
                json.append(",");
            }
        }

        json.append("]}");
        return json.toString();
    }

    UserData getStudentData(String login) throws PersistenceLayerException {
        Repository<UserData> userDataRepository = REPOSITORY_POOL.getRepository(Repositories.USER_DATA);
        SqlCriteria getUserDataByLogin = new UserDataByLogin(login);
        UserData userData = userDataRepository.query(getUserDataByLogin).get(0);
        return userData;
    }

    Wallet getStudentWallet(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        Repository<Wallet> walletRepository = REPOSITORY_POOL.getRepository(Repositories.WALLET);
        SqlCriteria getWalletByOwnerLogin = new WalletByOwnerLogin(resultSet.getString("login"));
        Wallet wallet = walletRepository.query(getWalletByOwnerLogin).get(0);
        return wallet;
    }
}
