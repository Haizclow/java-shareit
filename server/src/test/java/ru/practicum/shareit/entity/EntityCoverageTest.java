package ru.practicum.shareit.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class EntityCoverageTest {

    @Test
    void userEntityFullCoverage() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@test.com");

        assertEquals(1L, user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("test@test.com", user.getEmail());

        assertTrue(user.toString().contains("id=1"));
        assertTrue(user.toString().contains("name=Test User"));
        assertTrue(user.toString().contains("email=test@test.com"));

        // Test equals and hashCode
        User user2 = new User();
        user2.setId(1L);
        user2.setName("Different Name");
        user2.setEmail("different@test.com");

        User user3 = new User();
        user3.setId(2L);
        user3.setName("Test User");
        user3.setEmail("test@test.com");

        assertEquals(user, user2); // same id
        assertNotEquals(user, user3); // different id
        assertEquals(user.hashCode(), user2.hashCode());
        assertNotEquals(user, null);
        assertNotEquals(user, new Object());
        assertEquals(user, user); // reflexive
    }

    @Test
    void itemEntityFullCoverage() {
        User owner = new User();
        owner.setId(1L);
        owner.setName("Owner");

        ItemRequest request = new ItemRequest();
        request.setId(2L);

        Item item = new Item();
        item.setId(3L);
        item.setName("Drill");
        item.setDescription("Powerful drill");
        item.setAvailable(true);
        item.setOwner(owner);
        item.setRequest(request);

        assertEquals(3L, item.getId());
        assertEquals("Drill", item.getName());
        assertEquals("Powerful drill", item.getDescription());
        assertTrue(item.getAvailable());
        assertEquals(owner, item.getOwner());
        assertEquals(request, item.getRequest());

        // Test equals, hashCode and toString
        Item item2 = new Item();
        item2.setId(3L);

        Item item3 = new Item();
        item3.setId(4L);

        assertEquals(item, item2);
        assertNotEquals(item, item3);
        assertEquals(item.hashCode(), item2.hashCode());
        assertNotNull(item.toString());
        assertTrue(item.toString().contains("id=3"));
        assertNotEquals(item, null);
        assertNotEquals(item, new Object());
        assertEquals(item, item);
    }

    @Test
    void bookingEntityFullCoverage() {
        User booker = new User();
        booker.setId(1L);

        Item item = new Item();
        item.setId(2L);

        Booking booking = new Booking();
        booking.setId(3L);
        booking.setStart(LocalDateTime.of(2024, 1, 1, 10, 0));
        booking.setEnd(LocalDateTime.of(2024, 1, 2, 10, 0));
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStatus(BookingStatus.WAITING);

        assertEquals(3L, booking.getId());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), booking.getStart());
        assertEquals(LocalDateTime.of(2024, 1, 2, 10, 0), booking.getEnd());
        assertEquals(item, booking.getItem());
        assertEquals(booker, booking.getBooker());
        assertEquals(BookingStatus.WAITING, booking.getStatus());

        // Test equals, hashCode and toString
        Booking booking2 = new Booking();
        booking2.setId(3L);

        Booking booking3 = new Booking();
        booking3.setId(4L);

        assertEquals(booking, booking2);
        assertNotEquals(booking, booking3);
        assertEquals(booking.hashCode(), booking2.hashCode());
        assertNotNull(booking.toString());
        assertTrue(booking.toString().contains("id=3"));
        assertNotEquals(booking, null);
        assertNotEquals(booking, new Object());
        assertEquals(booking, booking);
    }

    @Test
    void commentEntityFullCoverage() {
        User author = new User();
        author.setId(1L);

        Item item = new Item();
        item.setId(2L);

        Comment comment = new Comment();
        comment.setId(3L);
        comment.setText("Great item!");
        comment.setItem(item);
        comment.setAuthor(author);
        comment.setCreated(LocalDateTime.of(2024, 1, 1, 12, 0));

        assertEquals(3L, comment.getId());
        assertEquals("Great item!", comment.getText());
        assertEquals(item, comment.getItem());
        assertEquals(author, comment.getAuthor());
        assertEquals(LocalDateTime.of(2024, 1, 1, 12, 0), comment.getCreated());

        // Test equals, hashCode and toString
        Comment comment2 = new Comment();
        comment2.setId(3L);

        Comment comment3 = new Comment();
        comment3.setId(4L);

        assertEquals(comment, comment2);
        assertNotEquals(comment, comment3);
        assertEquals(comment.hashCode(), comment2.hashCode());
        assertNotNull(comment.toString());
        assertTrue(comment.toString().contains("id=3"));
        assertNotEquals(comment, null);
        assertNotEquals(comment, new Object());
        assertEquals(comment, comment);
    }

    @Test
    void itemRequestEntityFullCoverage() {
        User requester = new User();
        requester.setId(1L);

        ItemRequest request = new ItemRequest();
        request.setId(2L);
        request.setDescription("Need a drill");
        request.setRequester(requester);
        request.setCreated(LocalDateTime.of(2024, 1, 1, 11, 0));

        assertEquals(2L, request.getId());
        assertEquals("Need a drill", request.getDescription());
        assertEquals(requester, request.getRequester());
        assertEquals(LocalDateTime.of(2024, 1, 1, 11, 0), request.getCreated());

        // Test equals, hashCode and toString
        ItemRequest request2 = new ItemRequest();
        request2.setId(2L);

        ItemRequest request3 = new ItemRequest();
        request3.setId(3L);

        assertEquals(request, request2);
        assertNotEquals(request, request3);
        assertEquals(request.hashCode(), request2.hashCode());
        assertNotNull(request.toString());
        assertTrue(request.toString().contains("id=2"));
        assertNotEquals(request, null);
        assertNotEquals(request, new Object());
        assertEquals(request, request);
    }

    @Test
    void entityNullFields() {
        // Проверяем работу с null полями (description в Item может быть null)
        User owner = new User();
        owner.setId(1L);

        Item item = new Item();
        item.setId(2L);
        item.setName("Drill");
        item.setOwner(owner);

        assertNull(item.getDescription());
        assertNull(item.getRequest());
    }

    @Test
    void entityToStringCoverage() {
        User user = new User();
        user.setName("Test");

        assertNotNull(user.toString());

        Item item = new Item();
        item.setName("Test Item");

        assertNotNull(item.toString());
    }
}