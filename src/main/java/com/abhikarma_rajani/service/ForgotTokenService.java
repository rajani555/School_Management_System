package com.abhikarma_rajani.service;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.abhikarma_rajani.entity.ForgotPasswordToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ForgotTokenService
{
	@Autowired
	JavaMailSender javaMailSender;					
	
	// Class level variable declaration !!
	private final int MINUTES= 10;
	public String generateToken()					//Step-2
	{
		return UUID.randomUUID().toString();		
	}
	
	public LocalDateTime expireTimeRange()			//Step-3
	{
		return LocalDateTime.now().plusMinutes(MINUTES);
	}
	
	public void sendEmail(String to, String subject, String emailLink) throws MessagingException, UnsupportedEncodingException			//Step-4
	{
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage);
		
		String emailContent= "<p>Hello!</p>"
							 + "Click the link below to reset password:"
							 + "<p><a href=\"" +emailLink+ "\">Link : Change my password</a></p>"
							 + "<br>"
							 + "Ignore this email if you did not made the request!";
		
		mimeMessageHelper.setText(emailContent, true);
		mimeMessageHelper.setFrom("rajanikant1588@gmail.com", "Coding Techniques Support");
		mimeMessageHelper.setSubject(subject);	
		mimeMessageHelper.setTo(to);
		
		javaMailSender.send(mimeMessage); 
	}
	
	public boolean isExpired(ForgotPasswordToken forgotPasswordToken)
	{
		return LocalDateTime.now().isAfter(forgotPasswordToken.getExpireTime());
	}
	
	public String checkValidity(ForgotPasswordToken forgotPasswordToken, Model model)
	{
		if(forgotPasswordToken == null)
		{
			model.addAttribute("error", "Invalid Token!");
			return "error-page";
		}
		else if(forgotPasswordToken.isUsed())
		{
			model.addAttribute("error", "The token is already used!");
			return "error-page";
		}
		else if(isExpired(forgotPasswordToken))
		{
			model.addAttribute("error", "The token is expired!");
			return "error-page";
		}
		else
		{
			return"reset-password";
		}
	}
	
}
