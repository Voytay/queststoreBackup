package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Mentor;
import com.codecool.queststore.entities.UserData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MentorMapperMapToJsonTest {

    private static MentorMapper mentorMapper;
    private static Mentor mentorTest;

    @BeforeAll
    public static void setUp() {
        mentorMapper = new MentorMapper();
        UserData mentorUserData = new UserData("mentorTest");
        mentorTest = new Mentor(mentorUserData);
    }

    @Test
    @DisplayName("Check data after using mapToJson method with one mentor")
    public void testMapToJsonSendOneMentor() {
        String expected = "{\"login\": \"mentorTest\", \"first_name\": \"null\", \"last_name\": \"null\", " +
                "\"email\": \"null\", \"classes\": []}";

        String methodResult = mentorMapper.mapToJson(mentorTest);
        assertEquals(expected, methodResult);
    }

    @Test
    @DisplayName("Send null to mapToJson method in MentorMapper class")
    public void testMapToJsonSendNull() {
        Mentor nullMentor = null;
        assertThrows(IllegalArgumentException.class, () -> {
            mentorMapper.mapToJson(nullMentor);
        });
    }

    @Test
    @DisplayName("Check data after using mapToJson method with list of mentors")
    public void testMapToJsonSendListMentors() {
        UserData mentorUserData2 = new UserData("mentorTest2");
        Mentor mentorTest2 = new Mentor(mentorUserData2);
        List<Mentor> list = new ArrayList<>();
        list.add(mentorTest);
        list.add(mentorTest2);

        String expected = "{\"mentors\": [{\"login\": \"mentorTest\", \"first_name\": \"null\", \"last_name\": " +
                "\"null\", \"email\": \"null\", \"classes\": []},"+"{\"login\": \"mentorTest2\", \"first_name\": " +
                "\"null\", \"last_name\": \"null\", \"email\": \"null\", \"classes\": []}]}";

        String methodResult = mentorMapper.mapToJson(list);
        assertEquals(expected, methodResult);
    }

    @Test
    @DisplayName("Send null instead of list to mapToJson method in MentorMapper class")
    public void testMapToJsonSendNullAsList() {
        List<Mentor> listNullMentor = null;
        assertThrows(IllegalArgumentException.class, () -> {
            mentorMapper.mapToJson(listNullMentor);
        });
    }
}