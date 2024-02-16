package app.event.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import app.event.Models.Event;
import app.event.Models.Guest;
import app.event.Repositories.EventRepository;
import app.event.Repositories.GuestRepository;
import app.event.Shared.DtoGuest;
import jakarta.validation.Valid;

@Service
public class GuestService {
    @Autowired
    private EventRepository evRepository;
    @Autowired
    private GuestRepository gRepository;
    
    public ModelAndView showGuestForm(@PathVariable long id){
        Optional<Event> event= evRepository.findById(id);
        ModelAndView mv= new ModelAndView("events/GuestForm");
        mv.addObject("event", event.get());
        return mv;
    }

    public ModelAndView saveGuest(@PathVariable long id, @Valid DtoGuest guest, BindingResult result){
        if(result.hasErrors()){
            Optional<Event> event = evRepository.findById(id);
            System.out.println("*******************Guest Service ERRorrrr*****");
            ModelAndView mv = new ModelAndView("/events/GuestForm");
            mv.addObject("event", event.get());
            return mv;
        }
        Optional<Event> event = evRepository.findById(id);
        ModelAndView mv = new ModelAndView("events/GuestForm");
        Guest g = guest.toGuest();
        g.setEvent(event.get());
        gRepository.save(g);
        mv.addObject("guest", g);
        return new ModelAndView("redirect:/{id}/guestList");
        
    }

    public ModelAndView showGuestList(@PathVariable long id){
        Optional<Event> event = evRepository.findById(id);
        List<Guest> g = gRepository.findByEvent(event.get());
        ModelAndView mv = new ModelAndView("/events/GuestList");
        Event ev = event.get();
        mv.addObject("id", ev.getId());
        mv.addObject("guest", g);
        return mv;
    }

    public String deleteAllGuests(@PathVariable Long id){
        Optional<Event> event = evRepository.findById(id);
        if (event.isPresent()) {
            System.out.println("Todos os convidados deletados da lista*********");
            List<Guest> gues = gRepository.findByEvent(event.get());
            gRepository.deleteAll(gues);
            return "redirect:/list";
        }else{
            return "redirect:/list";
        }
    }

    public String del(@PathVariable String phone){
        Guest guest = gRepository.findByPhone(phone);
        gRepository.deleteById(phone);;

        Event ev = guest.getEvent();
        Long code = ev.getId();
        String id = "" + code;

        return "redirect:/" + id + "/guestList";

        
    }
}