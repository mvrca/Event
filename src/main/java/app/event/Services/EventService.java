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
import app.event.Shared.DtoEvent;
import jakarta.validation.Valid;

@Service
public class EventService {
    @Autowired
    private EventRepository evRepository;
    @Autowired
    private GuestRepository gRepository;

    public String showEventForm(){
        return "events/EventForm";
    }

    public String saveEvent(@Valid DtoEvent dtoEvent, BindingResult result){
        if(result.hasErrors()){
            System.out.println("*********Form Error********");
            return "events/EventForm";
        }
        Event events = dtoEvent.toEvent();
        evRepository.save(events);
        return "redirect:/list";
    }

    public ModelAndView showList(){
        List<Event> ev = evRepository.findAll();
        ModelAndView mv = new ModelAndView("events/EventList");
        mv.addObject("events", ev);
        return mv;
    }

    public ModelAndView showDetails(@PathVariable long id){
        Optional<Event> event = evRepository.findById(id);
        ModelAndView mv = new ModelAndView("events/DetailsEvent");
        mv.addObject("event", event.get());
        return mv;
    }

    public ModelAndView edit(@PathVariable Long id, DtoEvent dto, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("***********************************************EDIIIIITTTT ERROOORRR!!!**********************************************");
            return new ModelAndView("events/Edit");
        }
        else{
            Optional<Event> opt = evRepository.findById(id);

            if (opt.isPresent()) {
                Event event= opt.get();
                dto.fromEvent(event);
                ModelAndView mv = new ModelAndView("events/Edit");
                mv.addObject("eventId", event.getId());
                return mv;
            }
            else{
                return new ModelAndView("redirect:/form");
            }
        }
        
        
    }

    public ModelAndView update(@PathVariable Long id, @Valid DtoEvent dto, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("********************UPDAAATEEEE  ERROOOOOOR!!!************************");
            return new ModelAndView("events/Edit");
        }
        else{ 
            Optional<Event> opt = evRepository.findById(id);
            if (opt.isPresent()) {
                Event eve = dto.toEvent(opt.get());
                evRepository.save(eve);
                return new ModelAndView("redirect:/"+ eve.getId()+ "/details");
            }
            else{
                System.out.println("******************************** OPTIONALLLLLLLLL    NÃO ENCONTRADOO");
                return new ModelAndView("redirect:/list");
            }
        }
        
    }

    public String delteEvent(@PathVariable Long id){
        Optional<Event> ev = evRepository.findById(id);
        if (ev.isPresent()) {
            List<Guest> guests = gRepository.findByEvent(ev.get());
            gRepository.deleteAll(guests);
            evRepository.deleteById(id);
            return "redirect:/list";
        }
        else{
            return "redirect:/list";
        }        
    }
        

}

