package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String usuario, @RequestParam String senha) {
        //lógica - ss
        if ("admin".equals(usuario) && "password".equals(senha)) {
            ModelAndView mv = new ModelAndView("entrada");
            mv.addObject("usuario", usuario);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("erro", "Usuário ou senha inválidos");
            return mv;
        }
    }
}
