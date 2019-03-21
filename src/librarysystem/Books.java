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
public class Books {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty auth = new SimpleStringProperty();
    private final StringProperty dept = new SimpleStringProperty();
    private final StringProperty quantity = new SimpleStringProperty();

    public Books(String id, String name, String auth, String dept, String quantity) {
        this.id.set(id);
        this.name.set(name);
        this.dept.set(dept);

        this.auth.set(auth);
        this.quantity.set(quantity);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getID() {
        return id.get();
    }

    public void setID(String id) {
        this.id.set(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty authProperty() {
        return auth;
    }

    public String getAuth() {
        return auth.get();
    }

    public void setAuth(String auth) {
        this.auth.set(auth);
    }

    public StringProperty deptProperty() {
        return dept;
    }

    public String getDept() {
        return dept.get();
    }

    public void setDept(String dept) {
        this.dept.set(dept);
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

}
