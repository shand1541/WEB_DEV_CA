import java.math.BigDecimal;
import java.sql.Timestamp;

public class Bid {
    private int bidId;
    private int productId;
    private int memberId;
    private BigDecimal bidValue;
    private Timestamp bidTimestamp;
    private String bidStatus;
    
    // Additional display fields
    private String productName;
    private String memberName;
    
    // Default constructor
    public Bid() {
        this.bidStatus = "active";
    }
    
    // Parameterized constructor
    public Bid(int productId, int memberId, BigDecimal bidValue) {
        this.productId = productId;
        this.memberId = memberId;
        this.bidValue = bidValue;
        this.bidStatus = "active";
    }
    
    // Getters and Setters
    public int getBidId() { return bidId; }
    public void setBidId(int bidId) { this.bidId = bidId; }
    
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    
    public BigDecimal getBidValue() { return bidValue; }
    public void setBidValue(BigDecimal bidValue) { this.bidValue = bidValue; }
    
    public Timestamp getBidTimestamp() { return bidTimestamp; }
    public void setBidTimestamp(Timestamp bidTimestamp) { this.bidTimestamp = bidTimestamp; }
    
    public String getBidStatus() { return bidStatus; }
    public void setBidStatus(String bidStatus) { this.bidStatus = bidStatus; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    
    @Override
    public String toString() {
        return "Bid{" +
                "bidId=" + bidId +
                ", productId=" + productId +
                ", memberId=" + memberId +
                ", bidValue=" + bidValue +
                ", bidTimestamp=" + bidTimestamp +
                ", bidStatus='" + bidStatus + '\'' +
                '}';
    }
}