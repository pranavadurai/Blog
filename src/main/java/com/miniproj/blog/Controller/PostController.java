package com.miniproj.blog.Controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.miniproj.blog.Model.Authentication;
import com.miniproj.blog.Model.Post;
import com.miniproj.blog.Repository.PostRepository;
import com.miniproj.blog.Service.AuthenticationService;
import com.miniproj.blog.emailService.EmailServiceImpl;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
@SessionAttributes("remembertoken")
public class PostController {

	private Log logger = LogFactory.getLog(PostController.class);
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@GetMapping("/new_post")
	public String new_post_form_display(ModelMap model) {
		logger.info("new post form called to create a post, Authorization check initiated");
		Authentication authentication = authService.getAuthUser((String) model.get("remembertoken"));
		if(authentication == null)
			return "redirect:/signin";
		
		logger.info("User "+authentication.getProfile().getName()+", Authorized to create a new post, displaying the post form");
		model.addAttribute("name",authentication.getProfile().getName());
		model.addAttribute("Post", new Post());
		return "post_form";
	}
	
	@PostMapping("/new_post")
	public String save_new_post_in_DB(@ModelAttribute Post post, ModelMap model) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, MessagingException {
		logger.info("new post form submitted, Authorization check initiated");
		Authentication authentication = authService.getAuthUser((String) model.get("remembertoken"));
		if(authentication == null)
			return "redirect:/signin";
		
		logger.info("Found the Authentication:"+ authentication.getProfile().getId());
		post.setProfile(authentication.getProfile());
		logger.info("Profile set for the new post:"+ post.getProfile());
		logger.info("new post details:"+ post.getId()+"tweet"+post.getTweet()+"profile:"+post.getProfile().getName());
		postRepository.save(post);
		logger.info("New Post Created:"+ post.getId());
		emailService.sendPostCreationMessage(authentication.getEmail(),authentication.getProfile().getName() );
		return "redirect:/post/"+post.getId();
	}
	
	@GetMapping("/post/{id}")
	public String display_post_page(ModelMap model, @PathVariable int id) {
		logger.info("Entered into Post view request");
		Authentication authentication = authService.getAuthUser((String) model.get("remembertoken"));
		if(authentication == null)
			return "redirect:/signin";
		
		logger.info("Found the Authentication:"+ authentication.getProfile().getId());
		Post post = postRepository.findById(id);
		logger.info("Found the post:"+post);
		model.addAttribute("post",post);
		return "post";
	}
	
}
