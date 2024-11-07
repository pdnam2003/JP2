package Service;

import Entity.Booking;
import Entity.Room;
import Entity.RoomType;

import java.util.List;

public class RoomService {
    private final List<Room> room;
    private final  List<Booking> booking;

    public RoomService(List<Room> room, List<Booking> booking) {
        this.room = room;
        this.booking = booking;
    }
    public List<RoomType> getRoomTypes()  {
        return List.of(RoomType.values());
    }

}
