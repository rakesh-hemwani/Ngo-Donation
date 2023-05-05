package com.example.ngodonations.controller;

import com.example.ngodonations.exceptions.DuplicateDonorException;
import com.example.ngodonations.exceptions.InvalidInformation;
import com.example.ngodonations.exceptions.NoSuchDonorException;
import com.example.ngodonations.model.Donation;
import com.example.ngodonations.model.Donor;
import com.example.ngodonations.model.EmailRequest;
import com.example.ngodonations.service.DonorServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/donors")
public class DonorController {

    @Autowired
    private DonorServiceImpl donorService;


    //Helper End Points
    @GetMapping({"/","/home"})
    public String home(){
        return "NGO HOME";
    }

    @GetMapping("/allDonors")
    public List<Donor> getAllDonors(){
        return donorService.getAllDonors();}

    @GetMapping("/donor/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id){
        if(donorService.getDonorById(id).isPresent()){
            return new ResponseEntity<>(donorService.getDonorById(id).get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().header("Message","Not Found").build();
    }

    @DeleteMapping("/deleteDonor/{id}")
    public void deleteDonorById(@PathVariable Long id){
        donorService.deleteDonorById(id);
    }

    @GetMapping("/allDonations")
    public List<Donation> getAllDonations(){
        return donorService.getAllDonations();
    }



    @GetMapping("/donations/{id}")
    public List<Donation> getDonationsByDonorId(@PathVariable Long id) {
        Optional<Donor> donor = donorService.getDonorById(id);
        if (donor.isPresent()) {
            return donorService.getDonationsByDonorId(id);
        } else {
            return new ArrayList<>();
        }
    }


    public boolean isValid(String password,String phone,String email)throws InvalidInformation{
        if (phone.length() != 10) {
            throw new InvalidInformation("Phone Number Not Correct");
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(!email.matches(regex)){
            throw new InvalidInformation("Email Not Valid");
        }
        if (password.length() < 8) {
            throw new InvalidInformation("Minimum 8 Character");
        }
        return true;
    }




    //Main End Points


    //Register
    @PostMapping("/register")
    public ResponseEntity<?> registerDonor(@RequestBody Donor donor) throws DuplicateDonorException {
        if (donorService.getDonorByEmail(donor.getDonorEmail())!=null){
            throw new DuplicateDonorException("Email already taken");
        }
        isValid(donor.getDonorPassword(),donor.getDonorPhone(),donor.getDonorEmail());
        Boolean flag=donorService.registerDonor(donor);
        if(!flag)return ResponseEntity.ok("Not Registered check with Information");
        return ResponseEntity.ok("Registered Successfully");
    }

    @ExceptionHandler(DuplicateDonorException.class)
    public ResponseEntity<?> handleDuplicateUserException(DuplicateDonorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(InvalidInformation.class)
    public ResponseEntity<?> handleInvalidInformationException(InvalidInformation ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    //Login
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginData, HttpSession session) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        if (donorService.login(email, password)) {
            Donor donor = donorService.getDonorByEmail(email);
            session.setAttribute("donor", donor);
            return "Login successful";
        } else {
            return "Login failed";
        }
    }

    //donateToNGo
    @PostMapping("/donateToNGO/{id}")
    public ResponseEntity<?> donateToNGO(@PathVariable Long id, @RequestBody Donation donation) throws NoSuchDonorException {
        Optional<Donor> optionalDonor = donorService.getDonorById(id);
        if (optionalDonor.isPresent()) {
            donorService.donateToNGO(id,donation);
            //donorService.sendThankyouMailToDonator(optionalDonor.get());
            return ResponseEntity.ok("Donated Successfully\n Sending Mail... \n " +
                    ""+optionalDonor.get().getDonorEmail()+"Thank You For Donating Rs"+ donation.getDonationAmount());
        } else {
            throw new NoSuchDonorException("No Donor Found with this id");
        }
    }

    @ExceptionHandler(NoSuchDonorException.class)
    public ResponseEntity<?> handleNoSuchDonorException(NoSuchDonorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    //Send Email
    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest request) {
        String to = request.getTo();
        String subject = request.getSubject();
        String body = request.getBody();
        donorService.sendMail(to, subject, body);
    }

    //Email Password to donor
    @PostMapping("/emailPassowrd/{email}")
    public ResponseEntity<?> emailPasswordToDonor(@PathVariable String email) throws NoSuchDonorException {
        Donor donor = donorService.getDonorByEmail(email);
        if (donor!=null) {
            String pass=donorService.emailPasswordToDonor(email);
            return ResponseEntity.ok("Sending Password to Mail... \n " +
                    ""+donor.getDonorName()+"Your Password is "+ pass);
        } else {
            throw new NoSuchDonorException("No Donor Found With This Email");
        }
    }


    //reset password
    @PostMapping("/resetPassword/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable String email,@RequestBody String newPassword) throws NoSuchDonorException {
        Donor donor = donorService.getDonorByEmail(email);
        if (donor!=null) {
            donorService.resetPassword(email,newPassword);
            return ResponseEntity.ok("Resetting Your Password..... \n " +
                    ""+donor.getDonorName()+" Your Password has been reset.");
        } else {
            throw new NoSuchDonorException("No Donor Found With This Email");
        }
    }

    //forgot password
    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email) throws NoSuchDonorException {
        Donor donor = donorService.getDonorByEmail(email);
        if (donor!=null) {
            String pass=donorService.forgotPassword(email);
            return ResponseEntity.ok("Sending Password to Mail... \n " +
                    ""+donor.getDonorName()+"Your Password is "+ pass);
        } else {
            throw new NoSuchDonorException("No Donor Found With This Email");
        }
    }
}
