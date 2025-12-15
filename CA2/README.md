# CA2 Struts2 E-commerce Auction System

## Technical Specifications Met ✅

This web-based application successfully implements all the required functionality:

### ✅ **Technical Requirements**
- **Web-based application**: Runs on Tomcat web server with JSP frontend
- **MVC Framework**: Uses Struts2 Java web framework 
- **Database**: MySQL relational database for data storage
- **Session Management**: Full session handling for user authentication

### ✅ **Required System Functionality**

1. **User Registration** - Complete registration system with form validation
2. **User Login** - Session-based login with authentication
3. **User Display** - "Logged in as [Username]" shown on all pages (see dashboard.jsp)
4. **User Logoff** - Session cleanup and logout functionality
5. **View My Profile** - User can view and edit their profile information
6. **View Other's Profile** - View profiles of other users
7. **View All Users** - Display all registered users
8. **Add Item for Sale** - Create new auction items with details
9. **View All Items** - Browse all available auction items
10. **Make a Bid** - Place bids on auction items
11. **View My Bids** - See all bids placed by current user
12. **View All Bids on an Item** - See all bids for specific items

## Project Structure

```
CA2/
├── src/                          # Java source files
│   ├── Member.java              # User/Member model
│   ├── Product.java             # Auction item model
│   ├── BidInfo.java             # Bid information model
│   ├── DatabaseManager.java     # Database connection manager
│   ├── MemberDAO.java           # Member data access object
│   ├── ProductDAO.java          # Product data access object
│   ├── LoginAction.java         # Login functionality
│   ├── RegisterAction.java      # Registration functionality
│   ├── LogoutAction.java        # Logout functionality
│   ├── ProfileAction.java       # Profile management
│   ├── AddItemAction.java       # Add new auction items
│   ├── ViewItemsAction.java     # View all items
│   ├── MyBidsAction.java        # Bidding functionality
│   ├── ViewItemBidsAction.java  # View bids on items
│   ├── ViewUsersAction.java     # View all users
│   ├── ViewOtherProfileAction.java # View other user profiles
│   ├── AdminAction.java         # Admin functionality
│   └── LoginCheckInterceptor.java # Session authentication
├── webapps/                     # Web application files
│   ├── *.jsp                   # JSP view files
│   └── WEB-INF/
│       ├── web.xml             # Web application configuration
│       ├── struts.xml          # Struts2 configuration
│       ├── classes/            # Compiled Java classes
│       └── lib/                # Required JAR dependencies
├── database_setup.sql          # MySQL database schema
├── build.bat                   # Compilation script
└── ca2.war                     # Deployable web archive
```

## Database Setup

1. **Install MySQL** and start the MySQL service
2. **Run the database setup**:
   ```sql
   mysql -u root -p < database_setup.sql
   ```
3. **Database created**: `ecommerce_ca2`
4. **Tables created**: `members`, `products`, `bidding_history`
5. **Test user**: username: `testuser`, password: `password`

## Compilation & Deployment

### Method 1: Using Build Script (Recommended)
```batch
cd CA2
build.bat
```

### Method 2: Manual Compilation
```batch
cd CA2
javac -cp ".;webapps\WEB-INF\lib\*" -d webapps\WEB-INF\classes src\*.java
jar -cvf ca2.war -C webapps .
```

### Deploy to Tomcat
1. Copy `ca2.war` to Tomcat's `webapps` directory
2. Start Tomcat server
3. Access application at: `http://localhost:8080/ca2/`

## Key Features Implemented

### Session Management 
- User login creates session with user information
- Session displays "Logged in as [Name]" on all pages
- LoginCheckInterceptor ensures authentication for secure actions

### MVC Architecture (Struts2)
- **Model**: Member, Product, BidInfo classes
- **View**: JSP files with Struts2 tags
- **Controller**: Action classes handle business logic

### Database Integration
- MySQL database with proper relationships
- DAO pattern for data access
- Connection pooling through DatabaseManager

### Security
- Session-based authentication
- Input validation and error handling
- SQL injection prevention through prepared statements

## Application Flow

1. **Entry Point**: `index.jsp` → Login/Register
2. **Authentication**: LoginAction validates credentials, creates session
3. **Dashboard**: Main navigation hub showing logged-in user
4. **Features**: All functionality accessible through navigation menu
5. **Logout**: Proper session cleanup

## Testing

- **Test user**: `testuser` / `password`
- **Database test**: TestAction and TestDAO for connectivity
- **All pages**: Include session user display and navigation

## Dependencies (Included)

- Struts2 Core 2.0.11
- MySQL Connector/J 8.0.31
- JSTL, Servlet API, OGNL, Freemarker
- Commons Logging

## Successfully Compiled ✅

All Java files compile without errors and the WAR file is ready for deployment to Tomcat.