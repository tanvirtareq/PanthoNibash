package net.therap.validator;

import net.therap.model.Hotel;
import net.therap.model.Room;
import net.therap.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author tanvirtareq
 * @since 8/14/23
 */

@Component
public class RoomNumberValidator {

    private final Logger LOGGER = Logger.getLogger(RoomNumberValidator.class);

    @Autowired
    private HotelService hotelService;

    public void validateRoomNumbers(Long hotelId, Room room, BindingResult bindingResult) {

        Hotel hotel = hotelService.findById(hotelId);

        LOGGER.info("in method validateRoomNumbers");
        LOGGER.info(hotel);

        room.getRoomNumbers().forEach((roomNumber) -> {
            validateRoomNumber(hotel, roomNumber, bindingResult);
        });
    }

    public void validateRoomNumber(Hotel hotel, String roomNumber, BindingResult bindingResult) {

        LOGGER.info("in method validateRoomNumber");
        LOGGER.info(roomNumber);

        if (hotel.getRooms().stream().anyMatch((room) -> roomNumberExists(room, roomNumber))) {
            bindingResult.rejectValue("roomNumbers", "duplicate.roomNumber",
                    "Duplicate room number " + roomNumber);
        }
    }

    public boolean roomNumberExists(Room room, String roomNumber) {

        LOGGER.info("In method roomNumberExists");
        LOGGER.info(room);
        LOGGER.info(room.getRoomNumbers());
        LOGGER.info(room.getRoomNumbers().contains(roomNumber));

        return room.getRoomNumbers().contains(roomNumber);
    }
}
