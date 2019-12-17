package com.jithin.test.controller;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.jithin.test.mail.EmailService;
import com.jithin.test.model.VideoListResponse;

@Controller
@Validated
public class TrendingVideosController {

	@Resource
	RestTemplate restTemplate;

	@Autowired
	Environment env;

	@Autowired
	EmailService emailService;

	@GetMapping("/topVideos")
	public String getTopVideos(Model model, HttpSession session) {

		String url = env.getProperty("youtube.api.url") + "&key=" + env.getProperty("youtube.api.key");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<VideoListResponse> result = restTemplate.exchange(url, HttpMethod.GET, entity,
				VideoListResponse.class);
		model.addAttribute("data", result.getBody());
		
		//save the data in session so that the email api can fetch it from session.
		session.setAttribute("data", result.getBody());
		return "topVideos";
	}

	@PostMapping("/sendEmail")
	public String sendEmail(@RequestParam(required = true) @Email String emailId, HttpSession session, Model model) {
		
		VideoListResponse data = (VideoListResponse) session.getAttribute("data");
		
		if (data.getItems() != null) {
			
			// header
			StringBuilder sb = new StringBuilder();
			sb.append("Title,Published Date,View Count,Like Count").append(System.lineSeparator());
			// data

			data.getItems().stream().forEach(item -> sb.append(item.getSnippet().getTitle()).append(",")
					.append(item.getSnippet().getPublishedAt()).append(",").append(item.getStatistics().getViewCount())
					.append(",").append(item.getStatistics().getLikeCount()).append(System.lineSeparator()));

			ByteArrayResource byteArrResource = new ByteArrayResource(sb.toString().getBytes());
			emailService.sendMessageWithAttachment(emailId, env.getProperty("report.email.subject"),
					env.getProperty("report.email.content"), byteArrResource);
			model.addAttribute("message", "Email sent successfully!!!");
		}

		return "result";
	}

}
