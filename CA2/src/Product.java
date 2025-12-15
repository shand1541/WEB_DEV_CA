import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private int productId;
    private String productName;
    private String productDetails;
    private String productCategory;
    private BigDecimal minimumBid;
    private BigDecimal highestBid;
    private int ownerId;
    private String listingStatus;
    private Timestamp listingDate;
    
    // Default constructor
    public Product() {
        this.listingStatus = "active";
        this.highestBid = BigDecimal.ZERO;
    }
    
    // Parameterized constructor
    public Product(String productName, String productDetails, String productCategory, 
                  BigDecimal minimumBid, int ownerId) {
        this.productName = productName;
        this.productDetails = productDetails;
        this.productCategory = productCategory;
        this.minimumBid = minimumBid;
        this.ownerId = ownerId;
        this.listingStatus = "active";
        this.highestBid = minimumBid;
    }
    
    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getProductDetails() { return productDetails; }
    public void setProductDetails(String productDetails) { this.productDetails = productDetails; }
    
    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
    
    public BigDecimal getMinimumBid() { return minimumBid; }
    public void setMinimumBid(BigDecimal minimumBid) { this.minimumBid = minimumBid; }
    
    public BigDecimal getHighestBid() { return highestBid; }
    public void setHighestBid(BigDecimal highestBid) { this.highestBid = highestBid; }
    
    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
    
    public String getListingStatus() { return listingStatus; }
    public void setListingStatus(String listingStatus) { this.listingStatus = listingStatus; }
    
    public Timestamp getListingDate() { return listingDate; }
    public void setListingDate(Timestamp listingDate) { this.listingDate = listingDate; }
    
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", minimumBid=" + minimumBid +
                ", highestBid=" + highestBid +
                ", listingStatus='" + listingStatus + '\'' +
                '}';
    }
}