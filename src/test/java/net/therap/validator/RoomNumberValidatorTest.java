package net.therap.validator;

import net.therap.model.Room;
import net.therap.service.HotelService;
import net.therap.service.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

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