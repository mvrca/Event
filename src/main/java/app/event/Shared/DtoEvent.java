package app.event.Shared;



import app.event.Models.Event;
import jakarta.validation.constraints.NotBlank;

public class DtoEvent {
    
    @NotBlank
    private String name;
    @NotBlank    
    private String place;
    @NotBlank
    private String date;
    @NotBlank
    private String time;

    public Event toEvent(){
        Event event = new Event();
        event.setName(name);
        event.setPlace(place);
        event.setDate(date);
        event.setTime(time);
        return event;
    }
    public Event toEvent(Event ev){
        ev.setName(this.name);
        ev.setPlace(this.place);
        ev.setDate(this.date);
        ev.setTime(this.time);
        return ev;
    }

    public void fromEvent(Event ev){
        this.name = ev.getName();
        this.place = ev.getPlace();
        this.date = ev.getDate();
        this.time = ev.getTime();
    }
    
    //#region Getters&Setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    //#endregion
    
}
