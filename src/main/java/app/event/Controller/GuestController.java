package app.event.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import app.event.Services.GuestService;
import app.event.Shared.DtoGuest;
import jakarta.validation.Valid;

@Controller
public class GuestController {

    @Autowired
    private GuestService gService;

    @GetMapping("/{id}/guest")
    public ModelAndView showGuestForm(@PathVariable long id, DtoGuest dtoGuest){
        return gService.showGuestForm(id);
    }

    @PostMapping("/{id}/guest")
    public ModelAndView saveGuest(@PathVariable long id, @Valid DtoGuest guest, BindingResult result){
        return gService.saveGuest(id, guest, result);
    }

    @GetMapping("/{id}/guestList")
    public ModelAndView showGuestList(@PathVariable long id){
        return gService.showGuestList(id);
    }

    @GetMapping("/{id}/delAll")
    public String deleteAllGuests(@PathVariable Long id){
        return gService.deleteAllGuests(id);
    }

    @GetMapping("/{phone}/del")
    public String del(@PathVariable String phone){
        return gService.del(phone);
    }
}
