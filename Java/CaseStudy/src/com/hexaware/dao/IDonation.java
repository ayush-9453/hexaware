package com.hexaware.dao;

import com.hexaware.entity.Donation;

import java.util.List;

public interface IDonation {
    void recordDonation(Donation donation);
    List<Donation> getAllDonations();
}
