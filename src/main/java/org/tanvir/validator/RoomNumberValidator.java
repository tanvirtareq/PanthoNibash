package org.tanvir.validator;

import org.tanvir.model.Hotel;
import org.tanvir.model.Room;
import org.tanvir.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author tanvirtareq
 * @since 8/14/23
 */

@Component
public class RoomNumberValidator {

    @Autowired
    private HotelService hotelService;

    public void validateRoomNumbers(Long hotelId, Room room, BindingResult bindingResult) {

        Hotel hotel = hotelService.findById(hotelId);

        room.getRoomNumbers().forEach((roomNumber) -> {
            validateRoomNumber(hotel, roomNumber, bindingResult);
        });
    }

    public void validateRoomNumber(Hotel hotel, String roomNumber, BindingResult bindingResult) {

        if (hotel.getRooms().stream().anyMatch((room) -> roomNumberExists(room, roomNumber))) {
            bindingResult.rejectValue("roomNumbers", "duplicate.roomNumber",
                    "Duplicate room number " + roomNumber);
        }
    }

    public boolean roomNumberExists(Room room, String roomNumber) {
        return room.getRoomNumbers().contains(roomNumber);
    }
}
