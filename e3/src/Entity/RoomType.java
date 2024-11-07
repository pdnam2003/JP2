package Entity;

public enum RoomType {
    SINGLE("Single"),DOUBLE("Double"),QUEEN("Queen"),QUAD("Quad"),TRIPLE("Triple");
    private String RoomTypeLabel;
    RoomType(String roomTypeLabel) {
        this.RoomTypeLabel = roomTypeLabel;
    }

    public String getRoomTypeLabel() {
        return RoomTypeLabel;
    }

    public void setRoomTypeLabel(String roomTypeLabel) {
        RoomTypeLabel = roomTypeLabel;
    }
}
