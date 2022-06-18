public class ReservationAide {
    private String GNameRA;
    private String GNumberRA;
    private String GContactRA;
    private String RoomNoRA;
    private String BookingDate;

    public ReservationAide(String GNameRA, String GNumberRA, String GContactRA, String roomNoRA,String BookingDate) {
        this.GNameRA = GNameRA;
        this.GNumberRA = GNumberRA;
        this.GContactRA = GContactRA;
        RoomNoRA = roomNoRA;
        this.BookingDate=BookingDate;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getGNameRA() {
        return GNameRA;
    }

    public void setGNameRA(String GNameRA) {
        this.GNameRA = GNameRA;
    }

    public String getGNumberRA() {
        return GNumberRA;
    }

    public void setGNumberRA(String GNumberRA) {
        this.GNumberRA = GNumberRA;
    }

    public String getGContactRA() {
        return GContactRA;
    }

    public void setGContactRA(String GContactRA) {
        this.GContactRA = GContactRA;
    }

    public String getRoomNoRA() {
        return RoomNoRA;
    }

    public void setRoomNoRA(String roomNoRA) {
        RoomNoRA = roomNoRA;
    }
}
