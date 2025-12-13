package ru.practicum.shareit.booking;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // ============ BOOKER BOOKINGS ============

    @Query("SELECT b FROM Booking b WHERE b.booker.id = :bookerId ORDER BY b.start DESC")
    List<Booking> findAllByBooker(@Param("bookerId") Long bookerId);

    @Query("""
        SELECT b FROM Booking b 
        WHERE b.booker.id = :bookerId 
          AND b.start < :end 
          AND b.end > :start 
        ORDER BY b.start DESC
    """)
    List<Booking> findCurrentByBooker(@Param("bookerId") Long bookerId,
                                      @Param("start") LocalDateTime start,
                                      @Param("end") LocalDateTime end);

    @Query("""
        SELECT b FROM Booking b 
        WHERE b.booker.id = :bookerId 
          AND b.end < :now 
        ORDER BY b.start DESC
    """)
    List<Booking> findPastByBooker(@Param("bookerId") Long bookerId, @Param("now") LocalDateTime now);

    @Query("""
        SELECT b FROM Booking b 
        WHERE b.booker.id = :bookerId 
          AND b.start > :now 
        ORDER BY b.start DESC
    """)
    List<Booking> findFutureByBooker(@Param("bookerId") Long bookerId, @Param("now") LocalDateTime now);

    @Query("""
        SELECT b FROM Booking b 
        WHERE b.booker.id = :bookerId 
          AND b.status = :status 
        ORDER BY b.start DESC
    """)
    List<Booking> findByBookerAndStatus(@Param("bookerId") Long bookerId,
                                        @Param("status") BookingStatus status);

    boolean existsByItemIdAndBookerIdAndEndBefore(Long itemId, Long bookerId, LocalDateTime end);

    // ============ OWNER BOOKINGS ============

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.owner.id = :ownerId
        ORDER BY b.start DESC
    """)
    List<Booking> findAllByOwner(@Param("ownerId") Long ownerId);

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.owner.id = :ownerId
          AND b.start < :now
          AND b.end > :now
        ORDER BY b.start DESC
    """)
    List<Booking> findCurrentByOwner(@Param("ownerId") Long ownerId, @Param("now") LocalDateTime now);

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.owner.id = :ownerId
          AND b.end < :now
        ORDER BY b.start DESC
    """)
    List<Booking> findPastByOwner(@Param("ownerId") Long ownerId, @Param("now") LocalDateTime now);

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.owner.id = :ownerId
          AND b.start > :now
        ORDER BY b.start DESC
    """)
    List<Booking> findFutureByOwner(@Param("ownerId") Long ownerId, @Param("now") LocalDateTime now);

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.owner.id = :ownerId
          AND b.status = :status
        ORDER BY b.start DESC
    """)
    List<Booking> findByOwnerAndStatus(@Param("ownerId") Long ownerId, @Param("status") BookingStatus status);

    // ============ ITEM BOOKINGS ============

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.id = :itemId
          AND b.start < CURRENT_TIMESTAMP
          AND b.status = :status
        ORDER BY b.start DESC
    """)
    List<Booking> findLastBooking(@Param("itemId") Long itemId,
                                  @Param("status") BookingStatus status,
                                  Pageable pageable);

    @Query("""
        SELECT b FROM Booking b
        WHERE b.item.id = :itemId
          AND b.start > CURRENT_TIMESTAMP
          AND b.status = :status
        ORDER BY b.start ASC
    """)
    List<Booking> findNextBooking(@Param("itemId") Long itemId,
                                  @Param("status") BookingStatus status,
                                  Pageable pageable);
}
