package com.example.ngodonations.service;

import com.example.ngodonations.exceptions.DuplicateDonorException;
import com.example.ngodonations.exceptions.NoSuchDonorException;
import com.example.ngodonations.model.Donation;
import com.example.ngodonations.model.Donor;

public interface DonorService {
    public boolean registerDonor(Donor donor) throws DuplicateDonorException;
    public boolean login(String email,String password) throws NoSuchDonorException;
    public Donation donateToNGO(Long id,Donation donation) throws NoSuchDonorException;
    public void sendThankyouMailToDonator(Donor donor);
    public String forgotPassword(String username);
    public void resetPassword(String email,String newPassword);
    public String emailPasswordToDonor(String email);
}
