package stu.lee.comuunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stu.lee.comuunity.dto.AccessTokenDTO;
import stu.lee.comuunity.dto.GithubUser;
import stu.lee.comuunity.provider.GithubProvide;


@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvide githubProvide;
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("18f47c4a1bbebcf5769f");
        accessTokenDTO.setClient_secret("30467262dd12a24c8a67571f070b98f9d475ebfb");
        String accessToken = githubProvide.getAccessToken(accessTokenDTO);
        GithubUser user =githubProvide.getUser(accessToken);
        System.out.println(user.getName());

        return "index";
    }
}
