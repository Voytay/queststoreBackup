package com.codecool.queststore.repositories;

import java.util.Map;
import java.util.HashMap;

public class RepositoryPool {
    private static RepositoryPool instance;
    private final Map<Repositories, Repository> repositories;

    private RepositoryPool() {
        this.repositories = new HashMap<>();
    }

    public static RepositoryPool getInstance() {
        if (instance == null) {
            instance = new RepositoryPool();
        }

        return instance;
    }

    public Repository getRepository(Repositories repositoryType) throws PersistenceLayerException {
        if (!this.repositories.containsKey(repositoryType)) {
            this.repositories.put(repositoryType, RepositoryFactory.makeRepository(repositoryType));
        }

        return this.repositories.get(repositoryType);
    }

    private static class RepositoryFactory {
        public static Repository makeRepository(Repositories repositoryType) throws PersistenceLayerException {
            switch (repositoryType) {
                case USER_DATA:
                    return new UserDataRepository();
                case ROLE:
                    return new RoleRepository();
                case PRIVILEGE:
                    return new PrivilegeRepository();
                case ADMIN:
                    return new AdminRepository();
                case MENTOR:
                    return new MentorRepository();
                case STUDENT:
                    return new StudentRepository();
                case ARTIFACT:
                    return new ArtifactRepository();
                case QUEST:
                    return new QuestRepository();
                case WALLET:
                    return new WalletRepository();
                case LEVEL:
                    return new LevelRepository();
                case CODECOOL_CLASS:
                    return new CodecoolClassRepository();
                case CLASS_MENTORS:
                    return new ClassMentorsRepository();
                case WALLET_ARTIFACTS:
                    return new WalletArtifactsRepository();
                default:
                    throw new PersistenceLayerException("There's no such a repository: " + repositoryType.toString());
            }
        }
    }
}
