package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Artifact;
import com.codecool.queststore.entities.Mentor;
import com.codecool.queststore.entities.UserData;
import com.codecool.queststore.repositories.PersistenceLayerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MentorMapperMapTest {

    private static MentorMapper mentorMapper;

    @BeforeAll
    public static void setUp() {
        mentorMapper = new MentorMapper();
    }

    @Test
    @DisplayName("Send null to map method in MentorMapper class")
    public void testMapSendNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            mentorMapper.map(null);
        });
    }

    @Test
    @DisplayName("Test map method in MentorMapper - base usecase")
    public void testMap() throws SQLException, PersistenceLayerException {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("login")).thenReturn("mentorTest");

        MentorMapper mentorMapper = spy(new MentorMapper());

        doReturn(new UserData("mentorTest"))
                .when(mentorMapper).getMentorData("mentorTest");
        doReturn(new ArrayList<Artifact>())
                .when(mentorMapper).getMentorClasses("mentorTest");
        Mentor mentor = mentorMapper.map(resultSetMock);

        assertEquals("mentorTest", mentor.getUserData().getLogin());
    }
}
