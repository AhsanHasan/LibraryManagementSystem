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
public class Student {
     private final StringProperty ssid = new SimpleStringProperty();
	private final StringProperty sname = new SimpleStringProperty();
        private final StringProperty sfname = new SimpleStringProperty();
	private final StringProperty sprogram = new SimpleStringProperty();
	private final StringProperty semail = new SimpleStringProperty();
	private final StringProperty scnic = new SimpleStringProperty();
        
	public Student(String ssid, String sname,String sfname, String sprogram, String semail,String scnic)
	{
		this.ssid.set(ssid);
		this.sname.set(sname);
		this.sfname.set(sfname);
                this.sprogram.set(sprogram);
                this.semail.set(semail);
           this.scnic.set(scnic);
        }

   

 
	
	public StringProperty ssidProperty()
	{
		return ssid;
	}
	
	public String getSsid()
	{
		return ssid.get();
	}
	
	public void setSsid(String ssid)
	{
		this.ssid.set(ssid);
	}
	
	
	
	public StringProperty snameProperty()
	{
		return sname;
	}
	
	public String getSname()
	{
		return sname.get();
	}
	
	public void setSname(String sname)
	{
		this.sname.set(sname);
	}
	
	
	
	public StringProperty sfnameProperty()
	{
		return sfname;
	}
	
	public String getSfname()
	{
		return sfname.get();
	}
	
	public void setSfname(String sfname)
	{
		this.sfname.set(sfname);
	}
	public StringProperty sprogramProperty()
	{
		return sprogram;
	}
	
	public String getSprogram()
	{
		return sprogram.get();
	}
	
	public void setSprogram(String sprogram)
	{
		this.sprogram.set(sprogram);
	}
	
public StringProperty semailProperty()
	{
		return semail;
	}
	
	public String getSemail()
	{
		return semail.get();
	}
	
	public void setSemail(String semail)
	{
		this.semail.set(semail);
	}
	public StringProperty scnicProperty()
	{
		return scnic;
	}
	
	public String getScnic()
	{
		return scnic.get();
	}
	
	public void setScnic(String scnic)
	{
		this.scnic.set(scnic);
	}
	
}
    

