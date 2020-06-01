/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

/**
 *
 * @author compulife
 */
public class Borrowers {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer mobile;
    private String email;
    private String address;
    private Integer gender;

    public Borrowers() {
        
    }
    
    

    public Borrowers(Integer id, String firstName, String lastName, Integer mobile, String email, String address, Integer gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

   
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the mobile
     */
    public Integer getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    
    
}
