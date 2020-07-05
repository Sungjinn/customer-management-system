package app.models;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
        String id, name,companyName,address,DOB,cardNumber,cardValidity,contractDay,contractPeriod,performance,note,phone, hideCardnumber = "************", hideCardValidity = "****";

    public Customer(String id, String name, String companyName,String phone, String address, String DOB, String cardNumber, String cardValidity, String contractDay, String contractPeriod, String performance, String note) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.phone = phone;
        this.address = address;
        this.DOB = DOB;
        this.cardNumber = cardNumber;
        this.cardValidity = cardValidity;
        this.contractDay = contractDay;
        this.contractPeriod = contractPeriod;
        this.performance = performance;
        this.note = note;
    }

    public String getHideCardValidity() {
        return hideCardValidity;
    }

    public void setHideCardValidity(String hideCardValidity) {
        this.hideCardValidity = hideCardValidity;
    }

    public String getHideCardnumber() {
        return hideCardnumber;
    }

    public void setHideCardnumber(String hideCardnumber) {
        this.hideCardnumber = hideCardnumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardValidity() {
        return cardValidity;
    }

    public void setCardValidity(String cardValidity) {
        this.cardValidity = cardValidity;
    }

    public String getContractDay() {
        return contractDay;
    }

    public void setContractDay(String contractDay) {
        this.contractDay = contractDay;
    }

    public String getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
