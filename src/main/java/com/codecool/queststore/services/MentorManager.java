package com.codecool.queststore.services;

import com.codecool.queststore.criteria.AllMentors;
import com.codecool.queststore.criteria.CodecoolClassByName;
import com.codecool.queststore.criteria.MentorByLogin;
import com.codecool.queststore.criteria.RoleByName;

import com.codecool.queststore.entities.CodecoolClass;
import com.codecool.queststore.entities.Mentor;
import com.codecool.queststore.entities.Role;
import com.codecool.queststore.entities.UserData;

import com.codecool.queststore.repositories.*;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class MentorManager {
    private static final RepositoryPool repositoryPool;

    static {
        repositoryPool = RepositoryPool.getInstance();
    }

    public List<Mentor> get(String login) throws ServiceLayerException {
        try {
            Repository<Mentor> mentorRepository = (MentorRepository) repositoryPool
                    .getRepository(Repositories.MENTOR);
            return mentorRepository.query(new MentorByLogin(login));
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get mentor %s: %s", login, e.getMessage()));
        }
    }

    public List<Mentor> getAll() throws ServiceLayerException {
        try {
            Repository<Mentor> mentorRepository = (MentorRepository) repositoryPool
                    .getRepository(Repositories.MENTOR);
            return mentorRepository.query(new AllMentors());
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get all mentors: %s", e.getMessage()));
        }
    }

    public void create(String login, String firstName, String lastName, String email,
                       String password, Map<String, String> classes) throws ServiceLayerException {

        try {
            Repository<Role> roleRepository = (RoleRepository) repositoryPool
                    .getRepository(Repositories.ROLE);
            Role mentorRole = roleRepository.query(new RoleByName("mentor")).get(0);

            UserData userData = new UserData(login, firstName, lastName, email, mentorRole, password);

            Mentor mentor = new Mentor(userData, parseCodecoolClasses(classes));

            Repository<Mentor> mentorRepository = (MentorRepository) repositoryPool
                    .getRepository(Repositories.MENTOR);
            mentorRepository.add(mentor);
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException("Can't create mentor: " + e.getMessage());
        }
    }

    public void edit(String login, String firstName, String lastName, String email,
                     String password, Map<String, String> classes) throws ServiceLayerException {

        try {
            Repository<Mentor> mentorRepository = (MentorRepository) repositoryPool
                    .getRepository(Repositories.MENTOR);

            Mentor mentor = this.get(login).get(0);
            mentor.getUserData().setLogin(login);
            mentor.getUserData().setFirstName(firstName);
            mentor.getUserData().setLastName(lastName);
            mentor.getUserData().setEmail(email);
            mentor.getUserData().setPassword(password);

            mentor.setClasses(parseCodecoolClasses(classes));

            mentorRepository.update(mentor);
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't edit %s: %s", login, e.getMessage()));
        }
    }

    public void remove(String login) throws ServiceLayerException {
        try {
            Repository<Mentor> mentorRepository = (MentorRepository) repositoryPool
                    .getRepository(Repositories.MENTOR);

            Mentor mentor = this.get(login).get(0);
            mentorRepository.delete(mentor);
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't remove %s: %s", login, e.getMessage()));
        }
    }

    private List<CodecoolClass> parseCodecoolClasses(Map<String, String> classes) throws PersistenceLayerException {
        Repository<CodecoolClass> codecoolClassRepository;
        codecoolClassRepository = (CodecoolClassRepository) repositoryPool
                .getRepository(Repositories.CODECOOL_CLASS);

        List<CodecoolClass> codecoolClasses = new ArrayList<>();
        for (String codecoolClass : classes.keySet()) {
            codecoolClasses.add(codecoolClassRepository.query(new CodecoolClassByName(codecoolClass)).get(0));
        }

        return codecoolClasses;
    }
}
