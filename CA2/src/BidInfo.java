import java.util.Date;

public class BidInfo {
    private String itemName;
    private double bidAmount;
    private double currentHigh;
    private String status;
    private Date timestamp;
    
    public BidInfo() {
        // Default constructor
    }
    
    public BidInfo(String itemName, double bidAmount, double currentHigh, String status) {
        this.itemName = itemName;
        this.bidAmount = bidAmount;
        this.currentHigh = currentHigh;
        this.status = status;
        this.timestamp = new Date();
    }
    
    // Getters and Setters
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public double getBidAmount() {
        return bidAmount;
    }
    
    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }
    
    public double getCurrentHigh() {
        return currentHigh;
    }
    
    public void setCurrentHigh(double currentHigh) {
        this.currentHigh = currentHigh;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}