package com.bank.model;

import com.bank.DTO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBInteraction {

	public static boolean signin(String fullname, String email, long mobile, String gender, String accountType,	String password, double initialBalance) {

		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users (fullname, email, mobile, gender, account_type, password, initial_balance) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, fullname);
			ps.setString(2, email);
			ps.setLong(3, mobile);
			ps.setString(4, gender);
			ps.setString(5, accountType);
			ps.setString(6, password);
			ps.setDouble(7, initialBalance);

			int result = ps.executeUpdate();
			if (result > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Bank login(String email, String password) {

		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Bank b = new Bank();
				b.setId(rs.getInt(1));
				b.setFullname(rs.getString(2));
				b.setEmail(rs.getString(3));
				b.setMobile(rs.getLong(4));
				b.setGender(rs.getString(5));
				b.setAccountType(rs.getString(6));
				b.setPassword(rs.getString(7));
				b.setInitialBalance(rs.getDouble(8));

				return b;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean depositAmount(int userId, double amount) {
		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE users SET initial_balance = initial_balance + ? WHERE id = ?");
			ps.setDouble(1, amount);
			ps.setInt(2, userId);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean withdrawAmount(int userId, double amount) {
		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE users SET initial_balance = initial_balance - ? WHERE id = ?");
			ps.setDouble(1, amount);
			ps.setInt(2, userId);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static double getBalance(int userId) {
		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT initial_balance FROM users WHERE id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	// Log transaction
	public static void logTransaction(int userId, String type, double amount) {
	    try (Connection con = DBConnection.getConnection()) {
	        PreparedStatement ps = con.prepareStatement("INSERT INTO transactions(user_id, type, amount) VALUES (?, ?, ?)");
	        ps.setInt(1, userId);
	        ps.setString(2, type);
	        ps.setDouble(3, amount);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public static List<String[]> getTransactionHistory(int userId) {
		List<String[]> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT type, amount, transaction_time FROM transactions WHERE user_id = ? ORDER BY transaction_time DESC");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String[] row = {
					rs.getString("type"),
					String.valueOf(rs.getDouble("amount")),
					rs.getString("transaction_time")
				};
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public static boolean updateUserProfile(int id, String fullname, String email, long mobile, String gender, String accountType, String password) {

		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE users SET fullname=?, email=?, mobile=?, gender=?, account_type=?, password=? WHERE id=?");
			ps.setString(1, fullname);
			ps.setString(2, email);
			ps.setLong(3, mobile);
			ps.setString(4, gender);
			ps.setString(5, accountType);
			ps.setString(6, password);
			ps.setInt(7, id);

			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
