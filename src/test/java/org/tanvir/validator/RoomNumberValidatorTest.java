package org.tanvir.validator;

import org.tanvir.model.Room;
import org.tanvir.service.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author tanvirtareq
 * @since 8/14/23
 */
@ExtendWith(MockitoExtension.class)
class RoomNumberValidatorTest {

    @Mock
    private RoomService roomService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateRoomNumbers() {
    }

    @Test
    void validateRoomNumber() {
    }

    @Test
    void roomNumberExists() {
        Long roomId = 6L;
        String roomNumber = "401";
        Room room = roomService.findById(roomId);

    }
}