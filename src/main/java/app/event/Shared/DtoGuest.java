package app.event.Shared;

import app.event.Models.Guest;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DtoGuest {
    @NotBlank
    @NotNull
    private String name;
    @Id
    @NotBlank
    @NotNull
    private String phone;

    public Guest toGuest(){
        Guest guest = new Guest();
        guest.setName(name);
        guest.setPhone(phone);
        return guest;
    }
    public void fromGuest(Guest g){
        this.name = g.getName();
        this.phone = g.getPhone();
    }
    public Guest toGuest( Guest g){
        g.setName(name);
        g.setPhone(phone);
        return g;
    }
    //#region Getters&Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //#endregion

    
}
