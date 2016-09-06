package ru.kpfu.itis.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.model.SimpleAuthUser;
import ru.kpfu.itis.model.VKAuthUser;
import ru.kpfu.itis.model.enums.AuthorityType;
import ru.kpfu.itis.repository.VKAuthUserRepository;
import ru.kpfu.itis.service.SimpleAuthUserService;
import ru.kpfu.itis.utils.Utils;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class IndexController {

    public final String CLIENT_ID = "5609595";
    public final String CLIENT_SECRET = "udqVuvIQ4o5lTREe1XpJ";
    public final String REDIRECT_URI = "http://localhost:8080/success";
    public final String accessCode = "udqVuvIQ4o5lTREe1XpJ";
    public final String ACCESS_TOKEN_URL = "https://oauth.vk.com/access_token";
    public final String API_URL = "https://api.vk.com/method/users.get";


    @Autowired
    VKAuthUserRepository vkRepository;
    @Autowired
    SimpleAuthUserService simpleAuthUserService;

    /*@Autowired
    private AuthenticationManager authenticationManager;*/

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping(value = "/success")
    public String successPage(@RequestParam("code") String code, Model model) {
        String request = Utils.sendHttpGetRequest(ACCESS_TOKEN_URL, new String[]{"client_id", "client_secret", "redirect_uri", "code"}, new String[]{CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, code});
        JSONObject jsonReq = new JSONObject(request);
        String accessToken = jsonReq.getString("access_token");
        Integer expiresIn = jsonReq.getInt("expires_in");

        String userInfo = Utils.sendHttpGetRequest(API_URL, new String[]{"access_token"}, new String[]{accessToken});
        JSONObject jsonRequest = new JSONObject(userInfo);
        JSONObject jsonUserInfo = (JSONObject)jsonRequest.getJSONArray("response").get(0);

        if (vkRepository.findOneByUsernameAndType(Integer.toString(jsonUserInfo.getInt("uid")), AuthorityType.VK) == null) {
            VKAuthUser user = new VKAuthUser();
            user.setAccessToken(accessToken);
            user.setUsername(Integer.toString(jsonUserInfo.getInt("uid")));
            user.setFirstName(jsonUserInfo.getString("first_name"));
            user.setLastName(jsonUserInfo.getString("last_name"));
            user.setType(AuthorityType.VK);
            vkRepository.save(user);
        } else model.addAttribute("error_is_registered", true);
        return "success";
    }

    @RequestMapping(value = "/registration/vk")
    public ModelAndView vkRegistration() throws Exception {
        return new ModelAndView(new RedirectView("https://oauth.vk.com/authorize?client_id=" + CLIENT_ID + "&display=page&scope=friends&redirect_uri=" + REDIRECT_URI + "&response_type=code&v=5.53", true, true, true));
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userform", new UserRegistrationForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userform") @Valid UserRegistrationForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        simpleAuthUserService.saveNewUser(form);
        return "redirect:/";
    }

    @RequestMapping(value = "/test")
    public String test(){

        return "index";
    }

}
