/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author AHSAN
 */
@SuppressWarnings("unchecked")
public class LibrarySystem extends Application {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/dbtest";
    private static final String username = "root";
    private static final String password = null;

    private static PreparedStatement insertStmt;
    private static PreparedStatement insertStmt1;
    private static PreparedStatement deleteStmt;
    private static PreparedStatement deleteStmt1;
    private static PreparedStatement deleteStmt2;
    private static PreparedStatement insertStmt3;
    private static Connection connection;

    private static ObservableList<Schedule> schedule = FXCollections.observableArrayList();
    private static ObservableList<Books> books = FXCollections.observableArrayList();
    private static ObservableList<Student> students = FXCollections.observableArrayList();
    private static ObservableList<IssuedBooks> issuebooks = FXCollections.observableArrayList();

    private ObservableList dataSchedule;
    private ObservableList dataBooks;
    private ObservableList dataStudents;
    private ObservableList dataIssueBook;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void openConnection() throws SQLException {

        connection = DriverManager.getConnection(url, username, password);
        deleteStmt = connection.prepareStatement("delete from Books where BookID = ?");
        deleteStmt1 = connection.prepareStatement("delete from student where StudentID = ?");
        insertStmt = connection.prepareStatement("insert into student values(?,?,?,?,?,?)");
        insertStmt1 = connection.prepareStatement("insert into Books values(?,?,?,?,?)");
        insertStmt3 = connection.prepareStatement("insert into issuedBooks values(?,?,?,?,?)");
        deleteStmt2 = connection.prepareStatement("delete from issuedBooks where Bookname = ?");
    }

    private static void closeConnection() throws SQLException {

        if (insertStmt != null) {
            insertStmt.close();
            deleteStmt.close();
        }

        connection.close();
    }

    private static void insertData() {
        // Step 1 loading the driver

        // Step 2
        // protocol ip port db Name
        try {

            // Step 3 making a connection
            openConnection();

            // Step 4 : Create a statement
            Statement stmt = connection.createStatement();

            // Step 5: Execute a query
            ResultSet results = stmt.executeQuery("select * from schedule");

            // Step 6: process results
            while (results.next()) {
                schedule.add(new Schedule(results.getString(1), results.getString(2), results.getString(3)));
            }

            // Step 7
            stmt.close();
            closeConnection();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void insertData1() {

        try {

            // Step 3 making a connection
            openConnection();

            // Step 4 : Create a statement
            Statement stmt = connection.createStatement();

            // Step 5: Execute a query
            ResultSet rs = stmt.executeQuery("select * from Books");

            // Step 6: process results
            while (rs.next()) {
                books.add(new Books(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
//			System.out.println(rs.getString("Author"));
            }

            // Step 7
            stmt.close();
            closeConnection();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void insertData2() {
        // Step 1 loading the driver

        // Step 2
        // protocol ip port db Name
        try {

            // Step 3 making a connection
            openConnection();

            // Step 5: Execute a query
            try ( // Step 4 : Create a statement
                    Statement stmt = connection.createStatement()) {
                // Step 5: Execute a query
                ResultSet rs1 = stmt.executeQuery("select * from student");

                // Step 6: process results
                while (rs1.next()) {
                    students.add(new Student(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6)));
//			System.out.println(rs.getString("Author"));
                }

                // Step 7
            }
            closeConnection();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void insertData3() {

        try {

            // Step 3 making a connection
            openConnection();

            // Step 4 : Create a statement
            Statement stmt3 = connection.createStatement();

            // Step 5: Execute a query
            ResultSet rsss = stmt3.executeQuery("select * from issuedBooks");

            // Step 6: process results
            while (rsss.next()) {
                issuebooks.add(new IssuedBooks(rsss.getString(1), rsss.getString(2), rsss.getString(3), rsss.getString(4), rsss.getString(5)));
//			System.out.println(rs.getString("Author"));
            }

            // Step 7
            stmt3.close();
            closeConnection();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        primaryStage.setTitle("LMS Login");
        primaryStage.setMaximized(true);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        BorderPane bp_main = new BorderPane();
        Scene scene_main = new Scene(bp_main);

        bp_main.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_main = new GridPane();
        gp_main.setPadding(new Insets(20, 20, 20, 20));
        gp_main.setHgap(5);
        gp_main.setVgap(5);

        Text txt_main = new Text();
        txt_main.setText("Welcome To Software Engineering Project");
        txt_main.setId("mytext1");

        Hyperlink hpl_terms = new Hyperlink();
        hpl_terms.setText("Terms And Conditions");
        hpl_terms.setId("hyperlink");
        Hyperlink hpl_scope = new Hyperlink();
        hpl_scope.setText("Scope");
        hpl_scope.setId("hyperlink");

        gp_main.add(txt_main, 6, 2);
        gp_main.add(hpl_terms, 2, 4);
        gp_main.add(hpl_scope, 2, 5);

        bp_main.prefHeightProperty().bind(scene_main.heightProperty());
        bp_main.prefWidthProperty().bind(scene_main.widthProperty());
        bp_main.setCenter(gp_main);

        BorderPane bp_terms = new BorderPane();
        Scene scene_terms = new Scene(bp_terms);

        bp_main.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_terms = new GridPane();
        gp_terms.setPadding(new Insets(20, 20, 20, 20));
        gp_terms.setHgap(5);
        gp_terms.setVgap(5);

        //Text txt_termsHead = new Text();
        //txt_termsHead.setText("Terms And Condition");
        //txt_termsHead.setId("mytext1");
        Text txt_terms = new Text();
        txt_terms.setText("* Project should not be copied\n"
                + "* Project terminates abnormally you will get zero\n"
                + "* Project should hold 3 pillers of OOP\n"
                + "* Project should lay under the umberella of SDLC\n"
                + "* Interface should be catchy\n"
                + "* Report and CD is must\n"
                + "* Deliverables should be submitted on same date and time\n"
                + "* Late submission will be evaluated from 50%\n"
                + "xx THESE TERMS AND CONDITIONS CAN BE UPDATED\n"
                + "WITHOUT ANY PRIOR NOTICE TO ME xx");
        txt_terms.setId("mytext");
        CheckBox chkbox_agree = new CheckBox("I Agree To These Terms And Conditions");
        Button btn_termsNext = new Button("Next");
        btn_termsNext.setMaxSize(100, 100);
        Button btn_termsBack = new Button("Back");
        btn_termsBack.setMaxSize(100, 100);

        // gp_terms.add(txt_termsHead, 4, 2);
        gp_terms.add(txt_terms, 2, 3);
        gp_terms.add(chkbox_agree, 2, 7);
        gp_terms.add(btn_termsNext, 3, 7);
        gp_terms.add(btn_termsBack, 4, 7);

        bp_terms.prefHeightProperty().bind(scene_terms.heightProperty());
        bp_terms.prefWidthProperty().bind(scene_terms.widthProperty());
        bp_terms.setCenter(gp_terms);

        hpl_terms.setOnAction(x -> {

            primaryStage.setScene(scene_terms);
        });
        btn_termsBack.setOnAction(x -> {

            primaryStage.setScene(scene_main);
        });

        BorderPane bp_scope = new BorderPane();
        Scene scene_scope = new Scene(bp_scope);

        bp_scope.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_scope = new GridPane();
        gp_scope.setPadding(new Insets(20, 20, 20, 20));
        gp_scope.setHgap(5);
        gp_scope.setVgap(5);

        // Text txt_scopeHead = new Text();
        // txt_scopeHead.setText("Scope Of The Project");
        //txt_scopeHead.setId("mytext1");
        Text txt_scope = new Text();
        txt_scope.setText("To mitigate the issues of conventional and manual\n"
                + " method of reservation and\n"
                + "distribution of books to readers from library,the\n"
                + "Library Management System will\n"
                + "be created. TheLibrary Management System is a system\n"
                + "for assisting both the\n"
                + "students!teachers and librarian!staff in searching\n"
                + "and supervising the books. The\n"
                + "system will provide fundamental set of features\n"
                + "for adding and or updating\n"
                + "members, adding and updating books\n"
                + "getting the list of books, finding out the list\n"
                + "of books currently checked out.");
        txt_scope.setId("mytext");
        Hyperlink hpl_gotoTerms = new Hyperlink("Goto Terms&Condition");
        hpl_gotoTerms.setId("hyperlink");

//        gp_scope.add(txt_scopeHead, 3, 2);
        gp_scope.add(txt_scope, 1, 4);
        gp_scope.add(hpl_gotoTerms, 2, 5);

        bp_scope.prefHeightProperty().bind(scene_scope.heightProperty());
        bp_scope.prefWidthProperty().bind(scene_scope.widthProperty());
        bp_scope.setCenter(gp_scope);

        hpl_gotoTerms.setOnAction(x -> {

            primaryStage.setScene(scene_terms);
        });

        hpl_scope.setOnAction(x -> {

            primaryStage.setScene(scene_scope);
        });

        BorderPane bp_1 = new BorderPane();
        Scene scene_1 = new Scene(bp_1);

        bp_1.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_1 = new GridPane();
        gp_1.setPadding(new Insets(20, 20, 20, 20));
        gp_1.setHgap(5);
        gp_1.setVgap(5);

        Text txt_welcome = new Text();
        txt_welcome.setX(10.0f);
        txt_welcome.setX(10.0f);
        txt_welcome.setY(50.0f);
        txt_welcome.setCache(true);
        txt_welcome.setText("Welcome To Library Management System");
        txt_welcome.setFill(Color.WHITE);
        txt_welcome.setFont(Font.font(null, FontWeight.BOLD, 30));

        Reflection r = new Reflection();
        r.setFraction(0.7f);
        txt_welcome.setEffect(r);
        txt_welcome.setTranslateY(400);

        Button btn_welcomeNext = new Button("Next");
        btn_welcomeNext.setMaxSize(100, 100);

        gp_1.add(txt_welcome, 4, 4);
        gp_1.add(btn_welcomeNext, 4, 95);

        bp_1.prefHeightProperty().bind(scene_1.heightProperty());
        bp_1.prefWidthProperty().bind(scene_1.widthProperty());
        bp_1.setCenter(gp_1);

        btn_termsNext.setOnAction(x -> {
            if (chkbox_agree.isSelected()) {
                primaryStage.setScene(scene_1);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Sorry Can not Continue!");
                alert.setContentText("Please Agree To The Terms!");
                alert.showAndWait();
            }
        });

        BorderPane bp_2 = new BorderPane();
        Scene scene_2 = new Scene(bp_2);

        //bp_2.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_2 = new GridPane();
        gp_2.setPadding(new Insets(20, 20, 20, 20));
        gp_2.setHgap(5);
        gp_2.setVgap(5);

        HBox h_menubar = new HBox();

        MenuBar menuBar = new MenuBar();

        Menu adminmenu1 = new Menu("Admin");
        MenuItem adminlogin = new MenuItem("Admin Login");
        Menu Schedulemenu2 = new Menu("Schedule");
        MenuItem scheduleview = new MenuItem("View Schedule");

        adminmenu1.getItems().add(adminlogin);
        Schedulemenu2.getItems().add(scheduleview);
        menuBar.getMenus().addAll(adminmenu1, Schedulemenu2);
        menuBar.setMinWidth(2700);
        menuBar.setLayoutX(3500);

        Text txtRules = new Text();
        txtRules.setText("* NO Talking Else You'll Be Fined\n"
                + "* If You Are Hungry, You May Leave Library,Eating Not Allowed\n"
                + "* Sleeping Is Not Allowed\n"
                + "* Place The Books Back From Where You Picked Them\n"
                + "* Return The Issued Book On Time Else You Will Be Fined\n"
                + "* Kindly Take Care Of These Instructions And Rules\n"
                + "THANKYOU!");
        txtRules.setId("mytext");
        h_menubar.getChildren().addAll(menuBar);
        gp_2.add(txtRules, 1, 5);
        bp_2.setTop(menuBar);
        bp_2.setCenter(gp_2);

        bp_2.prefHeightProperty().bind(scene_2.heightProperty());
        bp_2.prefWidthProperty().bind(scene_2.widthProperty());

        btn_welcomeNext.setOnAction(x -> {

            primaryStage.setScene(scene_2);
        });

        BorderPane bp_3 = new BorderPane();
        Scene scene_3 = new Scene(bp_3);
        bp_3.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_3 = new GridPane();
        gp_3.setPadding(new Insets(20, 20, 20, 20));
        gp_3.setHgap(5);
        gp_3.setVgap(5);

        Label lbl_user = new Label("User");
        lbl_user.setFont(Font.font("Impact", 20));
        lbl_user.setTextFill(Color.WHITE);
        Label lbl_password = new Label("Password");
        lbl_password.setFont(Font.font("Impact", 20));
        lbl_password.setTextFill(Color.WHITE);
        final Label lblMessage = new Label();
        TextField txt_user = new TextField();
        final PasswordField txt_pws = new PasswordField();
        Button btn_login = new Button("Login");

        gp_3.add(lbl_user, 1, 3);
        gp_3.add(lbl_password, 1, 4);
        gp_3.add(txt_user, 2, 3);
        gp_3.add(txt_pws, 2, 4);
        gp_3.add(btn_login, 3, 4);
        gp_3.add(lblMessage, 3, 5);
        bp_3.setCenter(gp_3);

        bp_3.prefHeightProperty().bind(scene_3.heightProperty());
        bp_3.prefWidthProperty().bind(scene_3.widthProperty());
        adminlogin.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_3);

        });

        BorderPane bp_4 = new BorderPane();
        Scene scene_4 = new Scene(bp_4);
        // bp_4.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_4 = new GridPane();
        gp_4.setPadding(new Insets(20, 20, 20, 20));
        gp_4.setHgap(5);
        gp_4.setVgap(5);

        HBox h1 = new HBox();

        MenuBar menuBar1 = new MenuBar();
        Menu viewd = new Menu("View Details");
        MenuItem studentdetails = new MenuItem("View Student Details");
        MenuItem issuebook = new MenuItem("Issue Book");
        MenuItem returnbook = new MenuItem("Return Book");
        MenuItem bookdetails = new MenuItem("View Book Details");
//        MenuItem vendordetails = new MenuItem("View Vendor Details");
//        MenuItem viewassets = new MenuItem("View Assests");
//        MenuItem viewbudget = new MenuItem("View Budget");
        Menu Booksearch = new Menu("Search");
        MenuItem search1 = new MenuItem("Book Search");
        Booksearch.getItems().add(search1);
        viewd.getItems().add(studentdetails);
        viewd.getItems().add(issuebook);
        viewd.getItems().add(returnbook);
        viewd.getItems().add(bookdetails);
//        viewd.getItems().add(vendordetails);
//        viewd.getItems().add(viewassets);
//        viewd.getItems().add(viewbudget);
        menuBar1.getMenus().addAll(viewd, Booksearch);
        menuBar1.setMinWidth(2700);
        menuBar1.setLayoutX(3500);

        VBox v1 = new VBox();
        TableView<Books> table_BooksInfo = new TableView<>();

        TableColumn<Books, String> id = new TableColumn<>("ID");

        id.setMinWidth(100);
        TableColumn<Books, String> name = new TableColumn<>("Name");

        name.setMinWidth(100);
        TableColumn<Books, String> auth = new TableColumn<>("Author");

        auth.setMinWidth(100);
        TableColumn<Books, String> dept = new TableColumn<>("Department");

        dept.setMinWidth(100);
        TableColumn<Books, String> quantity = new TableColumn<>("Quantity");

        quantity.setMinWidth(100);

        table_BooksInfo.getColumns().addAll(id, name, auth, dept, quantity);
        table_BooksInfo.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        auth.setCellValueFactory(new PropertyValueFactory<>("auth"));
        dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

//               BOOk ALignment
        Label l1 = new Label("Books");
        v1.setMaxHeight(400);
        l1.setTextFill(Color.WHITE);
        l1.setFont(Font.font("Verdana", 20));

        Button btn_addbook = new Button("Add Book");
        Button btn_delbook = new Button("Delete Book");
        Button btn_logout1 = new Button("Logout");

        v1.getChildren().addAll(l1, table_BooksInfo);
        h1.getChildren().addAll(menuBar1);
        gp_4.add(btn_addbook, 1, 1);
        gp_4.add(btn_delbook, 2, 1);
        gp_4.add(btn_logout1, 3, 1);

        bp_4.setBottom(gp_4);
        bp_4.setTop(menuBar1);
        bp_4.setCenter(table_BooksInfo);
        insertData1();
        table_BooksInfo.setItems(books);

        bp_4.prefHeightProperty().bind(scene_4.heightProperty());
        bp_4.prefWidthProperty().bind(scene_4.widthProperty());

        btn_login.setOnAction((ActionEvent event) -> {

            try {

                try {
                    openConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                }

                ResultSet rs = connection.createStatement().executeQuery("select * from admin");

                while (rs.next() == true) {
                    String nm = rs.getString("Name");
                    String pw = rs.getString("Password");

                    if (txt_user.getText().equals(nm) && txt_pws.getText().equals(pw)) {
                        lblMessage.setTextFill(Color.GREEN);

                        System.out.println("Login success");
                        primaryStage.setScene(scene_4);
                        lblMessage.setText("Congratulations!");
                    } else {
                        lblMessage.setText("Incorrect User Name or Psw");
                        lblMessage.setTextFill(Color.RED);
                    }
                }

                rs.close(); //Close the result set
                connection.close();

                txt_user.setText("");
                txt_pws.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn_logout1.setOnAction(x -> {

            primaryStage.setScene(scene_3);
        });
//table 123
        BorderPane bp_5 = new BorderPane();
        Scene scene_5 = new Scene(bp_5);
        bp_5.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_5 = new GridPane();
        gp_5.setPadding(new Insets(20, 20, 20, 20));
        gp_5.setHgap(5);
        gp_5.setVgap(5);

        VBox root = new VBox();

        TableView<Schedule> table = new TableView<>();

        TableColumn<Schedule, String> secol = new TableColumn<>("Serial");
        secol.setCellValueFactory(new PropertyValueFactory<>("serial"));
        secol.setMinWidth(100);
        TableColumn<Schedule, String> daycol = new TableColumn<>("Days");
        daycol.setCellValueFactory(new PropertyValueFactory<>("days"));
        daycol.setMinWidth(200);
        TableColumn<Schedule, String> tcol = new TableColumn<>("Timing");
        tcol.setCellValueFactory(new PropertyValueFactory<>("timing"));
        tcol.setMinWidth(300);
        table.getColumns().addAll(secol, daycol, tcol);
        table.setMinWidth(500);
        insertData();
        table.setItems(schedule);
        root.setMaxHeight(400);
        root.getChildren().addAll(l1, table);
        bp_5.setCenter(table);

        bp_5.prefHeightProperty().bind(scene_5.heightProperty());
        bp_5.prefWidthProperty().bind(scene_5.widthProperty());

        scheduleview.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_5);

        });

        btn_delbook.setOnAction(
                x -> {
                    if (table_BooksInfo.getSelectionModel().getSelectedItem() == null) {
                        System.out.println("Please select an item");
                        return;
                    }
                    Books ccc = table_BooksInfo.getSelectionModel().getSelectedItem();

                    try {

                        openConnection();

                        deleteStmt.setString(1, ccc.getID());
                        deleteStmt.executeUpdate();

                        closeConnection();
                        books.remove(ccc);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
        );

        BorderPane bp_6 = new BorderPane();
        Scene scene_6 = new Scene(bp_6);
        bp_6.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_6 = new GridPane();
        gp_6.setPadding(new Insets(20, 20, 20, 20));
        gp_6.setHgap(5);
        gp_6.setVgap(5);

        Label lbl_bookid = new Label("Book ID");
        Label lbl_bookname = new Label("Book Name");
        Label lbl_autname = new Label("Author Name");
        Label lbl_departname = new Label("Depart Name");
        Label lbl_quantity = new Label("Quantity");
        final TextField txt_bookid = new TextField();
        final TextField txt_bookname = new TextField();
        final TextField txt_autname = new TextField();
        final TextField txt_departname = new TextField();
        final TextField txt_quantity = new TextField();
        Button btn_addbookfinal = new Button("Add Now");
        Button btn_addbookfinalback = new Button("Back");

        gp_6.add(lbl_bookid, 1, 1);
        gp_6.add(lbl_bookname, 1, 2);
        gp_6.add(lbl_autname, 1, 3);
        gp_6.add(lbl_departname, 1, 4);
        gp_6.add(lbl_quantity, 1, 5);
        gp_6.add(txt_bookid, 2, 1);
        txt_bookid.setPromptText("Insert integer value");
        gp_6.add(txt_bookname, 2, 2);
        gp_6.add(txt_autname, 2, 3);
        gp_6.add(txt_departname, 2, 4);
        gp_6.add(txt_quantity, 2, 5);
        gp_6.add(btn_addbookfinal, 3, 6);
        gp_6.add(btn_addbookfinalback, 4, 6);

        bp_6.prefHeightProperty().bind(scene_6.heightProperty());
        bp_6.prefWidthProperty().bind(scene_6.widthProperty());

        bp_6.setCenter(gp_6);

        btn_addbook.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_6);

        });

        btn_addbookfinalback.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_4);

        });

        btn_addbookfinal.setOnAction(
                x -> {
                    Books c1 = new Books(txt_bookid.getText(), txt_bookname.getText(), txt_autname.getText(), txt_departname.getText(), txt_quantity.getText());
                    try {
                        openConnection();
                        ResultSet rex = connection.createStatement().executeQuery("select * from Books");
                        if (txt_bookid.getText().isEmpty() || txt_autname.getText().isEmpty() || txt_bookname.getText().isEmpty() || txt_departname.getText().isEmpty() || txt_quantity.getText().isEmpty()) {
                            Alert alert2 = new Alert(AlertType.ERROR);
                            alert2.setTitle("Error");
                            alert2.setHeaderText("Task Can Not Be Completed!");
                            alert2.setContentText("Please Fill All The Required Fields!");
                            alert2.show();

                        }

                        while (rex.next() == true) {
                            String comp = rex.getString("BookID");
                            if (txt_bookid.getText().equals(comp)) {
                                Alert alert3 = new Alert(AlertType.ERROR);
                                alert3.setTitle("Error");
                                alert3.setHeaderText("Task Can Not Be Completed!");
                                alert3.setContentText("Id Already Exsist!");
                                alert3.show();

                            } else {
                                insertStmt1.setString(1, c1.getID());
                                insertStmt1.setString(2, c1.getName());
                                insertStmt1.setString(3, c1.getAuth());
                                insertStmt1.setString(4, c1.getDept());
                                insertStmt1.setString(5, c1.getQuantity());

                                insertStmt1.executeUpdate();
                                Alert alert1 = new Alert(AlertType.CONFIRMATION);
                                alert1.setTitle("Alert");
                                alert1.setHeaderText("Task Done!");
                                alert1.setContentText("Book has been sucessfully added!");
                                alert1.showAndWait();

                                closeConnection();
                                books.add(c1);

                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
        );

        txt_bookid.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_bookid.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_bookid.setText(txt_bookid.getText().substring(0, txt_bookid.getText().length() - 1));
                    }
                }
            }

        });

        txt_quantity.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_quantity.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_quantity.setText(txt_quantity.getText().substring(0, txt_quantity.getText().length() - 1));
                    }
                }
            }

        });

        txt_bookname.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_bookname.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_bookname.setText(txt_bookname.getText().substring(0, txt_bookname.getText().length() - 1));
                    }
                }
            }

        });

        txt_autname.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_autname.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_autname.setText(txt_autname.getText().substring(0, txt_autname.getText().length() - 1));
                    }
                }
            }

        });

        txt_departname.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_departname.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_departname.setText(txt_departname.getText().substring(0, txt_departname.getText().length() - 1));
                    }
                }
            }

        });

        BorderPane bp_7 = new BorderPane();
        Scene scene_7 = new Scene(bp_7);
        bp_7.setPadding(new Insets(10, 50, 50, 50));

        GridPane gp_7 = new GridPane();
        gp_7.setPadding(new Insets(20, 20, 20, 20));
        gp_7.setHgap(5);
        gp_7.setVgap(5);

        Button btn_addstudent = new Button("Add Student");
        Button btn_deletestudent = new Button("Delete Student");
        Button btn_studentback = new Button("Back");

        VBox v_student = new VBox();

        TableView<Student> table2 = new TableView<>();

        TableColumn<Student, String> ssid = new TableColumn<>("Student ID");
        ssid.setCellValueFactory(new PropertyValueFactory<>("ssid"));
        ssid.setMinWidth(100);
        TableColumn<Student, String> sname = new TableColumn<>("Student Name");
        sname.setCellValueFactory(new PropertyValueFactory<>("sname"));
        sname.setMinWidth(200);
        TableColumn<Student, String> sfname = new TableColumn<>("Father Name");
        sfname.setCellValueFactory(new PropertyValueFactory<>("sfname"));
        sfname.setMinWidth(300);
        TableColumn<Student, String> sprogram = new TableColumn<>("Program");
        sprogram.setCellValueFactory(new PropertyValueFactory<>("sprogram"));
        sprogram.setMinWidth(100);
        TableColumn<Student, String> semail = new TableColumn<>("Email");
        semail.setCellValueFactory(new PropertyValueFactory<>("semail"));
        semail.setMinWidth(100);
        TableColumn<Student, String> scnic = new TableColumn<>("CNIC");
        scnic.setCellValueFactory(new PropertyValueFactory<>("scnic"));
        scnic.setMinWidth(100);
        table2.getColumns().addAll(ssid, sname, sfname, sprogram, semail, scnic);
        table2.setMinWidth(500);
        insertData2();
        table2.setItems(students);
        v_student.setMaxHeight(400);
        v_student.getChildren().addAll(l1, table2);
        gp_7.add(btn_addstudent, 1, 1);
        gp_7.add(btn_deletestudent, 2, 1);
        gp_7.add(btn_studentback, 3, 1);
        bp_7.setCenter(table2);
        bp_7.setBottom(gp_7);

        bp_7.prefHeightProperty().bind(scene_7.heightProperty());
        bp_7.prefWidthProperty().bind(scene_7.widthProperty());

        studentdetails.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_7);

        });

        btn_studentback.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_4);

        });

        btn_deletestudent.setOnAction(
                x -> {
                    if (table2.getSelectionModel().getSelectedItem() == null) {
                        System.out.println("Please select an item");
                        return;
                    }
                    Student stss = table2.getSelectionModel().getSelectedItem();

                    try {

                        openConnection();

                        deleteStmt1.setString(1, stss.getSsid());
                        deleteStmt1.executeUpdate();

                        closeConnection();
                        students.remove(stss);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
        );

        BorderPane bp_8 = new BorderPane();
        Scene scene_8 = new Scene(bp_8);
        bp_8.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_8 = new GridPane();
        gp_8.setPadding(new Insets(20, 20, 20, 20));
        gp_8.setHgap(5);
        gp_8.setVgap(5);

        Label lbl_studentid = new Label("Student ID");
        Label lbl_studentname = new Label("Student Name");
        Label lbl_fathername = new Label("Father Name");
        Label lbl_program = new Label("Program");
        Label lbl_email = new Label("Email");
        Label lbl_cnic = new Label("CNIC");
        final TextField txt_studentid = new TextField();
        final TextField txt_studentname = new TextField();
        final TextField txt_fathername = new TextField();
        final TextField txt_program = new TextField();
        final TextField txt_email = new TextField();
        final TextField txt_cnic = new TextField();
        Button btn_addstudentfinal = new Button("Add Now");
        Button btn_addstudentfinalback = new Button("Back");

        gp_8.add(lbl_studentid, 1, 1);
        gp_8.add(lbl_studentname, 1, 2);
        gp_8.add(lbl_fathername, 1, 3);
        gp_8.add(lbl_program, 1, 4);
        gp_8.add(lbl_email, 1, 5);
        gp_8.add(lbl_cnic, 1, 6);
        gp_8.add(txt_studentid, 2, 1);
        txt_studentid.setPromptText("Insert integer value");
        gp_8.add(txt_studentname, 2, 2);
        gp_8.add(txt_fathername, 2, 3);
        gp_8.add(txt_program, 2, 4);
        gp_8.add(txt_email, 2, 5);
        gp_8.add(txt_cnic, 2, 6);
        gp_8.add(btn_addstudentfinal, 3, 7);
        gp_8.add(btn_addstudentfinalback, 4, 7);

        bp_8.prefHeightProperty().bind(scene_8.heightProperty());
        bp_8.prefWidthProperty().bind(scene_8.widthProperty());

        bp_8.setCenter(gp_8);

        btn_addstudentfinalback.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_7);

        });

        btn_addstudentfinal.setOnAction(
                x -> {
                    Student st = new Student(txt_studentid.getText(), txt_studentname.getText(), txt_fathername.getText(), txt_program.getText(), txt_email.getText(), txt_cnic.getText());
                    try {
                        openConnection();
                        if (txt_studentname.getText().isEmpty() || txt_fathername.getText().isEmpty() || txt_studentid.getText().isEmpty() || txt_program.getText().isEmpty() || txt_email.getText().isEmpty() || txt_cnic.getText().isEmpty()) {
                            Alert alertstd = new Alert(AlertType.ERROR);
                            alertstd.setTitle("Error");
                            alertstd.setHeaderText("Task Can Not Be Completed!");
                            alertstd.setContentText("Please Fill All The Required Fields!");
                            alertstd.show();

                        }
                        ResultSet rex = connection.createStatement().executeQuery("select * from Student");
                        while (rex.next() == true) {
                            String comp = rex.getString("StudentID");
                            if (txt_studentid.getText().equals(comp)) {
                                txt_studentid.setText("ID Already Exist");
                            } else {

                                insertStmt.setString(1, st.getSsid());
                                insertStmt.setString(2, st.getSname());
                                insertStmt.setString(3, st.getSfname());
                                insertStmt.setString(4, st.getSprogram());
                                insertStmt.setString(5, st.getSemail());
                                insertStmt.setString(6, st.getScnic());

                                insertStmt.executeUpdate();
                                Alert alertstudent = new Alert(AlertType.CONFIRMATION);
                                alertstudent.setTitle("Alert");
                                alertstudent.setHeaderText("Task Done!");
                                alertstudent.setContentText("Student has been sucessfully added!");
                                alertstudent.showAndWait();

                                closeConnection();
                                students.add(st);

                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
        );

        txt_studentid.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_studentid.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_studentid.setText(txt_studentid.getText().substring(0, txt_studentid.getText().length() - 1));
                    }
                }
            }

        });

        txt_cnic.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_cnic.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_cnic.setText(txt_cnic.getText().substring(0, txt_cnic.getText().length() - 1));
                    }
                }
            }

        });

        txt_studentname.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_studentname.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_studentname.setText(txt_studentname.getText().substring(0, txt_studentname.getText().length() - 1));
                    }
                }
            }

        });

        txt_fathername.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_fathername.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_fathername.setText(txt_fathername.getText().substring(0, txt_fathername.getText().length() - 1));
                    }
                }
            }

        });

        txt_program.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_program.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_program.setText(txt_program.getText().substring(0, txt_program.getText().length() - 1));
                    }
                }
            }

        });
        btn_addstudent.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_8);
        });

        BorderPane bp_9 = new BorderPane();
        Scene scene_9 = new Scene(bp_9);
        bp_9.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_9 = new GridPane();
        gp_9.setPadding(new Insets(20, 20, 20, 20));
        gp_9.setHgap(5);
        gp_9.setVgap(5);

        Label lbl_issuebookname = new Label("Book Name");
        lbl_issuebookname.setTextFill(Color.WHITE);
        lbl_issuebookname.setFont(Font.font("Verdana", 16));
        Label lbl_issuestudentid = new Label("Student ID");
        lbl_issuestudentid.setTextFill(Color.WHITE);
        lbl_issuestudentid.setFont(Font.font("Verdana", 16));
        Label lbl_issuedepart = new Label("Student Department");
        lbl_issuedepart.setTextFill(Color.WHITE);
        lbl_issuedepart.setFont(Font.font("Verdana", 16));
        Label lbl_issueduedate = new Label("Due Date");
        lbl_issueduedate.setTextFill(Color.WHITE);
        lbl_issueduedate.setFont(Font.font("Verdana", 16));
        Button btn_issue = new Button("Issue Book");
        Button btn_issueback = new Button("Back");
        ComboBox combo_book = new ComboBox();
        Label lhead = new Label("Issue New Book");
        lhead.setTextFill(Color.WHITE);
        lhead.setFont(Font.font("Verdana", 16));
        combo_book.setMaxHeight(50);
        DatePicker date1 = new DatePicker();
        LocalDate da = date1.getValue();
        TextField txt_date = new TextField();
        TextField txt_issuestudentid = new TextField();
        TextField txt_issuedepartname = new TextField();
        combo_book.setValue("Books");
        openConnection();
        ResultSet ras = connection.createStatement().executeQuery("select Name from Books");
        while (ras.next() == true) {
            combo_book.getItems().addAll(ras.getString("Name"));
        }
        ras.close();
        connection.close();

        gp_9.add(lbl_issuebookname, 1, 2);
        gp_9.add(lbl_issuestudentid, 1, 3);
        gp_9.add(lbl_issuedepart, 1, 4);
        gp_9.add(lbl_issueduedate, 1, 5);
        gp_9.add(combo_book, 2, 2);
        gp_9.add(txt_issuestudentid, 2, 3);
        gp_9.add(txt_issuedepartname, 2, 4);
        gp_9.add(date1, 2, 5);
        gp_9.add(btn_issue, 3, 6);
        gp_9.add(btn_issueback, 4, 6);

        txt_issuestudentid.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_issuestudentid.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_issuestudentid.setText(txt_issuestudentid.getText().substring(0, txt_issuestudentid.getText().length() - 1));
                    }
                }
            }

        });

        txt_issuedepartname.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = txt_issuedepartname.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if ((ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        txt_issuedepartname.setText(txt_issuedepartname.getText().substring(0, txt_issuedepartname.getText().length() - 1));
                    }
                }
            }

        });

        bp_9.prefHeightProperty().bind(scene_9.heightProperty());
        bp_9.prefWidthProperty().bind(scene_9.widthProperty());

        bp_9.setCenter(gp_9);

        btn_issue.setOnAction((ActionEvent event) -> {
            try {
                IssuedBooks ib = new IssuedBooks(combo_book.getSelectionModel().getSelectedItem().toString(), txt_issuestudentid.getText(), txt_issuedepartname.getText(), date1.getValue().toString(), dateFormat.format(date));

                openConnection();

                insertStmt3.setString(1, ib.getBname());
                insertStmt3.setString(2, ib.getSid());
                insertStmt3.setString(3, ib.getStprogram());
                insertStmt3.setString(4, ib.getDuedate());
                insertStmt3.setString(5, ib.getIssuedate());

                insertStmt3.executeUpdate();
                Alert alertissue = new Alert(AlertType.CONFIRMATION);
                alertissue.setTitle("Alert");
                alertissue.setHeaderText("Task Done!");
                alertissue.setContentText("Book Is Issued To Student Successfully!");
                alertissue.showAndWait();

                closeConnection();
                issuebooks.add(ib);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        });
        btn_issueback.setOnAction(x -> {

            primaryStage.setScene(scene_4);
        });

        issuebook.setOnAction(x -> {

            primaryStage.setScene(scene_9);
        });

        BorderPane bp_10 = new BorderPane();
        Scene scene_10 = new Scene(bp_10);
        bp_10.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_10 = new GridPane();
        gp_10.setPadding(new Insets(20, 20, 20, 20));
        gp_10.setHgap(5);
        gp_10.setVgap(5);

        Label lbl_returnbook = new Label("Select Book");
        lbl_returnbook.setTextFill(Color.WHITE);
        ComboBox combo_returnbook = new ComboBox();
        Label lbl_currentdate = new Label("Current Date");
        lbl_currentdate.setTextFill(Color.WHITE);
        Label lbl_displaydate = new Label();
        lbl_displaydate.setTextFill(Color.WHITE);
        lbl_displaydate.setText(dateFormat.format(date));
        Button btn_returnbook = new Button("Return Book");
        Button btn_chkfine = new Button("Check Fine");
        Label lbl_show = new Label("Fine");
        Label lbl_return = new Label();
        lbl_show.setTextFill(Color.WHITE);
        lbl_show.setFont(Font.font("Verdena", 14));
        lbl_return.setTextFill(Color.GREEN);
        lbl_return.setFont(Font.font("Verdena", 14));
        Button btn_returnback = new Button("Back");
        btn_returnbook.setLayoutX(100);
        TableView<IssuedBooks> table3 = new TableView();
        table3.setMinWidth(100);
        TableColumn<IssuedBooks, String> bname = new TableColumn<>("Book Name");
        bname.setMinWidth(100);
        bname.setCellValueFactory(new PropertyValueFactory<>("bname"));
        TableColumn<IssuedBooks, String> stid = new TableColumn<>("Student ID");
        stid.setMinWidth(100);
        stid.setCellValueFactory(new PropertyValueFactory<>("stid"));
        TableColumn<IssuedBooks, String> stprogram = new TableColumn<>("Program");
        stprogram.setMinWidth(100);
        stprogram.setCellValueFactory(new PropertyValueFactory<>("stprogram"));
        TableColumn<IssuedBooks, String> duedate = new TableColumn<>("Due Date");
        duedate.setMinWidth(100);
        duedate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        TableColumn<IssuedBooks, String> issuedate = new TableColumn<>("Issue Date");
        issuedate.setMinWidth(100);
        issuedate.setCellValueFactory(new PropertyValueFactory<>("issuedate"));
        table3.getColumns().addAll(bname, stid, stprogram, duedate, issuedate);
        table3.setMinWidth(500);
        insertData3();
        table3.setItems(issuebooks);

        gp_10.add(lbl_returnbook, 1, 1);
        gp_10.add(lbl_currentdate, 1, 2);
        gp_10.add(combo_returnbook, 2, 1);
        gp_10.add(lbl_displaydate, 2, 2);
        gp_10.add(btn_chkfine, 1, 3);
        gp_10.add(lbl_show, 2, 3);
        bp_10.setTop(gp_10);
        bp_10.setCenter(table3);

        GridPane gp_btnonlypane = new GridPane();
        gp_btnonlypane.add(btn_returnbook, 1, 1);
        gp_btnonlypane.add(btn_returnback, 2, 1);
        gp_btnonlypane.add(lbl_return, 3, 1);
        bp_10.prefHeightProperty().bind(scene_10.heightProperty());
        bp_10.prefWidthProperty().bind(scene_10.widthProperty());
        bp_10.setBottom(gp_btnonlypane);

        btn_returnback.setOnAction(x -> {
            primaryStage.setScene(scene_4);

        });

        openConnection();
        ResultSet rreturn = connection.createStatement().executeQuery("select Bookname from issuedBooks");
        while (rreturn.next() == true) {
            combo_returnbook.getItems().addAll(rreturn.getString("Bookname"));

        }
        btn_chkfine.setOnAction((ActionEvent event) -> {
            try {

                try {
                    openConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                }

                ResultSet ret = connection.createStatement().executeQuery("select * from issuedBooks");

                while (ret.next() == true) {
                    String idate = ret.getString("issuedate");
                    String rdate = ret.getString("Due Date");
                    String bnn = ret.getString("Bookname");

                    if (combo_returnbook.getSelectionModel().getSelectedItem().equals(bnn) && rdate.equals(dateFormat.format(date))) {
                        lbl_show.setText("Fine Is Due");
                        lbl_show.setTextFill(Color.RED);

                    } else {
                        lbl_show.setText("No Fine Is Due");
                        lbl_show.setTextFill(Color.GREEN);
                    }
                }

                ret.close(); //Close the result set
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);

            }
        });

        btn_returnback.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_4);

        });

        btn_returnbook.setOnAction(x -> {

            IssuedBooks sss = table3.getSelectionModel().getSelectedItem();

            try {

                openConnection();

                deleteStmt2.setString(1, sss.getBname());
                deleteStmt2.executeUpdate();

                closeConnection();
                issuebooks.remove(sss);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            lbl_return.setText("Book Returned");

        });

        returnbook.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene_10);

        });

        dataBooks = FXCollections.observableArrayList();
        try {

            openConnection();

            ResultSet rchart = connection.createStatement().executeQuery("select * from Books");
            while (rchart.next()) {
                //adding data on piechart data
                dataBooks.add(new PieChart.Data(rchart.getString("Name"), rchart.getDouble("Quantity")));
            }
        } catch (Exception e) {

        }
        BorderPane bp_11 = new BorderPane();
        Scene scene_11 = new Scene(bp_11);
        bp_11.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_11 = new GridPane();
        gp_11.setHgap(5);
        gp_11.setVgap(5);
        PieChart piechart = new PieChart();
        Button btn_backBookData = new Button("Back");
        piechart.getData().addAll(dataBooks);
        gp_11.add(btn_backBookData, 6, 1);
        bp_11.setCenter(piechart);
        bp_11.setBottom(gp_11);

        bp_11.prefHeightProperty().bind(scene_11.heightProperty());
        bp_11.prefWidthProperty().bind(scene_11.widthProperty());

        btn_backBookData.setOnAction(x -> {

            primaryStage.setScene(scene_4);

        });

        bookdetails.setOnAction(x -> {

            primaryStage.setScene(scene_11);

        });

        BorderPane bp_12 = new BorderPane();
        Scene scene_12 = new Scene(bp_12);
        bp_12.setPadding(new Insets(10, 50, 50, 50));
        GridPane gp_12 = new GridPane();
        gp_12.setHgap(5);
        gp_12.setVgap(5);

        Label lbl_searchbook = new Label("Search Book");
        lbl_searchbook.setTextFill(Color.WHITE);
        lbl_searchbook.setFont(Font.font("Verdana", 16));
        Button btn_searchbook = new Button("Search");
        Button btn_searchbookBack = new Button("Back");
        TextArea txt_a = new TextArea();
        TextField txt_searchbook = new TextField();
        Label lbl_bookname1 = new Label("Enter Book Name");
        lbl_bookname1.setTextFill(Color.WHITE);
        lbl_bookname1.setFont(Font.font("Verdana", 16));
        gp_12.add(lbl_bookname1, 1, 1);
        gp_12.add(txt_searchbook, 2, 1);
        gp_12.add(btn_searchbook, 3, 2);
        gp_12.add(btn_searchbookBack, 4, 2);
        bp_12.setTop(lbl_searchbook);
        bp_12.setCenter(gp_12);
        bp_12.setBottom(txt_a);

        btn_searchbook.setOnAction(x -> {

            try {
                openConnection();

                ResultSet txtar = connection.createStatement().executeQuery("select * from Books");

                while (txtar.next() == true) {

                    if (txt_searchbook.getText().equals(txtar.getString("Name"))) {
                        txt_a.setText(txtar.getString("BookID") + "     " + txtar.getString("Name") + "     " + txtar.getString("Author") + "     " + txtar.getString("Department") + "     " + txtar.getString("Quantity"));
                        break;
                    } else {
                        txt_a.setText("Book Not Found");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn_searchbookBack.setOnAction(x -> {
            primaryStage.setScene(scene_4);
        });

        bp_12.prefHeightProperty().bind(scene_12.heightProperty());
        bp_12.prefWidthProperty().bind(scene_12.widthProperty());

        search1.setOnAction(x -> {
            primaryStage.setScene(scene_12);
        });

        scene_main.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_terms.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_scope.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_1.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_2.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_3.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_4.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_5.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_6.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_7.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_8.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_9.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_10.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_11.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        scene_12.getStylesheets().add(LibrarySystem.class.getResource("LMS.css").toExternalForm());
        primaryStage.setScene(scene_main);
        primaryStage.show();

    }

}
