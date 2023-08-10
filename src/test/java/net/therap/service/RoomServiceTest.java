package net.therap.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author tanvirtareq
 * @since 8/10/23
 */
class RoomServiceTest {

    RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService();
    }

    @Test
    void isOverlap() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        LocalDate startDate1 = LocalDate.parse("2/01/2000", formatter);
        LocalDate endDate1 = LocalDate.parse("5/01/2000", formatter);

        LocalDate startDate2 = LocalDate.parse("1/01/2000", formatter);
        LocalDate endDate2 = LocalDate.parse("8/01/2000", formatter);

        assertTrue(roomService.isOverlap(startDate1, endDate1, startDate2, endDate2));
    }
}