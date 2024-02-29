package app.event.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import app.event.Services.EventService;
import app.event.Shared.DtoEvent;
import jakarta.validation.Valid;


@Controller
public class EventController {

    @Autowired
    private EventService evService;

    @GetMapping("/form")
    public String showFormEvent(DtoEvent dtoEvent, BindingResult result){
        return evService.showEventForm();
    }

    @PostMapping("/form")
    public String saveEvent(@Valid DtoEvent dtoEvent, BindingResult result){
        return evService.saveEvent(dtoEvent,result);
    }

    @GetMapping("/list")
    public ModelAndView showList(){
        return evService.showList();
    }

    @GetMapping("/{id}/details")
    public ModelAndView showDetails(@PathVariable long id){
        return evService.showDetails(id);
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable Long id, DtoEvent dto, BindingResult result){
        return evService.edit(id, dto, result);
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id,@Valid DtoEvent dto, BindingResult result){
        return evService.update(id, dto, result);
    }

    @GetMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id){
        return evService.delteEvent(id);
    }

}
