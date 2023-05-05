package com.example.ngodonations.service;

import com.example.ngodonations.exceptions.DuplicateDonorException;
import com.example.ngodonations.exceptions.InvalidEmailException;
import com.example.ngodonations.exceptions.InvalidInformation;
import com.example.ngodonations.exceptions.NoSuchDonorException;
import com.example.ngodonations.model.Donation;
import com.example.ngodonations.model.Donor;
import com.example.ngodonations.repository.DonationRepository;
import com.example.ngodonations.repository.DonorRepository;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    //Extra Chunk
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    //helper methods

    public Donor getDonorByEmail(String email) {
        return donorRepository.findByDonorEmail(email);
    }
    public Optional<Donor> getDonorById(Long id) {
        return donorRepository.findById(id);
    }
    public List<Donor> getAllDonors(){
        return donorRepository.findAll();
    }
    public void deleteDonorById(Long id){
        donorRepository.deleteById(id);
    }
    public void updateDonor(Donor donor){
        donorRepository.save(donor);
    }

    public List<Donation> getDonationsByDonorId(Long id) {
        Optional<Donor> donor = getDonorById(id);
        if (donor != null) {
            return donor.get().getDonations();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }


    //Main Chunk

    public boolean validatePhone(String phoneNumber) throws InvalidInformation {
        String phoneNumberString = phoneNumber;
        if (phoneNumberString.length() != 10) {
            throw new InvalidInformation("Phone Number Not Correct");
        }
        return true;
    }

    @Override
    public boolean registerDonor(Donor donor) throws DuplicateDonorException {
        if (getDonorByEmail(donor.getDonorEmail())!=null){
            throw new DuplicateDonorException("Email already taken");
        }
        validatePhone(donor.getDonorPhone());
        Donor flagDonor=donorRepository.save(donor);
        if (flagDonor==null)return false;
        return true;
    }

    @Override
    public boolean login(String email, String password) {
        Donor donor = donorRepository.findByDonorEmail(email);
        if (donor != null && donor.getDonorPassword().equals(password)) {
            System.out.println(true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Donation donateToNGO(Long id,Donation donation) throws NoSuchDonorException {
        Optional<Donor> optionalDonor = donorRepository.findById(id);
        if (optionalDonor.isPresent()) {
            Donor donor = optionalDonor.get();
            donation.setDonor(donor);
            Donation temp=donationRepository.save(donation);
            donor.getDonations().add(donation);
            donorRepository.save(donor);
            return temp;
        } else {
            throw new NoSuchDonorException("No Such Donor");
        }
    }



    public void sendMail(String to, String subject, String body) throws InvalidEmailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new InvalidEmailException("Invalid email address", e);
        }
    }

    @Override
    public void sendThankyouMailToDonator(Donor donor) {
        String to=donor.getDonorEmail();
        String subject="Thank You For Donation";
        String body="NGO Donations thanks you for helping needy people.";
        sendMail(to,subject,body);
    }

    @Override
    public String forgotPassword(String email) {
        Donor donor=getDonorByEmail(email);
        if(donor!=null){
            return donor.getDonorPassword();
        }
        return null;
    }

    @Override
    public void resetPassword(String email,String newPassword) {
        Donor donor=getDonorByEmail(email);
        if(donor!=null){
            donor.setDonorPassword(newPassword);
            donorRepository.save(donor);
        }
    }

    @Override
    public String emailPasswordToDonor(String email) {
        Donor donor=getDonorByEmail(email);
        if(donor!=null){
            return donor.getDonorPassword();
        }
        return null;
    }

    //Extra Services
}
