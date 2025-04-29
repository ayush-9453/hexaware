package com.hexaware.dao;

import com.hexaware.entity.Donation;

import java.sql.*;

import com.hexaware.entity.CashDonation;
import com.hexaware.entity.ItemDonation;
import com.hexaware.utils.DbConn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonationDao{


    public static void recordDonation(Donation donation) {
        String sql = "INSERT INTO donations (donor_name, amount, donation_type, donation_date, item_type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, donation.getDonorName());
            stmt.setDouble(2, donation.getAmount());

            if (donation instanceof CashDonation) {
                CashDonation cash = (CashDonation) donation;
                stmt.setString(3, "cash");
                stmt.setDate(4, Date.valueOf(cash.getDonationDate()));
                stmt.setNull(5, Types.VARCHAR);
            } else if (donation instanceof ItemDonation) {
                ItemDonation item = (ItemDonation) donation;
                stmt.setString(3, "item");
                stmt.setNull(4, Types.DATE);
                stmt.setString(5, item.getItemType());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Donation> getAllDonations() {
        List<Donation> list = new ArrayList<>();
        String sql = "SELECT * FROM donations";

        try (Connection conn = DbConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String donorName = rs.getString("donor_name");
                double amount = rs.getDouble("amount");
                String type = rs.getString("donation_type");

                if ("cash".equalsIgnoreCase(type)) {
                    LocalDate date = rs.getDate("donation_date").toLocalDate();
                    list.add(new CashDonation(donorName, amount, date));
                } else if ("item".equalsIgnoreCase(type)) {
                    String itemType = rs.getString("item_type");
                    list.add(new ItemDonation(donorName, amount, itemType));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
