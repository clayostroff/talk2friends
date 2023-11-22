package com.example.talk2friends;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeetingManagerTest {

    private MeetingManager meetingManager;

    @Before
    public void setUp() {
        meetingManager = new MeetingManager();
    }

    @Test
    public void testCreateMeeting() {
        // Create a sample meeting
        String meetingLink = "sampleLink";
        String topic = "Sample Topic";
        String time = "Sample Time";
        String location = "Sample Location";

        // Call the createMeeting method
        meetingManager.createMeeting(meetingLink, topic, time, location);

        // Get the list of meetings
        List<MeetingManager.Meeting> meetings = meetingManager.getMeetings();

        // Ensure that the meetings list is not empty
        assertEquals(1, meetings.size());

        // Ensure that the created meeting is in the list
        MeetingManager.Meeting createdMeeting = meetings.get(0);
        assertEquals(meetingLink, createdMeeting.getMeetingLink());
        assertEquals(topic, createdMeeting.getTopic());
        assertEquals(time, createdMeeting.getTime());
        assertEquals(location, createdMeeting.getLocation());
    }
}
