package com.codecool.queststore.services;

import com.codecool.queststore.criteria.AllStudents;
import com.codecool.queststore.criteria.RoleByName;
import com.codecool.queststore.criteria.SqlCriteria;
import com.codecool.queststore.criteria.StudentByLogin;
import com.codecool.queststore.entities.Role;
import com.codecool.queststore.entities.Student;
import com.codecool.queststore.entities.UserData;
import com.codecool.queststore.entities.Wallet;
import com.codecool.queststore.repositories.*;

import java.util.List;

public class StudentManager {
    private static final RepositoryPool REPOSITORY_POOL;

    static {
        REPOSITORY_POOL = RepositoryPool.getInstance();
    }

    public List<Student> get(String login) throws ServiceLayerException {
        try {

            Repository<Student> studentRepository = (StudentRepository)REPOSITORY_POOL
                    .getRepository(Repositories.STUDENT);
            return studentRepository.query(new StudentByLogin(login));

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException(String.format("Can't get quest (login: %s): %s", login, e.getMessage()));
            
        }
    }

    public List<Student> getAll() throws ServiceLayerException {

        try {

            Repository<Student> studentRepository = (StudentRepository)REPOSITORY_POOL.getRepository(Repositories.STUDENT);
            List<Student> students = studentRepository.query(new AllStudents());

            return students;

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't get students: " + e.getMessage());
        }
    }

    public void create(String login, String firstName, String lastName, String email, String password)
                                                                            throws ServiceLayerException {
        try {

            SqlCriteria getRoleByName = new RoleByName("student");
            Repository<Role> roleRepository = (RoleRepository)REPOSITORY_POOL.getRepository(Repositories.ROLE);
            Role role = roleRepository.query(getRoleByName).get(0);

            UserData userData = new UserData(login, firstName, lastName, email, role, password);
            Wallet wallet = new Wallet(login);
            Student student = new Student(userData, 0, wallet);

            Repository<Wallet> walletRepository = (WalletRepository)REPOSITORY_POOL.getRepository(Repositories.WALLET);
            walletRepository.add(wallet);

            Repository<UserData> userDataRepository = (UserDataRepository)REPOSITORY_POOL.getRepository(Repositories.USER_DATA);
            userDataRepository.add(userData);

            Repository<Student> studentRepository = (StudentRepository)REPOSITORY_POOL.getRepository(Repositories.STUDENT);
            studentRepository.add(student);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't create student: " + e.getMessage());
        }
    }

    public void edit(String login, String firstName, String lastName, String email, String password)
            throws ServiceLayerException {

        try {

            Repository<Student> studentRepository = (StudentRepository)REPOSITORY_POOL.getRepository(Repositories.STUDENT);
            Student student = this.get(login).get(0);

            student.getUserData().setFirstName(firstName);
            student.getUserData().setLastName(lastName);
            student.getUserData().setEmail(email);
            student.getUserData().setPassword(password);

            studentRepository.update(student);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't edit student: " + e.getMessage());
        }
    }

    public void delete(String login) throws ServiceLayerException {

        try {

            Repository<Student> studentRepository = (StudentRepository)REPOSITORY_POOL.getRepository(Repositories.STUDENT);
            Student student = this.get(login).get(0);
            studentRepository.delete(student);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't remove student: " + e.getMessage());
        }
    }
}
