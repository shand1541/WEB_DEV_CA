import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO for member 
public class MemberDAO {
    
    // Register new user
    public boolean registerMember(Member member) {
        String sql = "INSERT INTO members (login_name, email_address, password_hash, display_name, contact_number, postal_address) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, member.getLoginName());
            stmt.setString(2, member.getEmailAddress());
            stmt.setString(3, member.getPasswordHash());
            stmt.setString(4, member.getDisplayName());
            stmt.setString(5, member.getContactNumber());
            stmt.setString(6, member.getPostalAddress());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error registering member: " + e.getMessage());
            return false;
        }
    }
    
    // Check login 
    public Member loginMember(String username, String password) {
        String sql = "SELECT * FROM members WHERE login_name = ? AND password_hash = ? AND account_status = 'active'";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setLoginName(rs.getString("login_name"));
                member.setEmailAddress(rs.getString("email_address"));
                member.setDisplayName(rs.getString("display_name"));
                member.setContactNumber(rs.getString("contact_number"));
                member.setPostalAddress(rs.getString("postal_address"));
                member.setRegistrationDate(rs.getTimestamp("registration_date"));
                return member;
            }
            
        } catch (SQLException e) {
            System.err.println("Error logging in member: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Check if username already exists
     */
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM members WHERE login_name = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return false;
    }
    
    // View all members
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE account_status = 'active' ORDER BY display_name";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setLoginName(rs.getString("login_name"));
                member.setEmailAddress(rs.getString("email_address"));
                member.setDisplayName(rs.getString("display_name"));
                member.setContactNumber(rs.getString("contact_number"));
                member.setPostalAddress(rs.getString("postal_address"));
                member.setRegistrationDate(rs.getTimestamp("registration_date"));
                members.add(member);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all members: " + e.getMessage());
        }
        return members;
    }
    // View member by ID
    public Member getMemberById(int memberId) {
        String sql = "SELECT * FROM members WHERE member_id = ? AND account_status = 'active'";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setLoginName(rs.getString("login_name"));
                member.setEmailAddress(rs.getString("email_address"));
                member.setDisplayName(rs.getString("display_name"));
                member.setContactNumber(rs.getString("contact_number"));
                member.setPostalAddress(rs.getString("postal_address"));
                member.setRegistrationDate(rs.getTimestamp("registration_date"));
                return member;
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting member by ID: " + e.getMessage());
        }
        return null;
    }
}
