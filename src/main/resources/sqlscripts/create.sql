DROP DATABASE IF EXISTS queststore;
CREATE DATABASE queststore;

DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS privileges CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS class_mentors CASCADE;
DROP TABLE IF EXISTS mentors CASCADE;
DROP TABLE IF EXISTS classes CASCADE;
DROP TABLE IF EXISTS levels CASCADE;
DROP TABLE IF EXISTS quests CASCADE;
DROP TABLE IF EXISTS wallets CASCADE;
DROP TABLE IF EXISTS artifacts CASCADE;
DROP TABLE IF EXISTS wallet_artifacts CASCADE;


-- Roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);


-- Privileges
CREATE TABLE privileges (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);


-- Users
CREATE TABLE users (
    login TEXT PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    email TEXT,
    role_id INTEGER REFERENCES roles(id),
    password TEXT
);


-- Admins
CREATE TABLE admins (
    login TEXT PRIMARY KEY REFERENCES users(login)
);


-- Mentors
CREATE TABLE mentors (
    login TEXT PRIMARY KEY REFERENCES users(login)
);


-- Students
CREATE TABLE students (
    login TEXT PRIMARY KEY REFERENCES users(login),
    exp INTEGER NOT NULL DEFAULT 0,
    class_id INTEGER
);


-- Classes
CREATE TABLE classes (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);


-- M-M mentors x classes
CREATE TABLE class_mentors (
    class_id INTEGER REFERENCES classes(id),
    mentor_login TEXT REFERENCES mentors(login),
    PRIMARY KEY(class_id, mentor_login)
);


-- Levels
CREATE TABLE levels (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    threshold INTEGER NOT NULL CHECK (threshold > 0)
);


-- Wallets
CREATE TABLE wallets (
    id SERIAL PRIMARY KEY,
    owner_login TEXT NOT NULL REFERENCES students(login),
    balance INTEGER NOT NULL DEFAULT 0 CHECK (balance >= 0)
);


-- Artifacts
CREATE TABLE artifacts (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    DESCRIPTION TEXT,
    price INTEGER NOT NULL CHECK (price > 0)
);


-- M-M wallet_artifacts
CREATE TABLE wallet_artifacts (
    wallet_id INTEGER NOT NULL REFERENCES wallets(id),
    artifact_id INTEGER NOT NULL REFERENCES artifacts(id),
    quantity INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    PRIMARY KEY(wallet_id, artifact_id)
);


-- Quests
CREATE TABLE quests (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    reward INTEGER NOT NULL CHECK (reward > 0)
);
