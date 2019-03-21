/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author AHSAN
 */
public class IssuedBooks {

    private final StringProperty bname = new SimpleStringProperty();
    private final StringProperty stid = new SimpleStringProperty();
    private final StringProperty stprogram = new SimpleStringProperty();
    private final StringProperty duedate = new SimpleStringProperty();
    private final StringProperty issuedate = new SimpleStringProperty();

    IssuedBooks(String bname, String stid, String stprogram, String duedate, String issuedate) {
        this.bname.set(bname);
        this.stid.set(stid);
        this.stprogram.set(stprogram);
        this.duedate.set(duedate);
        this.issuedate.set(issuedate);
    }

    public StringProperty bnameProperty() {
        return bname;
    }

    public String getBname() {
        return bname.get();
    }

    public void setBname(String bname) {
        this.bname.set(bname);
    }

    public StringProperty stidProperty() {
        return stid;
    }

    public String getSid() {
        return stid.get();
    }

    public void setStid(String stid) {
        this.stid.set(stid);
    }

    public StringProperty stprogramProperty() {
        return stprogram;
    }

    public String getStprogram() {
        return stprogram.get();
    }

    public void setStprogram(String stprogram) {
        this.stprogram.set(stprogram);
    }

    public StringProperty duedateProperty() {
        return duedate;
    }

    public String getDuedate() {
        return duedate.get();
    }

    public void setDuedate(String duedate) {
        this.duedate.set(duedate);
    }

    public StringProperty issuedateProperty() {
        return issuedate;
    }

    public String getIssuedate() {
        return issuedate.get();
    }

    public void setIssuedate(String issuedate) {
        this.issuedate.set(issuedate);
    }

}
