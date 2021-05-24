package com.crypto.dao;

import com.crypto.DataStore;
import com.crypto.entities.User;

public class UserDao {

	public User[] getUsers() {

		return DataStore.getUsers();
	}
}
