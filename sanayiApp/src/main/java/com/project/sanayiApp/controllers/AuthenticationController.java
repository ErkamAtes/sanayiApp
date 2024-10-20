package com.project.sanayiApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sanayiApp.business.abstracts.UserService;
import com.project.sanayiApp.business.requests.UserRequest;
import com.project.sanayiApp.entities.User;
import com.project.sanayiApp.security.JwtTokenProvider;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserService userService;
	
	private PasswordEncoder passwordEncoder;
	
	/*
	 * @PostMapping("/login") public String login(@RequestBody UserRequest
	 * loginRequest) { UsernamePasswordAuthenticationToken authenticationToken = new
	 * UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
	 * loginRequest.getPassword()); Authentication auth =
	 * authenticationManager.authenticate(authenticationToken);
	 * SecurityContextHolder.getContext().setAuthentication(auth); String jwtToken =
	 * jwtTokenProvider.generateJwtToken(auth); return "Bearer " + jwtToken;
	 * 
	 * }
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest loginRequest) {
	    try {
	        // Kullanıcı adı ve şifrenin doğru geldiğini kontrol etmek için log ekleyin
	        System.out.println("Login attempt with username: " + loginRequest.getUserName());
	        System.out.println("Password entered: " + loginRequest.getPassword());

	        // Kullanıcı adı ve şifre ile AuthenticationToken oluşturuluyor
	        UsernamePasswordAuthenticationToken authenticationToken = 
	            new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
	        
	        // AuthenticationManager kullanarak kimlik doğrulama işlemi yapılıyor
	        Authentication auth = authenticationManager.authenticate(authenticationToken);

	        // Kimlik doğrulama başarılıysa bir log yazdırın
	        System.out.println("Authentication successful for user: " + loginRequest.getUserName());
	        
	        // Kimlik doğrulama başarılıysa SecurityContext'e auth objesi atanıyor
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        
	        // JWT token oluşturuluyor
	        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
	        
	        // Başarılı sonuç ve token ile birlikte döndürülüyor
	        System.out.println("JWT Token generated: " + jwtToken);
	        return ResponseEntity.ok("Bearer " + jwtToken);
	        
	    } catch (BadCredentialsException ex) {
	        // Kullanıcı adı veya şifre hatalı ise 401 Unauthorized hatası döndürülüyor
	        System.out.println("Authentication failed: Invalid username or password");
	        System.out.println("Error message: " + ex.getMessage());
	        ex.printStackTrace();  // Stacktrace ile hatanın nerede oluştuğunu görebiliriz
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    } catch (Exception e) {
	        // Diğer hatalar için genel bir 500 Internal Server Error döndürülebilir
	        System.out.println("An unexpected error occurred during login.");
	        System.out.println("Exception type: " + e.getClass().getName());  // Hata türünü logla
	        System.out.println("Error message: " + e.getMessage());  // Hata mesajını logla
	        e.printStackTrace();  // Hatanın detaylarını ve nerede oluştuğunu görmek için stacktrace yazdır
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
	    }
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest registerRequest){
		if(userService.getUserByUserName(registerRequest.getUserName()) != null )
			return new ResponseEntity<>("Username already in use.", HttpStatus.BAD_REQUEST);
		User user = new User();
		user.setUserName(registerRequest.getUserName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userService.createUser(user);
		return new ResponseEntity<>("User successfully registered.", HttpStatus.CREATED);
	}
	
	
	
	
	
	
}
