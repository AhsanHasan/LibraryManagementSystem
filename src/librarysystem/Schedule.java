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
public class Schedule {

    private final StringProperty serial = new SimpleStringProperty();
    private final StringProperty days = new SimpleStringProperty();
    private final StringProperty timing = new SimpleStringProperty();

    public Schedule(String serial, String days, String timing) {
        this.serial.set(serial);
        this.days.set(days);
        this.timing.set(timing);
    }

    public StringProperty serialProperty() {
        return serial;
    }

    public String getSerial() {
        return serial.get();
    }

    public void setSerial(String serial) {
        this.serial.set(serial);
    }

    public StringProperty daysProperty() {
        return days;
    }

    public String getDays() {
        return days.get();
    }

    public void setDays(String days) {
        this.days.set(days);
    }

    public StringProperty timingProperty() {
        return timing;
    }

    public String getTiming() {
        return timing.get();
    }

    public void setTiming(String timing) {
        this.timing.set(timing);
    }

}
