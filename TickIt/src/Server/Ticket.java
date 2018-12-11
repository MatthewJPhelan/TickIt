package Server;
import java.math.BigDecimal;

public class Ticket {
	
	int listingId;
	int seller;
	int buyer;
	String eventName;
	String eventDate;
	String eventVenue;
	String eventCity;
	String eventPostcode;
	String listingStart;
	String listingEnd;
	String highestBid;
	int buyerId;
	boolean emailSent;
	
	public Ticket(int listingId, int seller, String eventName, String eventDate, String eventVenue,
			String eventCity, String eventPostcode, String listingStart, String listingEnd, String highestBid,
			int buyerId, boolean emailSent) {
		super();
		this.listingId = listingId;
		this.seller = seller;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventVenue = eventVenue;
		this.eventCity = eventCity;
		this.eventPostcode = eventPostcode;
		this.listingStart = listingStart;
		this.listingEnd = listingEnd;
		this.highestBid = highestBid;
		this.buyerId = buyerId;
		this.emailSent = emailSent;
	}

	public int getListingId() {
		return listingId;
	}

	public int getSeller() {
		return seller;
	}

	public int getBuyer() {
		return buyer;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getEventVenue() {
		return eventVenue;
	}

	public String getEventCity() {
		return eventCity;
	}

	public String getEventPostcode() {
		return eventPostcode;
	}

	public String getListingStart() {
		return listingStart;
	}

	public String getListingEnd() {
		return listingEnd;
	}

	public String getHighestBid() {
		return highestBid;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public boolean isEmailSent() {
		return emailSent;
	}
}
